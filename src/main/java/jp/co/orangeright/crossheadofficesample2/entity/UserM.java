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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "user_m")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserM.findAll", query = "SELECT u FROM UserM u"),
    @NamedQuery(name = "UserM.findByUserid", query = "SELECT u FROM UserM u WHERE u.userid = :userid"),
    @NamedQuery(name = "UserM.findByPasswd", query = "SELECT u FROM UserM u WHERE u.passwd = :passwd"),
    @NamedQuery(name = "UserM.findByUsername", query = "SELECT u FROM UserM u WHERE u.username = :username"),
    @NamedQuery(name = "UserM.findByGroupid", query = "SELECT u FROM UserM u WHERE u.groupid = :groupid"),
    @NamedQuery(name = "UserM.findByPhone", query = "SELECT u FROM UserM u WHERE u.phone = :phone"),
    @NamedQuery(name = "UserM.findByMobilephone", query = "SELECT u FROM UserM u WHERE u.mobilephone = :mobilephone"),
    @NamedQuery(name = "UserM.findByMail", query = "SELECT u FROM UserM u WHERE u.mail = :mail"),
    @NamedQuery(name = "UserM.findByMobilemail", query = "SELECT u FROM UserM u WHERE u.mobilemail = :mobilemail"),
    @NamedQuery(name = "UserM.findByJoindate", query = "SELECT u FROM UserM u WHERE u.joindate = :joindate"),
    @NamedQuery(name = "UserM.findByLeavedate", query = "SELECT u FROM UserM u WHERE u.leavedate = :leavedate"),
    @NamedQuery(name = "UserM.findByStatejoin", query = "SELECT u FROM UserM u WHERE u.statejoin = :statejoin"),
    @NamedQuery(name = "UserM.findByAdministrator", query = "SELECT u FROM UserM u WHERE u.administrator = :administrator"),
    @NamedQuery(name = "UserM.findByViewpersonalinfo", query = "SELECT u FROM UserM u WHERE u.viewpersonalinfo = :viewpersonalinfo"),
    @NamedQuery(name = "UserM.findByViewallschedule", query = "SELECT u FROM UserM u WHERE u.viewallschedule = :viewallschedule"),
    @NamedQuery(name = "UserM.findByWriteotherschedule", query = "SELECT u FROM UserM u WHERE u.writeotherschedule = :writeotherschedule"),
    @NamedQuery(name = "UserM.findByViewotherschedule", query = "SELECT u FROM UserM u WHERE u.viewotherschedule = :viewotherschedule"),
    @NamedQuery(name = "UserM.findByViewallmessage", query = "SELECT u FROM UserM u WHERE u.viewallmessage = :viewallmessage"),
    @NamedQuery(name = "UserM.findByViewalluser", query = "SELECT u FROM UserM u WHERE u.viewalluser = :viewalluser"),
    @NamedQuery(name = "UserM.findByAdddate", query = "SELECT u FROM UserM u WHERE u.adddate = :adddate"),
    @NamedQuery(name = "UserM.findByAddprogram", query = "SELECT u FROM UserM u WHERE u.addprogram = :addprogram"),
    @NamedQuery(name = "UserM.findByAddcode", query = "SELECT u FROM UserM u WHERE u.addcode = :addcode"),
    @NamedQuery(name = "UserM.findByUpdatedate", query = "SELECT u FROM UserM u WHERE u.updatedate = :updatedate"),
    @NamedQuery(name = "UserM.findByUpdateprogram", query = "SELECT u FROM UserM u WHERE u.updateprogram = :updateprogram"),
    @NamedQuery(name = "UserM.findByUpdatecode", query = "SELECT u FROM UserM u WHERE u.updatecode = :updatecode"),
    @NamedQuery(name = "UserM.findByValidrow", query = "SELECT u FROM UserM u WHERE u.validrow = :validrow"),
    @NamedQuery(name = "UserM.findByRoworder", query = "SELECT u FROM UserM u WHERE u.roworder = :roworder"),
    @NamedQuery(name = "UserM.findByBbspermit", query = "SELECT u FROM UserM u WHERE u.bbspermit = :bbspermit"),
    @NamedQuery(name = "UserM.findByItemadmin", query = "SELECT u FROM UserM u WHERE u.itemadmin = :itemadmin")})
