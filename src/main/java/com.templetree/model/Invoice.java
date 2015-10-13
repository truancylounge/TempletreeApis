package com.templetree.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Lalith on 10/6/15.
 */
@Entity
@Table(name = "invoices")
public class Invoice {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "invoiceName")
    private String invoiceName;
    @Column(name = "subTotal")
    private Double subTotal;
    @Column(name = "shipping")
    private Double shipping;
    @Column(name = "packing")
    private Double packing;
    @Column(name = "grandTotal")
    private Double grandTotal;
    @Column(name = "createdDate", insertable = true, updatable = false)
    private Timestamp createdDate;
    @Column(name = "updatedDate")
    private Timestamp updatedDate;

    @JsonManagedReference
    @OneToMany(cascade=CascadeType.ALL, mappedBy="invoice")
    private List<InvoicesItems> invoicesItemsList;

    public Invoice() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getShipping() {
        return shipping;
    }

    public void setShipping(Double shipping) {
        this.shipping = shipping;
    }

    public Double getPacking() {
        return packing;
    }

    public void setPacking(Double packing) {
        this.packing = packing;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
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

    public List<InvoicesItems> getInvoicesItemsList() {
        return invoicesItemsList;
    }

    public void setInvoicesItemsList(List<InvoicesItems> invoicesItemsList) {
        this.invoicesItemsList = invoicesItemsList;
    }
}
