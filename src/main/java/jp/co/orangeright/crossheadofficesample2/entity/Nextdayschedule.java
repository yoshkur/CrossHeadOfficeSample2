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
    @NamedQuery(name = "Nextdayschedule.findAll", query = "SELECT n FROM Nextdayschedule n"),
    @NamedQuery(name = "Nextdayschedule.findById", query = "SELECT n FROM Nextdayschedule n WHERE n.id = :id"),
    @NamedQuery(name = "Nextdayschedule.findBySendto", query = "SELECT n FROM Nextdayschedule n WHERE n.sendto = :sendto"),
    @NamedQuery(name = "Nextdayschedule.findBySendmessagetitle", query = "SELECT n FROM Nextdayschedule n WHERE n.sendmessagetitle = :sendmessagetitle"),
    @NamedQuery(name = "Nextdayschedule.findBySendmessage", query = "SELECT n FROM Nextdayschedule n WHERE n.sendmessage = :sendmessage"),
    @NamedQuery(name = "Nextdayschedule.findByReturnflg", query = "SELECT n FROM Nextdayschedule n WHERE n.returnflg = :returnflg"),
    @NamedQuery(name = "Nextdayschedule.findByReturnmessage", query = "SELECT n FROM Nextdayschedule n WHERE n.returnmessage = :returnmessage"),
    @NamedQuery(name = "Nextdayschedule.findByAdddate", query = "SELECT n FROM Nextdayschedule n WHERE n.adddate = :adddate"),
    @NamedQuery(name = "Nextdayschedule.findByAddprogram", query = "SELECT n FROM Nextdayschedule n WHERE n.addprogram = :addprogram"),
    @NamedQuery(name = "Nextdayschedule.findByAddcode", query = "SELECT n FROM Nextdayschedule n WHERE n.addcode = :addcode"),
    @NamedQuery(name = "Nextdayschedule.findByUpdatedate", query = "SELECT n FROM Nextdayschedule n WHERE n.updatedate = :updatedate"),
    @NamedQuery(name = "Nextdayschedule.findByUpdateprogram", query = "SELECT n FROM Nextdayschedule n WHERE n.updateprogram = :updateprogram"),
    @NamedQuery(name = "Nextdayschedule.findByUpdatecode", query = "SELECT n FROM Nextdayschedule n WHERE n.updatecode = :updatecode"),
    @NamedQuery(name = "Nextdayschedule.findByValidrow", query = "SELECT n FROM Nextdayschedule n WHERE n.validrow = :validrow")})
public class Nextdayschedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Size(max = 2147483647)
    private String sendto;
    @Size(max = 2147483647)
    private String sendmessagetitle;
    @Size(max = 2147483647)
    private String sendmessage;
    private Boolean returnflg;
    @Size(max = 2147483647)
    private String returnmessage;
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

    public Nextdayschedule() {
    }

    public Nextdayschedule(Integer id) {
        this.id = id;
    }

    public Nextdayschedule(Integer id, Date adddate, String addprogram, String addcode, Date updatedate, String updateprogram, String updatecode, boolean validrow) {
        this.id = id;
        this.adddate = adddate;
        this.addprogram = addprogram;
        this.addcode = addcode;
        this.updatedate = updatedate;
        this.updateprogram = updateprogram;
        this.updatecode = updatecode;
        this.validrow = validrow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSendto() {
        return sendto;
    }

    public void setSendto(String sendto) {
        this.sendto = sendto;
    }

    public String getSendmessagetitle() {
        return sendmessagetitle;
    }

    public void setSendmessagetitle(String sendmessagetitle) {
        this.sendmessagetitle = sendmessagetitle;
    }

    public String getSendmessage() {
        return sendmessage;
    }

    public void setSendmessage(String sendmessage) {
        this.sendmessage = sendmessage;
    }

    public Boolean getReturnflg() {
        return returnflg;
    }

    public void setReturnflg(Boolean returnflg) {
        this.returnflg = returnflg;
    }

    public String getReturnmessage() {
        return returnmessage;
    }

    public void setReturnmessage(String returnmessage) {
        this.returnmessage = returnmessage;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nextdayschedule)) {
            return false;
        }
        Nextdayschedule other = (Nextdayschedule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jp.co.orangeright.crossheadofficesample2.entity.Nextdayschedule[ id=" + id + " ]";
    }
    
}
