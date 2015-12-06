package com.templetree.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Lalith Mannur
 */

@Entity
@Table(name = "attributes")
public class Attributes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "type")
    private String type;
    @Column(name = "tkey")
    private String tkey;
    @Column(name = "value")
    private String value;
    @Column(name = "createdDate", insertable = true, updatable = false)
    private Timestamp createdDate;
    @Column(name = "updatedDate")
    private Timestamp updatedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTkey() {
        return tkey;
    }

    public void setTkey(String tkey) {
        this.tkey = tkey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
}
