package com.templetree.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Lalith on 10/1/15.
 */
@Entity
@Table(name = "items")
public class Item {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "barcode")
    private String barcode;
    @Column(name = "category")
    private String category;
    @Column(name = "itemName")
    private String itemName;
    @Column(name = "salesPrice")
    private Double salesPrice;
    @Column(name = "purchasePrice")
    private Double purchasePrice;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "createdDate", insertable = true, updatable = false)
    private Timestamp createdDate;
    @Column(name = "updatedDate")
    private Timestamp updatedDate;

    public Item() {

    }

    public Item(String barcode, String itemName, Double purchasePrice, Integer quantity) {
        this.barcode = barcode;
        this.itemName = itemName;
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (!barcode.equals(item.barcode)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = barcode.hashCode();
        return result;
    }
}
