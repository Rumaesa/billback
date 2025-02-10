import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DashboardService } from '../../services/dashboardService';
import { Distributor } from '../../models/Distributor';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {
  
  form!: FormGroup;
  distributor: Distributor[] = [];
  // distributorOptions = ['Option 1', 'Option 2', 'Option 3']; not using it.
  claimPeriodOptions : string[] = [];

  currentDate = new Date();
  currentMonth = this.currentDate.getMonth() + 1;

  constructor(private fb: FormBuilder, private dashboardService: DashboardService) { 
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

  getActiveDistributors(){
    this.dashboardService.getActiveDistributors().subscribe({
      next: (items: any[]) => {
        this.distributor = items;
      },
      error: (error) => {
        console.error('error code :: '+ error.status)
      }
    })
  }

   onSubmit(): void {
    if (this.form.valid) {
      console.log(this.form.value);
      // You can add logic to handle the form data here
    } else {
      console.log('Form is invalid');
    }
  }

  // Handle file selection
  onFileChange(event: any): void {
    const file = event.target.files[0];
    this.form.patchValue({
      file: file,
    });
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
