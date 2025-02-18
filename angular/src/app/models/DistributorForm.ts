import { Distributor } from "./Distributor";

export class DistributorForm {
  distributor: Distributor
  claimPeriod: string;
  file: File | null;
  create: boolean;
  distributorHeader: any[];

  constructor(distributor: Distributor , claimPeriod: string, file: File | null, create: boolean, distributorHeader: any[]) {
    this.distributor = distributor;
    this.claimPeriod = claimPeriod;
    this.file = file;
    this.create = create;
    this.distributorHeader = distributorHeader;
  }
}