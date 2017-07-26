/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.orangeright.crossheadofficesample2.jsf.nextdayschedule;

import java.util.Date;
import jp.co.orangeright.crossheadofficesample2.jsf.util.SearchCondition;

/**
 *
 * @author yosh
 */
public class NextdayscheduleSearchCondition extends SearchCondition {

    private String sendto;
    private String sendmessagetitle;
    private String sendmessage;
    private Boolean returnflg;
    private String returnmessage;
    private Date adddate;
    private String addprogram;
    private String addcode;
    private Date updatedate;
    private String updateprogram;
    private String updatecode;
    private boolean validrow;

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

    public boolean isValidrow() {
        return validrow;
    }

    public void setValidrow(boolean validrow) {
        this.validrow = validrow;
    }

}
