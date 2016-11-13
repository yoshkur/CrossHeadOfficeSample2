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
import javax.persistence.Table;
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
@Table(name = "group_m")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupM.findAll", query = "SELECT g FROM GroupM g"),
    @NamedQuery(name = "GroupM.findByGroupid", query = "SELECT g FROM GroupM g WHERE g.groupid = :groupid"),
    @NamedQuery(name = "GroupM.findByGroupname", query = "SELECT g FROM GroupM g WHERE g.groupname = :groupname"),
    @NamedQuery(name = "GroupM.findByMemo", query = "SELECT g FROM GroupM g WHERE g.memo = :memo"),
    @NamedQuery(name = "GroupM.findByAdddate", query = "SELECT g FROM GroupM g WHERE g.adddate = :adddate"),
    @NamedQuery(name = "GroupM.findByAddprogram", query = "SELECT g FROM GroupM g WHERE g.addprogram = :addprogram"),
    @NamedQuery(name = "GroupM.findByAddcode", query = "SELECT g FROM GroupM g WHERE g.addcode = :addcode"),
    @NamedQuery(name = "GroupM.findByUpdatedate", query = "SELECT g FROM GroupM g WHERE g.updatedate = :updatedate"),
    @NamedQuery(name = "GroupM.findByUpdateprogram", query = "SELECT g FROM GroupM g WHERE g.updateprogram = :updateprogram"),
    @NamedQuery(name = "GroupM.findByUpdatecode", query = "SELECT g FROM GroupM g WHERE g.updatecode = :updatecode"),
    @NamedQuery(name = "GroupM.findByValidrow", query = "SELECT g FROM GroupM g WHERE g.validrow = :validrow"),
    @NamedQuery(name = "GroupM.findByRoworder", query = "SELECT g FROM GroupM g WHERE g.roworder = :roworder")})
public class GroupM implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer groupid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String groupname;
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
    @Basic(optional = false)
    @NotNull
    private int roworder;

    public GroupM() {
    }

    public GroupM(Integer groupid) {
        this.groupid = groupid;
    }

    public GroupM(Integer groupid, String groupname, Date adddate, String addprogram, String addcode, Date updatedate, String updateprogram, String updatecode, boolean validrow, int roworder) {
        this.groupid = groupid;
        this.groupname = groupname;
        this.adddate = adddate;
        this.addprogram = addprogram;
        this.addcode = addcode;
        this.updatedate = updatedate;
        this.updateprogram = updateprogram;
        this.updatecode = updatecode;
        this.validrow = validrow;
        this.roworder = roworder;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
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

    public int getRoworder() {
        return roworder;
    }

    public void setRoworder(int roworder) {
        this.roworder = roworder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupid != null ? groupid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupM)) {
            return false;
        }
        GroupM other = (GroupM) object;
        if ((this.groupid == null && other.groupid != null) || (this.groupid != null && !this.groupid.equals(other.groupid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jp.co.orangeright.crossheadofficesample2.entity.GroupM[ groupid=" + groupid + " ]";
    }

}
