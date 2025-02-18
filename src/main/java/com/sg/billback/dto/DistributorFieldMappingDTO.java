package com.sg.billback.dto;

import lombok.Data;

@Data
public class DistributorFieldMappingDTO {
	private Integer mappingId;
	private String customerProductShippedToAddress;
	private String amountCreditedPerItem;
	private String billbackPartnerCustomerNumber;
	private String customerProductShippedToCity;
	private String invoiceShippedDate;
	private String distributorBilledShippedInvoiceNumber;
	private String customerDistributorName;
	private String noMapping;
	private String sgaPXCustomerUnitPrice;
	private String quantityShipped;
	private String rebateClaim;
	private String materialNumber;
	private String billbackSgaPXDistributorCustomerCombination;
	private String customerProductShippedToState;
	private String distributorSgaInvoiceUOM;
	private String distributorInvoiceSgaProductUnitPrice;
	private String customerProductShippedToZipCode;
}
