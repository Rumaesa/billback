import { Distributor } from "./Distributor";

export interface DistributorFieldMapping {
  distributorName: string;
  claimPeriod: string;
  customerProductShippedToAddress: string;
  amountCreditedPerItem: string;
  billbackPartnerCustomerNumber: string;
  customerProductShippedToCity: string;
  invoiceShippedDate: string;
  distributorBilledShippedInvoiceNumber: string;
  customerDistributorName: string;
  noMapping: string;
  sgaPXCustomerUnitPrice: string;
  quantityShipped: string;
  rebateClaim: string;
  materialNumber: string;
  billbackSgaPXDistributorCustomerCombination: string;
  customerProductShippedToState: string;
  distributorSgaInvoiceUOM: string;
  distributorInvoiceSgaProductUnitPrice: string;
  customerProductShippedToZipCode: string;
}
