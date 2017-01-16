/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.orangeright.crossheadofficesample2.jsf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.Part;
import jp.co.orangeright.crossheadofficesample2.ejb.CustomerFacade;
import jp.co.orangeright.crossheadofficesample2.ejb.ItemFacade;
import jp.co.orangeright.crossheadofficesample2.ejb.UserMFacade;
import jp.co.orangeright.crossheadofficesample2.entity.Item;
import jp.co.orangeright.crossheadofficesample2.jsf.item.ItemSearchCondition;
import jp.co.orangeright.crossheadofficesample2.jsf.util.JsfUtil;

/**
 *
 * @author yosh
 */
@Named(value = "itemFileInterfaceContoroller")
@SessionScoped
public class ItemFileInterfaceContoroller implements Serializable {

    private Part dataFile;
    @Inject
    private ItemController itemController;
    @EJB
    private ItemFacade itemEjb;
    @EJB
    private CustomerFacade customerEjb;
    @EJB
    private UserMFacade userEjb;

    /**
     * Creates a new instance of ItemFileInterfaceContoroller
     */
    public ItemFileInterfaceContoroller() {
    }

    public Part getDataFile() {
        return dataFile;
    }

    public void setDataFile(Part dataFile) {
        this.dataFile = dataFile;
    }

    public String auroraItemCreate() {
        int count = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(this.dataFile.getInputStream()));
            //1行目はヘッダなので処理しない。
            String line = br.readLine();
            while ((line = br.readLine()) != null || line.length() > 0) {
                line = line.replaceAll("\"", "");
                String[] cols = line.split(",");
                ItemSearchCondition itemCondition = new ItemSearchCondition();
                itemCondition.setItemcd(cols[0]);
                List<Item> itemList = this.itemEjb.findAll(itemCondition);
                if (itemList.size() > 0) {

                } else {
                    this.itemController.prepareCreate();
                    this.itemController.getSelected().setItemcd(cols[0]);
                    this.itemController.getSelected().setCustomerid(this.customerEjb.find(14541));
                    this.itemController.getSelected().setUserid(this.userEjb.find("ariie"));
                    StringBuilder detail = new StringBuilder();
                    detail.append("/****** auRora案件 ******/");
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("対応ID: ");
                    detail.append(cols[0]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("拠点名: ");
                    detail.append(cols[3]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("郵便番号: ");
                    detail.append(cols[4]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("住所: ");
                    detail.append(cols[5]);
                    detail.append(cols[6]);
                    detail.append(System.lineSeparator());
                    detail.append(cols[7]);
                    detail.append(System.lineSeparator());
                    detail.append(cols[8]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("電話番号: ");
                    detail.append(cols[9]);
                    this.itemController.getSelected().setDetail(detail.toString());
                    this.itemController.getSelected().setMemo("");
                    this.itemController.create();
                    count++;
                }
            }

        } catch (Exception e) {
            e.getLocalizedMessage();
            return null;
        }
        JsfUtil.addSuccessMessage(count + "件登録しました。");
        return "/index?faces-redirect=true";
    }
}