public class UserM implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String userid;
    @Size(max = 2147483647)
    private String passwd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    private String username;
    @JoinColumn(name = "groupid", referencedColumnName = "groupid")
    @ManyToOne
    private GroupM groupid;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="電話/FAXの形式が無効です。xxx-xxx-xxxxの形式にしてください")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 2147483647)
    private String phone;
    @Size(max = 2147483647)
    private String mobilephone;
    @Size(max = 2147483647)
    private String mail;
    @Size(max = 2147483647)
    private String mobilemail;
    @Temporal(TemporalType.DATE)
    private Date joindate;
    @Temporal(TemporalType.DATE)
    private Date leavedate;
    @Basic(optional = false)
    @NotNull
    private boolean statejoin;
    @Basic(optional = false)
    @NotNull
    private boolean administrator;
    @Basic(optional = false)
    @NotNull
    private boolean viewpersonalinfo;
    @Basic(optional = false)
    @NotNull
    private boolean viewallschedule;
    @Basic(optional = false)
    @NotNull
    private boolean writeotherschedule;
    @Basic(optional = false)
    @NotNull
    private boolean viewotherschedule;
    @Basic(optional = false)
    @NotNull
    private boolean viewallmessage;
    @Basic(optional = false)
    @NotNull
    private boolean viewalluser;
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
    private Boolean bbspermit;
    private Boolean itemadmin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private List<Schedule> scheduleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private List<Item> itemList;

    public UserM() {
    }

    public UserM(String userid) {
        this.userid = userid;
    }

    public UserM(String userid, String username, boolean statejoin, boolean administrator, boolean viewpersonalinfo, boolean viewallschedule, boolean writeotherschedule, boolean viewotherschedule, boolean viewallmessage, boolean viewalluser, Date adddate, String addprogram, String addcode, Date updatedate, String updateprogram, String updatecode, boolean validrow, int roworder) {
        this.userid = userid;
        this.username = username;
        this.statejoin = statejoin;
        this.administrator = administrator;
        this.viewpersonalinfo = viewpersonalinfo;
        this.viewallschedule = viewallschedule;
        this.writeotherschedule = writeotherschedule;
        this.viewotherschedule = viewotherschedule;
        this.viewallmessage = viewallmessage;
        this.viewalluser = viewalluser;
        this.adddate = adddate;
        this.addprogram = addprogram;
        this.addcode = addcode;
        this.updatedate = updatedate;
        this.updateprogram = updateprogram;
        this.updatecode = updatecode;
        this.validrow = validrow;
        this.roworder = roworder;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public GroupM getGroupid() {
        return groupid;
    }

    public void setGroupid(GroupM groupid) {
        this.groupid = groupid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMobilemail() {
        return mobilemail;
    }

    public void setMobilemail(String mobilemail) {
        this.mobilemail = mobilemail;
    }

    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    public Date getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(Date leavedate) {
        this.leavedate = leavedate;
    }

    public boolean getStatejoin() {
        return statejoin;
    }

    public void setStatejoin(boolean statejoin) {
        this.statejoin = statejoin;
    }

    public boolean getAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public boolean getViewpersonalinfo() {
        return viewpersonalinfo;
    }

    public void setViewpersonalinfo(boolean viewpersonalinfo) {
        this.viewpersonalinfo = viewpersonalinfo;
    }

    public boolean getViewallschedule() {
        return viewallschedule;
    }

    public void setViewallschedule(boolean viewallschedule) {
        this.viewallschedule = viewallschedule;
    }

    public boolean getWriteotherschedule() {
        return writeotherschedule;
    }

    public void setWriteotherschedule(boolean writeotherschedule) {
        this.writeotherschedule = writeotherschedule;
    }

    public boolean getViewotherschedule() {
        return viewotherschedule;
    }

    public void setViewotherschedule(boolean viewotherschedule) {
        this.viewotherschedule = viewotherschedule;
    }

    public boolean getViewallmessage() {
        return viewallmessage;
    }

    public void setViewallmessage(boolean viewallmessage) {
        this.viewallmessage = viewallmessage;
    }

    public boolean getViewalluser() {
        return viewalluser;
    }

    public void setViewalluser(boolean viewalluser) {
        this.viewalluser = viewalluser;
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

    public Boolean getBbspermit() {
        return bbspermit;
    }

    public void setBbspermit(Boolean bbspermit) {
        this.bbspermit = bbspermit;
    }

    public Boolean getItemadmin() {
        return itemadmin;
    }

    public void setItemadmin(Boolean itemadmin) {
        this.itemadmin = itemadmin;
    }

    @XmlTransient
    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
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
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserM)) {
            return false;
        }
        UserM other = (UserM) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jp.co.orangeright.crossheadofficesample2.entity.UserM[ userid=" + userid + " ]";
    }

}
