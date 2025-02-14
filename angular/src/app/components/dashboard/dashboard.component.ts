import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../services/dashboardService';
import { Distributor } from '../../models/Distributor';
import { DistributorForm } from '../../models/DistributorForm';
import { DistributorDataService } from '../../services/DistributorDataService';
import * as XLSX from 'xlsx';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {

  form!: FormGroup;
  distributor: Distributor[] = [];
  // distributorOptions = ['Option 1', 'Option 2', 'Option 3']; not using it.
  claimPeriodOptions: string[] = [];
  selectedFile: File | null = null;
  distributorFormData!: DistributorForm;
  selectedDistributor: Distributor | undefined = undefined;
  DistributorFetchedHeader: any[] = [];


  currentDate = new Date();
  currentMonth = this.currentDate.getMonth() + 1;

  constructor(private fb: FormBuilder,
    private dashboardService: DashboardService,
    private distributorDataService: DistributorDataService,
    private router: Router) {
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      distributor: ['', Validators.required],
      claimPeriod: ['', Validators.required],
      file: [null, Validators.required], // Ensure file is selected
    });
    this.getActiveDistributors();
    this.claimPeriodOptions = this.getPast13Months();
  }

  onDistributorSelect(event: any): void {
    const selectedDistributorId = event.target.value;
    this.selectedDistributor = this.distributor.find(
      (dist) => dist.distributorId === +selectedDistributorId
    );
    console.log('Selected Distributor:', this.selectedDistributor);
  }

  getActiveDistributors() {
    this.dashboardService.getActiveDistributors().subscribe({
      next: (items: any[]) => {
        this.distributor = items;
      },
      error: (error) => {
        console.error('error code :: ' + error.status)
      }
    })
  }

  onSubmit(form: any): void {
    if (this.form.valid) {
      if (this.selectedDistributor) {
        this.distributorFormData = {
          distributorId: this.selectedDistributor.distributorId,
          distributorName: this.selectedDistributor.distributorName,
          claimPeriod: form.value.claimPeriod,
          file: this.selectedFile,
          create: this.selectedDistributor.isMappingPresent ? false : true,
          distributorHeader: this.DistributorFetchedHeader
        };
        this.distributorDataService.setFormData(this.distributorFormData);
        this.router.navigate(['/distributor-mapping']);
      } else {
        console.log('Distributor not found');
      }
    } else {
      console.log('Form is invalid');
    }
  }

  onFileChange(event: any): void {
    const file = event.target.files[0];

    if (file) {
      const reader = new FileReader();

      // When the file is read, parse it
      reader.onload = (e: ProgressEvent<FileReader>) => {
        // Read the file as binary string
        const binaryString = e.target?.result as string;  // Cast result to string

        // Parse the Excel file using xlsx
        const wb = XLSX.read(binaryString, { type: 'binary' });

        // Assuming the first sheet is the one we want to read (change index if necessary)
        const sheetName = wb.SheetNames[0];
        const sheet = wb.Sheets[sheetName];

        // Convert the sheet data to JSON format
        const data = XLSX.utils.sheet_to_json(sheet, { header: 1 }); // header: 1 treats the first row as header

        // Extract the specific row based on the row number in selectedDistributor.header
        let rowIndex = -1;
        if (this.selectedDistributor && this.selectedDistributor.header !== undefined) {
          rowIndex = this.selectedDistributor.header - 1;
        }

        if (data[rowIndex]) {
          const rowData = data[rowIndex] as any;  // Get the data for the specified row

          // Store rowData in the global variable
          this.DistributorFetchedHeader = rowData;

          console.log(this.DistributorFetchedHeader);  // Log the global row data
        } else {
          console.log('Row not found.');
        }
        // After the file is processed, update the form control
        this.selectedFile = file;  // Store the selected file in the component

        // Manually set the value of the 'file' form control
        this.form.controls['file'].setValue(file);

        // Mark the file form control as touched to trigger validation
        this.form.controls['file'].markAsDirty();  // Mark as dirty
        this.form.controls['file'].updateValueAndValidity();  // Re-validate the control
      };

      reader.readAsBinaryString(file);  // Start reading the file
    }
  }


  getPast13Months(): string[] {
    const monthsList: string[] = [];
    const currentDate = new Date();

    for (let i = 0; i < 13; i++) {
      const year = currentDate.getFullYear();
      const month = currentDate.getMonth() + 1; // Months are 0-based

      // Format to YYYYMM
      const formattedMonth = `${year}${month.toString().padStart(2, '0')}`;
      monthsList.push(formattedMonth);

      // Move to the previous month
      currentDate.setMonth(currentDate.getMonth() - 1);
    }

    return monthsList;
  }

}
