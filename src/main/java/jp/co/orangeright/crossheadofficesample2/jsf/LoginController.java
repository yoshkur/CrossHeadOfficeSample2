/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.orangeright.crossheadofficesample2.jsf;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import jp.co.orangeright.crossheadofficesample2.ejb.UserMFacade;
import jp.co.orangeright.crossheadofficesample2.entity.UserM;

/**
 *
 * @author yosh
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private UserM loginUser;
    @EJB
    private UserMFacade ejb;

    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
    }

    public UserM getLoginUser() {
        if (this.loginUser == null) {
            this.loginUser = new UserM();
            this.loginUser.setValidrow(false);
            // テスト用
//            this.loginUser = this.ejb.find("jimu");

        }
        return loginUser;
    }

    public void setLoginUser(UserM loginUser) {
        this.loginUser = loginUser;
    }

    public String login() {
        if (!this.loginUser.getValidrow()) {
            UserM tempUser = this.ejb.find(this.loginUser.getUserid());
            if (tempUser != null && tempUser.getValidrow()
                    && tempUser.getPasswd().equals(this.loginUser.getPasswd())
                    && tempUser.getItemadmin()) {
                this.loginUser = tempUser;
                return "/index.xhtml?faces-redirect=true";
            }
        }
        return "";
    }

    public String logout() {
        if (this.loginUser.getValidrow()) {
            this.loginUser = null;
            return "/Login.xhtml?faces-redirect=true";
        } else {
            return "";
        }
    }

    public String getLoginUserName() {
        if (!this.getLoginUser().getValidrow()) {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.invalidateSession();
            try {
                externalContext.dispatch("/faces/Login.xhtml");
            } catch (IOException e) {

            }
            return "";
        } else {
            return this.getLoginUser().getUsername();
        }
    }
}
