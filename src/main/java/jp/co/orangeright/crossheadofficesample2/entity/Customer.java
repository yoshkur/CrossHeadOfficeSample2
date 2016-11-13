/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.orangeright.crossheadofficesample2.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author yosh
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByCustomerid", query = "SELECT c FROM Customer c WHERE c.customerid = :customerid"),
    @NamedQuery(name = "Customer.findByCustomername", query = "SELECT c FROM Customer c WHERE c.customername = :customername"),
    @NamedQuery(name = "Customer.findByMemo", query = "SELECT c FROM Customer c WHERE c.memo = :memo"),
    @NamedQuery(name = "Customer.findByAdddate", query = "SELECT c FROM Customer c WHERE c.adddate = :adddate"),
    @NamedQuery(name = "Customer.findByAddprogram", query = "SELECT c FROM Customer c WHERE c.addprogram = :addprogram"),
    @NamedQuery(name = "Customer.findByAddcode", query = "SELECT c FROM Customer c WHERE c.addcode = :addcode"),
    @NamedQuery(name = "Customer.findByUpdatedate", query = "SELECT c FROM Customer c WHERE c.updatedate = :updatedate"),
    @NamedQuery(name = "Customer.findByUpdateprogram", query = "SELECT c FROM Customer c WHERE c.updateprogram = :updateprogram"),
    @NamedQuery(name = "Customer.findByUpdatecode", query = "SELECT c FROM Customer c WHERE c.updatecode = :updatecode"),
    @NamedQuery(name = "Customer.findByValidrow", query = "SELECT c FROM Customer c WHERE c.validrow = :validrow")})
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer customerid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String customername;
    @Size(max = 2147483647)
    private String memo;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date adddate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String addprogram;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String addcode;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String updateprogram;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String updatecode;
    @Basic(optional = false)
    @NotNull
    private boolean validrow;
    @OneToMany(mappedBy = "customerid")
    private List<Item> itemList;

    public Customer() {
    }

    public Customer(Integer customerid) {
        this.customerid = customerid;
    }

    public Customer(Integer customerid, String customername, Date adddate, String addprogram, String addcode, Date updatedate, String updateprogram, String updatecode, boolean validrow) {
        this.customerid = customerid;
        this.customername = customername;
        this.adddate = adddate;
        this.addprogram = addprogram;
        this.addcode = addcode;
        this.updatedate = updatedate;
        this.updateprogram = updateprogram;
        this.updatecode = updatecode;
        this.validrow = validrow;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getAdddate() {
        return adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

    public String getAddprogram() {
        return addprogram;
    }

    public void setAddprogram(String addprogram) {
        this.addprogram = addprogram;
    }

    public String getAddcode() {
        return addcode;
    }

    public void setAddcode(String addcode) {
        this.addcode = addcode;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getUpdateprogram() {
        return updateprogram;
    }

    public void setUpdateprogram(String updateprogram) {
        this.updateprogram = updateprogram;
    }

    public String getUpdatecode() {
        return updatecode;
    }

    public void setUpdatecode(String updatecode) {
        this.updatecode = updatecode;
    }

    public boolean getValidrow() {
        return validrow;
    }

    public void setValidrow(boolean validrow) {
        this.validrow = validrow;
    }

    @XmlTransient
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerid != null ? customerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.customerid == null && other.customerid != null) || (this.customerid != null && !this.customerid.equals(other.customerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jp.co.orangeright.crossheadofficesample2.entity.Customer[ customerid=" + customerid + " ]";
    }

}
