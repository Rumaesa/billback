import { Component, OnInit } from '@angular/core';
import { DistributorForm } from '../../models/DistributorForm';
import { DistributorDataService } from '../../services/DistributorDataService';
import { propertyOrder, STANDARD_HEADER} from '../../constants/Constants';
import { DistributorFieldMapping } from '../../models/DistributorFieldMapping';
import { DashboardService } from '../../services/dashboardService';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
import { map } from 'rxjs';
import { Distributor } from '../../models/Distributor';
import { DistributorMappingDTO } from '../../models/DistributorMappingDTO';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-distributor-mapping',
  templateUrl: './distributor-mapping.component.html',
  styleUrl: './distributor-mapping.component.scss'
})
export class DistributorMappingComponent implements OnInit {

  formData: DistributorForm | null = null;
  distributorHeader: any[] = [];
  standardHeader: string[] = [];
  distributorMapping!: DistributorFieldMapping;
  distributorMappingDto!: DistributorMappingDTO;
  form!: FormGroup;
  mappingSequence: any[]= propertyOrder;
// dropdowns: any;

  HEADER_TO_DTO_MAP: { [key: string]: keyof DistributorMappingDTO } = {
    "Address of customer product shipped to": "customerProductShippedToAddress",
    "Amount to be credited per each for this item (difference between purchase each price and PX each price":
      "amountCreditedPerItem",
    "BillBack Partner Customer Number": "billbackPartnerCustomerNumber",
    "City of customer product shipped to": "customerProductShippedToCity",
    "Invoice Date or Ship Date. This is required to validate px pricing in effect at time of invoice or shipment to customer":
      "invoiceShippedDate",
    "Invoice number distributor billed and shipped to customer":
      "distributorBilledShippedInvoiceNumber",
    "Name of customer distributor shipped product to": "customerDistributorName",
    "No Mapping": "noMapping",
    "PX unit price for this item/customer on SGA's PX": "sgaPXCustomerUnitPrice",
    "Quantity Shipped": "quantityShipped",
    "Rebate Claim - Extended (Amt to be credited each x Qty shipped)":
      "rebateClaim",
    "SGA 11 - digit material number": "materialNumber",
    "SGA PX # for Distributor and Customer combination for BillBack":
      "billbackSgaPXDistributorCustomerCombination",
    "State of customer product shipped to": "customerProductShippedToState",
    "Unit of Measure expressed on SGA invoice to Distributor":
      "distributorSgaInvoiceUOM",
    "Unit price on distributor invoice from SGA for this product":
      "distributorInvoiceSgaProductUnitPrice",
    "Zip Code of customer product shipped to": "customerProductShippedToZipCode",
  };

  constructor(private distributorDataService: DistributorDataService,
    private distributorService: DashboardService, private dialog: MatDialog, private router: Router,
    private snackBar: MatSnackBar
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
    this.checkIfMappingExists(this.formData!);
  }
  checkIfMappingExists( distributorForm :DistributorForm){
    if(distributorForm.distributor.isMappingPresent){
      this.getCurrentDistributorMapping(distributorForm.distributor.mappingId);
    }
  }

  getCurrentDistributorMapping(mappingId: number){
    console.log(mappingId);
    this.distributorService.getDistributorMapping(mappingId).subscribe({
      next: (items: DistributorMappingDTO) => {
        // this.distributorMappingDto = items;
        this.distributorMappingDto = {
          customerProductShippedToAddress: items.customerProductShippedToAddress || '',
          amountCreditedPerItem: items.amountCreditedPerItem || '',
          billbackPartnerCustomerNumber: items.billbackPartnerCustomerNumber || '',
          customerProductShippedToCity: items.customerProductShippedToCity || '',
          invoiceShippedDate: items.invoiceShippedDate || '',
          distributorBilledShippedInvoiceNumber: items.distributorBilledShippedInvoiceNumber || '',
          customerDistributorName: items.customerDistributorName || '',
          noMapping: items.noMapping || '',
          sgaPXCustomerUnitPrice: items.sgaPXCustomerUnitPrice || '',
          quantityShipped: items.quantityShipped || '',
          rebateClaim: items.rebateClaim || '',
          materialNumber: items.materialNumber || '',
          billbackSgaPXDistributorCustomerCombination: items.billbackSgaPXDistributorCustomerCombination || '',
          customerProductShippedToState: items.customerProductShippedToState || '',
          distributorSgaInvoiceUOM: items.distributorSgaInvoiceUOM || '',
          distributorInvoiceSgaProductUnitPrice: items.distributorInvoiceSgaProductUnitPrice || '',
          customerProductShippedToZipCode: items.customerProductShippedToZipCode || ''
        };

        console.log(this.distributorMappingDto);
      },
      error: (error) => {
        console.error('error code :: ' + error.status)
      }
    })
    
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
      distributorName: this.formData?.distributor.distributorName ?? '',
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
        this.distributorService.createDistributorMapping(distributorFieldMapping, this.formData?.distributor.distributorId, this.formData.create).subscribe({
          next: (data) => {
            console.log("saved..."+data);
            this.router.navigate(['/dashboard']).then(() => {
              this.snackBar.open('Distributor Mapping saved successfully!', 'X', {
                duration: 5000,
                verticalPosition: 'top',
                horizontalPosition: 'right',
                panelClass: ['snackbar-success'],
              });
            });
          },
          error: (error) => {
            console.log("encountered an error.."+error);
            this.snackBar.open('Failed to save mapping. Try again!', 'X', {
              duration: 5000,
              panelClass: ['snackbar-error'],  // Optional: Add custom styles
            });
          }
        })
    }
  }

  cancel(): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '350px',
      data: { message: 'All the mapping will be lost. Are you sure you want to cancel?' }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        // User confirmed, navigate to another page
        this.router.navigate(['/dashboard']); // Change to your target route
      }
    });
  }
}
