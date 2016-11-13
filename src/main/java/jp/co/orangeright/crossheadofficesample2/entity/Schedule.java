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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s"),
    @NamedQuery(name = "Schedule.findByScheid", query = "SELECT s FROM Schedule s WHERE s.scheid = :scheid"),
    @NamedQuery(name = "Schedule.findByOwnerid", query = "SELECT s FROM Schedule s WHERE s.ownerid = :ownerid"),
    @NamedQuery(name = "Schedule.findByTitle", query = "SELECT s FROM Schedule s WHERE s.title = :title"),
    @NamedQuery(name = "Schedule.findByDatefrom", query = "SELECT s FROM Schedule s WHERE s.datefrom = :datefrom"),
    @NamedQuery(name = "Schedule.findByDateto", query = "SELECT s FROM Schedule s WHERE s.dateto = :dateto"),
    @NamedQuery(name = "Schedule.findByState", query = "SELECT s FROM Schedule s WHERE s.state = :state"),
    @NamedQuery(name = "Schedule.findByItemno", query = "SELECT s FROM Schedule s WHERE s.itemno = :itemno"),
    @NamedQuery(name = "Schedule.findByAddress", query = "SELECT s FROM Schedule s WHERE s.address = :address"),
    @NamedQuery(name = "Schedule.findByCustomer", query = "SELECT s FROM Schedule s WHERE s.customer = :customer"),
    @NamedQuery(name = "Schedule.findByTel", query = "SELECT s FROM Schedule s WHERE s.tel = :tel"),
    @NamedQuery(name = "Schedule.findByNote", query = "SELECT s FROM Schedule s WHERE s.note = :note"),
    @NamedQuery(name = "Schedule.findByWorkmemo", query = "SELECT s FROM Schedule s WHERE s.workmemo = :workmemo"),
    @NamedQuery(name = "Schedule.findByStatein", query = "SELECT s FROM Schedule s WHERE s.statein = :statein"),
    @NamedQuery(name = "Schedule.findByDatein", query = "SELECT s FROM Schedule s WHERE s.datein = :datein"),
    @NamedQuery(name = "Schedule.findByStateout", query = "SELECT s FROM Schedule s WHERE s.stateout = :stateout"),
    @NamedQuery(name = "Schedule.findByDateout", query = "SELECT s FROM Schedule s WHERE s.dateout = :dateout"),
    @NamedQuery(name = "Schedule.findByOpen", query = "SELECT s FROM Schedule s WHERE s.open = :open"),
    @NamedQuery(name = "Schedule.findByAdddate", query = "SELECT s FROM Schedule s WHERE s.adddate = :adddate"),
    @NamedQuery(name = "Schedule.findByAddprogram", query = "SELECT s FROM Schedule s WHERE s.addprogram = :addprogram"),
    @NamedQuery(name = "Schedule.findByAddcode", query = "SELECT s FROM Schedule s WHERE s.addcode = :addcode"),
    @NamedQuery(name = "Schedule.findByUpdatedate", query = "SELECT s FROM Schedule s WHERE s.updatedate = :updatedate"),
    @NamedQuery(name = "Schedule.findByUpdateprogram", query = "SELECT s FROM Schedule s WHERE s.updateprogram = :updateprogram"),
    @NamedQuery(name = "Schedule.findByUpdatecode", query = "SELECT s FROM Schedule s WHERE s.updatecode = :updatecode"),
    @NamedQuery(name = "Schedule.findByValidrow", query = "SELECT s FROM Schedule s WHERE s.validrow = :validrow")})
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer scheid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String ownerid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String title;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date datefrom;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateto;
    @Size(max = 2147483647)
    private String state;
    @Size(max = 2147483647)
    private String itemno;
    @Size(max = 2147483647)
    private String address;
    @Size(max = 2147483647)
    private String customer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String tel;
    @Size(max = 2147483647)
    private String note;
    @Size(max = 2147483647)
    private String workmemo;
    @Basic(optional = false)
    @NotNull
    private boolean statein;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datein;
    @Basic(optional = false)
    @NotNull
    private boolean stateout;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateout;
    @Basic(optional = false)
    @NotNull
    private boolean open;
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
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private UserM userid;

    public Schedule() {
    }

    public Schedule(Integer scheid) {
        this.scheid = scheid;
    }

    public Schedule(Integer scheid, String ownerid, String title, Date datefrom, Date dateto, String tel, boolean statein, boolean stateout, boolean open, Date adddate, String addprogram, String addcode, Date updatedate, String updateprogram, String updatecode, boolean validrow) {
        this.scheid = scheid;
        this.ownerid = ownerid;
        this.title = title;
        this.datefrom = datefrom;
        this.dateto = dateto;
        this.tel = tel;
        this.statein = statein;
        this.stateout = stateout;
        this.open = open;
        this.adddate = adddate;
        this.addprogram = addprogram;
        this.addcode = addcode;
        this.updatedate = updatedate;
        this.updateprogram = updateprogram;
        this.updatecode = updatecode;
        this.validrow = validrow;
    }

    public Integer getScheid() {
        return scheid;
    }

    public void setScheid(Integer scheid) {
        this.scheid = scheid;
    }

    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(Date datefrom) {
        this.datefrom = datefrom;
    }

    public Date getDateto() {
        return dateto;
    }

    public void setDateto(Date dateto) {
        this.dateto = dateto;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getWorkmemo() {
        return workmemo;
    }

    public void setWorkmemo(String workmemo) {
        this.workmemo = workmemo;
    }

    public boolean getStatein() {
        return statein;
    }

    public void setStatein(boolean statein) {
        this.statein = statein;
    }

    public Date getDatein() {
        return datein;
    }

    public void setDatein(Date datein) {
        this.datein = datein;
    }

    public boolean getStateout() {
        return stateout;
    }

    public void setStateout(boolean stateout) {
        this.stateout = stateout;
    }

    public Date getDateout() {
        return dateout;
    }

    public void setDateout(Date dateout) {
        this.dateout = dateout;
    }

    public boolean getOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
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

    public UserM getUserid() {
        return userid;
    }

    public void setUserid(UserM userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scheid != null ? scheid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schedule)) {
            return false;
        }
        Schedule other = (Schedule) object;
        if ((this.scheid == null && other.scheid != null) || (this.scheid != null && !this.scheid.equals(other.scheid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jp.co.orangeright.crossheadofficesample2.entity.Schedule[ scheid=" + scheid + " ]";
    }

}
