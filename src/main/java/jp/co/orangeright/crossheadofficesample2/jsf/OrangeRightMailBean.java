/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.orangeright.crossheadofficesample2.jsf;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author yosh
 */
@Named(value = "orangeRightMailBean")
@Dependent
public class OrangeRightMailBean {

    @Resource(mappedName = "java:jboss/mail/orange-right")
    private Session session;
    private final String from = "system@orange-right.co.jp";
    private String to = "system@orange-right.co.jp";
    private String sub = "テストメールorange-right";
    private String body = "Wildflyからメール送信。";

    /**
     * Creates a new instance of OrangeRightMailBean
     */
    public OrangeRightMailBean() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Asynchronous
    public String send(String mailto, String subject, String body) throws MessagingException {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailto));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException e) {
            throw (e);
        }
        return null;
    }

    @Asynchronous
    public String send() throws MessagingException {
        return this.send(this.getTo(), this.getSub(), this.getBody());
    }

    @Asynchronous
    public String mailSendButtom() throws MessagingException {
        return this.send(this.getTo(), this.getSub(), this.getBody());
    }
}
