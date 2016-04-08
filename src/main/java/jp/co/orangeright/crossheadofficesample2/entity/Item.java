/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.orangeright.crossheadofficesample2.entity;

import java.io.File;
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
	@NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
	@NamedQuery(name = "Item.findByItemid", query = "SELECT i FROM Item i WHERE i.itemid = :itemid"),
	@NamedQuery(name = "Item.findByItemcd", query = "SELECT i FROM Item i WHERE i.itemcd = :itemcd"),
	@NamedQuery(name = "Item.findByDetail", query = "SELECT i FROM Item i WHERE i.detail = :detail"),
	@NamedQuery(name = "Item.findByAppoint", query = "SELECT i FROM Item i WHERE i.appoint = :appoint"),
	@NamedQuery(name = "Item.findByScheid", query = "SELECT i FROM Item i WHERE i.scheid = :scheid"),
	@NamedQuery(name = "Item.findByWorked", query = "SELECT i FROM Item i WHERE i.worked = :worked"),
	@NamedQuery(name = "Item.findByWorkedreport", query = "SELECT i FROM Item i WHERE i.workedreport = :workedreport"),
	@NamedQuery(name = "Item.findByFinished", query = "SELECT i FROM Item i WHERE i.finished = :finished"),
	@NamedQuery(name = "Item.findByCancel", query = "SELECT i FROM Item i WHERE i.cancel = :cancel"),
	@NamedQuery(name = "Item.findByAccounting", query = "SELECT i FROM Item i WHERE i.accounting = :accounting"),
	@NamedQuery(name = "Item.findByOnhold", query = "SELECT i FROM Item i WHERE i.onhold = :onhold"),
	@NamedQuery(name = "Item.findByDirectpayment", query = "SELECT i FROM Item i WHERE i.directpayment = :directpayment"),
	@NamedQuery(name = "Item.findByMemo", query = "SELECT i FROM Item i WHERE i.memo = :memo"),
	@NamedQuery(name = "Item.findByAdddate", query = "SELECT i FROM Item i WHERE i.adddate = :adddate"),
	@NamedQuery(name = "Item.findByAddprogram", query = "SELECT i FROM Item i WHERE i.addprogram = :addprogram"),
	@NamedQuery(name = "Item.findByAddcode", query = "SELECT i FROM Item i WHERE i.addcode = :addcode"),
	@NamedQuery(name = "Item.findByUpdatedate", query = "SELECT i FROM Item i WHERE i.updatedate = :updatedate"),
	@NamedQuery(name = "Item.findByUpdateprogram", query = "SELECT i FROM Item i WHERE i.updateprogram = :updateprogram"),
	@NamedQuery(name = "Item.findByUpdatecode", query = "SELECT i FROM Item i WHERE i.updatecode = :updatecode"),
	@NamedQuery(name = "Item.findByValidrow", query = "SELECT i FROM Item i WHERE i.validrow = :validrow")})
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Integer itemid;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 2147483647)
	private String itemcd;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 2147483647)
	private String detail;
	@Basic(optional = false)
	@NotNull
	private boolean appoint;
	@JoinColumn(name = "scheid", referencedColumnName = "scheid")
	@ManyToOne(optional = false)
	private Schedule scheid;
	@Basic(optional = false)
	@NotNull
	private boolean worked;
	@Size(max = 2147483647)
	private String workedreport;
	@Basic(optional = false)
	@NotNull
	private boolean finished;
	@Basic(optional = false)
	@NotNull
	private boolean cancel;
	@Basic(optional = false)
	@NotNull
	private boolean accounting;
	@Basic(optional = false)
	@NotNull
	private boolean onhold;
	@Basic(optional = false)
	@NotNull
	private boolean directpayment;
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
	@JoinColumn(name = "customerid", referencedColumnName = "customerid")
	@ManyToOne
	private Customer customerid;
	@JoinColumn(name = "userid", referencedColumnName = "userid")
	@ManyToOne(optional = false)
	private UserM userid;

	public Item() {
	}

	public Item(Integer itemid) {
		this.itemid = itemid;
	}

	public Item(Integer itemid, String itemcd, String detail, boolean appoint, boolean worked, boolean finished, boolean cancel, boolean accounting, boolean onhold, boolean directpayment, Date adddate, String addprogram, String addcode, Date updatedate, String updateprogram, String updatecode, boolean validrow) {
		this.itemid = itemid;
		this.itemcd = itemcd;
		this.detail = detail;
		this.appoint = appoint;
		this.worked = worked;
		this.finished = finished;
		this.cancel = cancel;
		this.accounting = accounting;
		this.onhold = onhold;
		this.directpayment = directpayment;
		this.adddate = adddate;
		this.addprogram = addprogram;
		this.addcode = addcode;
		this.updatedate = updatedate;
		this.updateprogram = updateprogram;
		this.updatecode = updatecode;
		this.validrow = validrow;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public String getItemcd() {
		return itemcd;
	}

	public void setItemcd(String itemcd) {
		this.itemcd = itemcd;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public boolean getAppoint() {
		return appoint;
	}

	public void setAppoint(boolean appoint) {
		this.appoint = appoint;
	}

	public Schedule getScheid() {
		return scheid;
	}

	public void setScheid(Schedule scheid) {
		this.scheid = scheid;
	}

	public boolean getWorked() {
		return worked;
	}

	public void setWorked(boolean worked) {
		this.worked = worked;
	}

	public String getWorkedreport() {
		return workedreport;
	}

	public void setWorkedreport(String workedreport) {
		this.workedreport = workedreport;
	}

	public boolean getFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public boolean getCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

	public boolean getAccounting() {
		return accounting;
	}

	public void setAccounting(boolean accounting) {
		this.accounting = accounting;
	}

	public boolean getOnhold() {
		return onhold;
	}

	public void setOnhold(boolean onhold) {
		this.onhold = onhold;
	}

	public boolean getDirectpayment() {
		return directpayment;
	}

	public void setDirectpayment(boolean directpayment) {
		this.directpayment = directpayment;
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

	public Customer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Customer customerid) {
		this.customerid = customerid;
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
		hash += (itemid != null ? itemid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Item)) {
			return false;
		}
		Item other = (Item) object;
		if ((this.itemid == null && other.itemid != null) || (this.itemid != null && !this.itemid.equals(other.itemid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "jp.co.orangeright.crossheadofficesample2.entity.Item[ itemid=" + itemid + " ]";
	}

	public String getPayment() {
		if (this.directpayment) {
			return "直収";
		} else {
			return "本部請求";
		}
	}

	public String getStatus() {
		if (this.getCancel()) {
			return "キャンセル";
		} else if (this.getOnhold()) {
			return "保留中";
		} else if (this.getAccounting()) {
			return "経理処理完了";
		} else if (this.getFinished()) {
			return "経理処理中";
		} else if (this.getWorked()) {
			return "提携先完了報告待ち";
		} else if (this.getAppoint()) {
			return "作業待ち";
		} else {
			return "アポイント中";
		}
	}

	public String getWorkedfilename() {
		return new File(this.getWorkedreport()).getName();
	}

	public Boolean isKDDI() {
		return this.customerid.getCustomername().contains("KDDI");
	}
}
