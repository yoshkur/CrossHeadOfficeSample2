/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.orangeright.crossheadofficesample2.jsf.item;

import java.util.Date;
import java.util.List;
import jp.co.orangeright.crossheadofficesample2.entity.Customer;
import jp.co.orangeright.crossheadofficesample2.entity.UserM;
import jp.co.orangeright.crossheadofficesample2.jsf.util.SearchCondition;

/**
 *
 * @author yosh
 */
public class ItemSearchCondition extends SearchCondition {

    private String itemcd;
    private String keyword;
    private Customer customer;
    private List<Customer> customerList;
    private UserM user;
    private List<UserM> userList;
    private Boolean appoint;
    private Date turnStart;
    private Date turnEnd;
    private Date addDateStart;
    private Date addDateEnd;
    private Date updateDateStart;
    private Date updateDateEnd;
    private Boolean worked;
    private Boolean finished;
    private Boolean onhold;
    private Boolean cancel;
    private Boolean payment;
    private Boolean accounting;

    public ItemSearchCondition() {
    }

    public String getItemcd() {
        return itemcd;
    }

    public void setItemcd(String itemcd) {
        this.itemcd = itemcd;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public UserM getUser() {
        return user;
    }

    public void setUser(UserM user) {
        this.user = user;
    }

    public List<UserM> getUserList() {
        return userList;
    }

    public void setUserList(List<UserM> userList) {
        this.userList = userList;
    }

    public Boolean getAppoint() {
        return appoint;
    }

    public void setAppoint(Boolean appoint) {
        this.appoint = appoint;
    }

    public Date getTurnStart() {
        return turnStart;
    }

    public void setTurnStart(Date turnStart) {
        this.turnStart = turnStart;
    }

    public Date getTurnEnd() {
        return turnEnd;
    }

    public void setTurnEnd(Date turnEnd) {
        this.turnEnd = turnEnd;
    }

    public Boolean getWorked() {
        return worked;
    }

    public void setWorked(Boolean worked) {
        this.worked = worked;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Boolean getOnhold() {
        return onhold;
    }

    public void setOnhold(Boolean onhold) {
        this.onhold = onhold;
    }

    public Boolean getCancel() {
        return cancel;
    }

    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
    }

    public Boolean getPayment() {
        return payment;
    }

    public void setPayment(Boolean payment) {
        this.payment = payment;
    }

    public Boolean getAccounting() {
        return accounting;
    }

    public void setAccounting(Boolean accounting) {
        this.accounting = accounting;
    }

    public Date getAddDateStart() {
        return addDateStart;
    }

    public void setAddDateStart(Date addDateStart) {
        this.addDateStart = addDateStart;
    }

    public Date getAddDateEnd() {
        return addDateEnd;
    }

    public void setAddDateEnd(Date addDateEnd) {
        this.addDateEnd = addDateEnd;
    }

    public Date getUpdateDateStart() {
        return updateDateStart;
    }

    public void setUpdateDateStart(Date updateDateStart) {
        this.updateDateStart = updateDateStart;
    }

    public Date getUpdateDateEnd() {
        return updateDateEnd;
    }

    public void setUpdateDateEnd(Date updateDateEnd) {
        this.updateDateEnd = updateDateEnd;
    }

}
