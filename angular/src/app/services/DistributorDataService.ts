// distributor-data.service.ts
import { Injectable } from '@angular/core';
import { DistributorForm } from '../models/DistributorForm';


@Injectable({
  providedIn: 'root'
})
export class DistributorDataService {
  private formData: DistributorForm | null = null;

  constructor() { }

  // Store form data
  setFormData(data: DistributorForm): void {
    this.formData = data;
  }

  // Get form data
  getFormData(): DistributorForm | null {
    return this.formData;
  }
}
