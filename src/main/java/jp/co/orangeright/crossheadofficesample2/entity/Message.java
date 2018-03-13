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
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m")
    , @NamedQuery(name = "Message.findByMessageid", query = "SELECT m FROM Message m WHERE m.messageid = :messageid")
    , @NamedQuery(name = "Message.findBySubject", query = "SELECT m FROM Message m WHERE m.subject = :subject")
    , @NamedQuery(name = "Message.findByMessagetime", query = "SELECT m FROM Message m WHERE m.messagetime = :messagetime")
    , @NamedQuery(name = "Message.findByMessagefrom", query = "SELECT m FROM Message m WHERE m.messagefrom = :messagefrom")
    , @NamedQuery(name = "Message.findByBody", query = "SELECT m FROM Message m WHERE m.body = :body")
    , @NamedQuery(name = "Message.findByMessageto", query = "SELECT m FROM Message m WHERE m.messageto = :messageto")
    , @NamedQuery(name = "Message.findByReadst", query = "SELECT m FROM Message m WHERE m.readst = :readst")
    , @NamedQuery(name = "Message.findByReadtime", query = "SELECT m FROM Message m WHERE m.readtime = :readtime")
    , @NamedQuery(name = "Message.findByAdddate", query = "SELECT m FROM Message m WHERE m.adddate = :adddate")
    , @NamedQuery(name = "Message.findByAddprogram", query = "SELECT m FROM Message m WHERE m.addprogram = :addprogram")
    , @NamedQuery(name = "Message.findByAddcode", query = "SELECT m FROM Message m WHERE m.addcode = :addcode")
    , @NamedQuery(name = "Message.findByUpdatedate", query = "SELECT m FROM Message m WHERE m.updatedate = :updatedate")
    , @NamedQuery(name = "Message.findByUpdateprogram", query = "SELECT m FROM Message m WHERE m.updateprogram = :updateprogram")
    , @NamedQuery(name = "Message.findByUpdatecode", query = "SELECT m FROM Message m WHERE m.updatecode = :updatecode")
    , @NamedQuery(name = "Message.findByValidrow", query = "SELECT m FROM Message m WHERE m.validrow = :validrow")})
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer messageid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String subject;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date messagetime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String messagefrom;
    @Size(max = 2147483647)
    private String body;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String messageto;
    @Basic(optional = false)
    @NotNull
    private boolean readst;
    @Temporal(TemporalType.TIMESTAMP)
    private Date readtime;
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

    public Message() {
    }

    public Message(Integer messageid) {
        this.messageid = messageid;
    }

    public Message(Integer messageid, String subject, Date messagetime, String messagefrom, String messageto, boolean readst, Date adddate, String addprogram, String addcode, Date updatedate, String updateprogram, String updatecode, boolean validrow) {
        this.messageid = messageid;
        this.subject = subject;
        this.messagetime = messagetime;
        this.messagefrom = messagefrom;
        this.messageto = messageto;
        this.readst = readst;
        this.adddate = adddate;
        this.addprogram = addprogram;
        this.addcode = addcode;
        this.updatedate = updatedate;
        this.updateprogram = updateprogram;
        this.updatecode = updatecode;
        this.validrow = validrow;
    }

    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getMessagetime() {
        return messagetime;
    }

    public void setMessagetime(Date messagetime) {
        this.messagetime = messagetime;
    }

    public String getMessagefrom() {
        return messagefrom;
    }

    public void setMessagefrom(String messagefrom) {
        this.messagefrom = messagefrom;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMessageto() {
        return messageto;
    }

    public void setMessageto(String messageto) {
        this.messageto = messageto;
    }

    public boolean getReadst() {
        return readst;
    }

    public void setReadst(boolean readst) {
        this.readst = readst;
    }

    public Date getReadtime() {
        return readtime;
    }

    public void setReadtime(Date readtime) {
        this.readtime = readtime;
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
        hash += (messageid != null ? messageid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.messageid == null && other.messageid != null) || (this.messageid != null && !this.messageid.equals(other.messageid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jp.co.orangeright.crossheadofficesample2.entity.Message[ messageid=" + messageid + " ]";
    }
    
}
