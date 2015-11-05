package com.templetree.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Lalith Mannur
 */

@Entity
@Table(name = "billingInvoicesItems")
public class BillingInvoicesItems {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "barcode")
    private String barcode;
    @Column(name = "itemName")
    private String itemName;
    @Column(name = "purchasePrice")
    private Double purchasePrice;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "total")
    private Double total; // field is quantity * purchase price used in Invoices
    @Column(name = "createdDate", insertable = true, updatable = false)
    private Timestamp createdDate;
    @Column(name = "updatedDate")
    private Timestamp updatedDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "billingInvoiceId")
    private BillingInvoice billingInvoice;

    public BillingInvoicesItems() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public BillingInvoice getBillingInvoice() {
        return billingInvoice;
    }

    public void setBillingInvoice(BillingInvoice billingInvoice) {
        this.billingInvoice = billingInvoice;
    }
}
