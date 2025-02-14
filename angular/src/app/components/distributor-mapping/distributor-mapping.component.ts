import { Component, OnInit } from '@angular/core';
import { DistributorForm } from '../../models/DistributorForm';
import { DistributorDataService } from '../../services/DistributorDataService';
import { STANDARD_HEADER } from '../../constants/Constants';
import { FormGroup, FormArray, FormControl } from '@angular/forms';
import { DistributorFieldMapping } from '../../models/DistributorFieldMapping';
import { DashboardService } from '../../services/dashboardService';

@Component({
  selector: 'app-distributor-mapping',
  templateUrl: './distributor-mapping.component.html',
  styleUrl: './distributor-mapping.component.scss'
})
export class DistributorMappingComponent implements OnInit {

  formData: DistributorForm | null = null;
  distributorHeader: any[] = [];
  standardHeader: string[] = [];
  form!: FormGroup;
// dropdowns: any;

  constructor(private distributorDataService: DistributorDataService,
    private distributorService: DashboardService
  ) { }

  ngOnInit(): void {
    this.formData = this.distributorDataService.getFormData();
    this.distributorHeader = this.formData?.distributorHeader ?? [];
    console.log('Distributor Header:', this.distributorHeader); 
    this.standardHeader = [...STANDARD_HEADER]; 
    this.form = new FormGroup({
      dropdowns: new FormArray([])  // This will hold our dropdowns
    });
    for (let i = 0; i < 17; i++) {
      (this.form.get('dropdowns') as FormArray).push(new FormControl(''));
    }
  }

  get dropdowns() {
    return (this.form.get('dropdowns') as FormArray).controls;
  }

  getAvailableOptions(index: number): string[] {
    const selectedValues = this.form.value.dropdowns; // Get selected values
    return this.distributorHeader.filter(option =>
      !selectedValues.includes(option) || option === selectedValues[index] // Allow the same option for its dropdown
    );
  }


  onSubmit() {
    if (this.form.invalid) return;

    const selectedValues = this.form.value.dropdowns; // Extract selected dropdown values

    const distributorFieldMapping: DistributorFieldMapping = {
      distributorName: this.formData?.distributorName ?? '',
      claimPeriod: this.formData?.claimPeriod ?? '',
      customerProductShippedToAddress: selectedValues[0] || '', 
      amountCreditedPerItem: selectedValues[1] || '',
      billbackPartnerCustomerNumber: selectedValues[2] || '',
      customerProductShippedToCity: selectedValues[3] || '',
      invoiceShippedDate: selectedValues[4] || '',
      distributorBilledShippedInvoiceNumber: selectedValues[5] || '',
      customerDistributorName: selectedValues[6] || '',
      noMapping: selectedValues[7] || '',
      sgaPXCustomerUnitPrice: selectedValues[8] || '',
      quantityShipped: selectedValues[9] || '',
      rebateClaim: selectedValues[10] || '',
      materialNumber: selectedValues[11] || '',
      billbackSgaPXDistributorCustomerCombination: selectedValues[12] || '',
      customerProductShippedToState: selectedValues[13] || '',
      distributorSgaInvoiceUOM: selectedValues[14] || '',
      distributorInvoiceSgaProductUnitPrice: selectedValues[15] || '',
      customerProductShippedToZipCode: selectedValues[16] || '',
    };

    console.log('Mapped DistributorFieldMapping:', distributorFieldMapping);
    // You can now send this object to a service or store it
    if(this.formData){
      this.distributorService.createDistributorMapping(distributorFieldMapping, this.formData?.distributorId).subscribe({
        next: (data) => {
          console.log("saved..."+data);
        },
        error: (error) => {
          console.log("encountered an error.."+error);
        }
      })
    }
  }

  cancel(){}
}
