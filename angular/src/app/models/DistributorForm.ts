export class DistributorForm {
  distributorId: number;
  distributorName: string;
  claimPeriod: string;
  file: File | null;
  create: boolean;
  distributorHeader: any[];

  constructor(distributorId: number, claimPeriod: string, file: File | null, create: boolean, distributorName: string, distributorHeader: any[]) {
    this.distributorId = distributorId;
    this.distributorName = distributorName;
    this.claimPeriod = claimPeriod;
    this.file = file;
    this.create = create;
    this.distributorHeader = distributorHeader;
  }
}