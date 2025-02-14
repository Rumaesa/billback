import { Distributor } from "./Distributor";

export interface DistributorFieldMapping {
  mappingId: number;
  distributorName: string;
  claimPeriod: string;
  customerShiptoAddress: string;
  amountCreditedPerLine: string;
  billbackPartnerCustomerNumber: string;
  customerShiptoCity: string;
  invoiceDate: string;
  invoiceNumber: string;
  distributorInvoiceNumber: string;
  distributorCustomerName: string;
  noMapping: string;
  unitPriceItem: string;
  quantityShipped: string;
  rebateClaim: string;
  materialNumber: string;
  salesInvoiceDate: string;
  customerProductState: string;
  sgaDistributorUom: string;
  sgaDistributorUnitPrice: string;
  agreedCost: string;
  // distributor: Distributor; // Reference to the Distributor model
}
