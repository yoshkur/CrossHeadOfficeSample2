/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.orangeright.crossheadofficesample2.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yosh
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Keihi.findAll", query = "SELECT k FROM Keihi k"),
    @NamedQuery(name = "Keihi.findByItemid", query = "SELECT k FROM Keihi k WHERE k.itemid = :itemid"),
    @NamedQuery(name = "Keihi.findByRecorddate", query = "SELECT k FROM Keihi k WHERE k.recorddate = :recorddate"),
    @NamedQuery(name = "Keihi.findByRecordprogram", query = "SELECT k FROM Keihi k WHERE k.recordprogram = :recordprogram"),
    @NamedQuery(name = "Keihi.findByRecorduserid", query = "SELECT k FROM Keihi k WHERE k.recorduserid = :recorduserid"),
    @NamedQuery(name = "Keihi.findByRecordvalid", query = "SELECT k FROM Keihi k WHERE k.recordvalid = :recordvalid"),
    @NamedQuery(name = "Keihi.findByShomohin", query = "SELECT k FROM Keihi k WHERE k.shomohin = :shomohin"),
    @NamedQuery(name = "Keihi.findBySharyo", query = "SELECT k FROM Keihi k WHERE k.sharyo = :sharyo"),
    @NamedQuery(name = "Keihi.findByKotsuhi", query = "SELECT k FROM Keihi k WHERE k.kotsuhi = :kotsuhi"),
    @NamedQuery(name = "Keihi.findByNidukuriunchin", query = "SELECT k FROM Keihi k WHERE k.nidukuriunchin = :nidukuriunchin"),
    @NamedQuery(name = "Keihi.findByKonetsuhi", query = "SELECT k FROM Keihi k WHERE k.konetsuhi = :konetsuhi"),
    @NamedQuery(name = "Keihi.findByKosaihi", query = "SELECT k FROM Keihi k WHERE k.kosaihi = :kosaihi"),
    @NamedQuery(name = "Keihi.findByShuzenhi", query = "SELECT k FROM Keihi k WHERE k.shuzenhi = :shuzenhi"),
    @NamedQuery(name = "Keihi.findByZappi", query = "SELECT k FROM Keihi k WHERE k.zappi = :zappi")})
public class Keihi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer itemid;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIME)
    private Date recorddate;
    @Size(max = 2147483647)
    private String recordprogram;
    @Size(max = 2147483647)
    private String recorduserid;
    private Boolean recordvalid;
    private Integer shomohin;
    private Integer sharyo;
    private Integer kotsuhi;
    private Integer nidukuriunchin;
    private Integer konetsuhi;
    private Integer kosaihi;
    private Integer shuzenhi;
    private Integer zappi;

    public Keihi() {
    }

    public Keihi(Integer itemid) {
        this.itemid = itemid;
    }

    public Keihi(Integer itemid, Date recorddate) {
        this.itemid = itemid;
        this.recorddate = recorddate;
    }

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public Date getRecorddate() {
        return recorddate;
    }

    public void setRecorddate(Date recorddate) {
        this.recorddate = recorddate;
    }

    public String getRecordprogram() {
        return recordprogram;
    }

    public void setRecordprogram(String recordprogram) {
        this.recordprogram = recordprogram;
    }

    public String getRecorduserid() {
        return recorduserid;
    }

    public void setRecorduserid(String recorduserid) {
        this.recorduserid = recorduserid;
    }

    public Boolean getRecordvalid() {
        return recordvalid;
    }

    public void setRecordvalid(Boolean recordvalid) {
        this.recordvalid = recordvalid;
    }

    public Integer getShomohin() {
        return shomohin;
    }

    public void setShomohin(Integer shomohin) {
        this.shomohin = shomohin;
    }

    public Integer getSharyo() {
        return sharyo;
    }

    public void setSharyo(Integer sharyo) {
        this.sharyo = sharyo;
    }

    public Integer getKotsuhi() {
        return kotsuhi;
    }

    public void setKotsuhi(Integer kotsuhi) {
        this.kotsuhi = kotsuhi;
    }

    public Integer getNidukuriunchin() {
        return nidukuriunchin;
    }

    public void setNidukuriunchin(Integer nidukuriunchin) {
        this.nidukuriunchin = nidukuriunchin;
    }

    public Integer getKonetsuhi() {
        return konetsuhi;
    }

    public void setKonetsuhi(Integer konetsuhi) {
        this.konetsuhi = konetsuhi;
    }

    public Integer getKosaihi() {
        return kosaihi;
    }

    public void setKosaihi(Integer kosaihi) {
        this.kosaihi = kosaihi;
    }

    public Integer getShuzenhi() {
        return shuzenhi;
    }

    public void setShuzenhi(Integer shuzenhi) {
        this.shuzenhi = shuzenhi;
    }

    public Integer getZappi() {
        return zappi;
    }

    public void setZappi(Integer zappi) {
        this.zappi = zappi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemid != null ? itemid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Keihi)) {
            return false;
        }
        Keihi other = (Keihi) object;
        if ((this.itemid == null && other.itemid != null) || (this.itemid != null && !this.itemid.equals(other.itemid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jp.co.orangeright.crossheadofficesample2.entity.Keihi[ itemid=" + itemid + " ]";
    }
    
}
