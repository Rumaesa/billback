package com.sg.billback.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "distributor_field_mapping")
public class DistributorFieldMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mapping_id")
    private Integer mappingId;
    
    

    @OneToOne
    @JoinColumn(name = "distributor_id", referencedColumnName = "distributor_id")
    private Distributor distributor;

    @Column(name = "distributor_name", nullable = false, length = 45)
    private String distributorName;

    @Column(name = "claim_period", nullable = false, length = 8)
    private String claimPeriod;

    @Column(name = "customer_product_shipto_address", length = 45)
    private String customerProductShippedToAddress;

    @Column(name = "amount_credited_per_item", length = 45)
    private String amountCreditedPerItem;

    @Column(name = "billback_partner_customer_number", length = 45)
    private String billbackPartnerCustomerNumber;

    @Column(name = "customer_product_shipto_city", length = 45)
    private String customerProductShippedToCity;

    @Column(name = "invoice_ship_date", length = 45)
    private String invoiceShippedDate;

    @Column(name = "distributor_billed_shipped_invoice_number", length = 45)
    private String distributorBilledShippedInvoiceNumber;

    @Column(name = "customer_distributor_name", length = 45)
    private String customerDistributorName;

    @Column(name = "no_mapping", length = 45)
    private String noMapping;

    @Column(name = "sga_px_customer_unit_price", length = 45)
    private String sgaPXCustomerUnitPrice;

    @Column(name = "quanity_shipped", length = 45)
    private String quantityShipped;

    @Column(name = "rebate_claim", length = 45)
    private String rebateClaim;

    @Column(name = "material_number", length = 45)
    private String materialNumber;

    @Column(name = "billback_sga_px_distributor_customer_combination", length = 45)
    private String billbackSgaPXDistributorCustomerCombination;

    @Column(name = "customer_product_shipto_state", length = 45)
    private String customerProductShippedToState;

    @Column(name = "distributor_sga_invoice_uom", length = 45)
    private String distributorSgaInvoiceUOM;

    @Column(name = "distributor_invoice_sga_product_unit_price", length = 45)
    private String distributorInvoiceSgaProductUnitPrice;

    @Column(name = "customer_product_shipto_zipcode", length = 45)
    private String customerProductShippedToZipCode;
    
    @Column(name = "date_created")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateCreated;
    
    @Column(name = "date_modified")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateModified;

}
