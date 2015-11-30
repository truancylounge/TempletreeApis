package com.templetree.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Lalith Mannur
 */

@Entity
@Table(name = "billingInvoices")
public class BillingInvoice {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "invoiceName")
    private String invoiceName;
    @Column(name = "totalAmount")
    private Double totalAmount;
    @Column(name = "cash")
    private Double cash;
    @Column(name = "credit")
    private Double credit;
    @Column(name = "createdDate", insertable = true, updatable = false)
    private Timestamp createdDate;
    @Column(name = "updatedDate")
    private Timestamp updatedDate;

    @JsonManagedReference
    @OneToMany(cascade=CascadeType.ALL, mappedBy="billingInvoice")
    private List<BillingInvoicesItems> billingInvoicesItemsList;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    public BillingInvoice() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
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

    public List<BillingInvoicesItems> getBillingInvoicesItemsList() {
        return billingInvoicesItemsList;
    }

    public void setBillingInvoicesItemsList(List<BillingInvoicesItems> billingInvoicesItemsList) {
        this.billingInvoicesItemsList = billingInvoicesItemsList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }
}
