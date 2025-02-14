package com.sg.billback.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "distributor_field_mapping")
public class DistributorFieldMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mapping_id", nullable = false)
    private int mappingId;

//    @Column(name = "distributor_id", nullable = false)
//    private int distributorId;

    @Column(name = "distributor_name", nullable = false, length = 45)
    private String distributorName;

    @Column(name = "claim_period", nullable = false, length = 8)
    private String claimPeriod;

    @Column(name = "customer_shipto_address", length = 45)
    private String customerShiptoAddress;

    @Column(name = "amount_credited_per_line", length = 45)
    private String amountCreditedPerLine;

    @Column(name = "billback_partner_customer_number", length = 45)
    private String billbackPartnerCustomerNumber;

    @Column(name = "customer_shipto_city", length = 45)
    private String customerShiptoCity;

    @Column(name = "invoice_date", length = 45)
    private String invoiceDate;

    @Column(name = "invoice_number", length = 45)
    private String invoiceNumber;

    @Column(name = "distributor_invoice_number", length = 45)
    private String distributorInvoiceNumber;

    @Column(name = "distributor_cutomer_name", length = 45)
    private String distributorCustomerName;

    @Column(name = "no_mapping", length = 45)
    private String noMapping;

    @Column(name = "unit_price_item", length = 45)
    private String unitPriceItem;

    @Column(name = "quanity_shipped", length = 45)
    private String quantityShipped;

    @Column(name = "rebate_claim", length = 45)
    private String rebateClaim;

    @Column(name = "material_number", length = 45)
    private String materialNumber;

    @Column(name = "sales_invoice_date", length = 45)
    private String salesInvoiceDate;

    @Column(name = "customer_product_state", length = 45)
    private String customerProductState;

    @Column(name = "sga_distributor_uom", length = 45)
    private String sgaDistributorUom;

    @Column(name = "sga_distributor_unit_price", length = 45)
    private String sgaDistributorUnitPrice;

    @Column(name = "agreed_cost", length = 45)
    private String agreedCost;

    @OneToOne
    @JoinColumn(name = "distributor_id", referencedColumnName = "distributor_id")
    private Distributor distributor;
}
