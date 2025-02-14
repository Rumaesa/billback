import { Component, OnInit } from '@angular/core';
import { DistributorForm } from '../../models/DistributorForm';
import { DistributorDataService } from '../../services/DistributorDataService';
import { STANDARD_HEADER } from '../../constants/Constants';

@Component({
  selector: 'app-distributor-mapping',
  templateUrl: './distributor-mapping.component.html',
  styleUrl: './distributor-mapping.component.scss'
})
export class DistributorMappingComponent implements OnInit {

  formData: DistributorForm | null = null;
  distributorHeader: any[] = [];
  standardHeader: string[] = [];

  constructor(private distributorDataService: DistributorDataService) { }

  ngOnInit(): void {
    this.formData = this.distributorDataService.getFormData();
    this.distributorHeader = this.formData?.distributorHeader ?? [];
    this.standardHeader = [...STANDARD_HEADER]; 
  }

}
