/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.orangeright.crossheadofficesample2.jsf;

import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.Csv;
import com.orangesignal.csv.handlers.StringArrayListHandler;
import com.orangesignal.csv.manager.CsvManager;
import com.orangesignal.csv.manager.CsvManagerFactory;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;
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
    private Integer wifiItemCount;
    private Integer wifihoshuItemCount;

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

    public String createWifiItem() {
        int count = 0;
        CsvConfig csvConfig = new CsvConfig();
        csvConfig.setSeparator(',');
        csvConfig.setQuoteDisabled(false);
        csvConfig.setQuote('"');
        csvConfig.setSkipLines(1);
        try {
            File csvFile = this.getFile("/tmp/auwifi.csv");
            List<String[]> csv = Csv.load(csvFile, csvConfig, new StringArrayListHandler());
            for (String[] cols : csv) {
                if (cols[0].length() == 0) {
                    break;
                }
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
                    detail.append("/****** au Wi-Fi 保守 ******/");
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
            csvFile.delete();
            JsfUtil.addSuccessMessage(count + "件登録しました。");
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public String createWifiHoshItem() {
        int count = 0;
        CsvConfig csvConfig = new CsvConfig();
        csvConfig.setSeparator(',');
        csvConfig.setQuoteDisabled(false);
        csvConfig.setQuote('"');
        csvConfig.setSkipLines(1);
        try {
            File csvFile = this.getFile("/tmp/w2wifi.csv");
            List<String[]> csv = Csv.load(csvFile, csvConfig, new StringArrayListHandler());
            for (String[] cols : csv) {
                if (cols[0].length() == 0) {
                    break;
                }
                ItemSearchCondition itemCondition = new ItemSearchCondition();
                itemCondition.setItemcd(cols[0]);
                List<Item> itemList = this.itemEjb.findAll(itemCondition);
                if (itemList.size() > 0) {

                } else {
                    this.itemController.prepareCreate();
                    this.itemController.getSelected().setItemcd(cols[0]);
                    this.itemController.getSelected().setCustomerid(this.customerEjb.find(6));
                    this.itemController.getSelected().setUserid(this.userEjb.find("ariie"));
                    StringBuilder detail = new StringBuilder();
                    detail.append("/****** w2 Wi-Fi (保守) ******/");
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("工事オーダーNo: ");
                    detail.append(cols[0]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("設置場所名: ");
                    detail.append(cols[4]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("住所: ");
                    detail.append(cols[6]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("案件名: ");
                    detail.append(cols[10]);
                    this.itemController.getSelected().setDetail(detail.toString());
                    this.itemController.getSelected().setMemo("");
                    this.itemController.create();
                    count++;
                }
            }
            csvFile.delete();
            JsfUtil.addSuccessMessage(count + "件登録しました。");
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    public Integer getWifiItemCount() {
        ItemSearchCondition iCon = new ItemSearchCondition();
        iCon.setCustomer(this.customerEjb.find(14541));
        Set<String> itemSet = new TreeSet<>();
        for (Item item : this.itemEjb.findAll(iCon)) {
            if (item.getItemcd().startsWith("tsk")) {
                itemSet.add(item.getItemcd());
            }
        }
        this.setWifiItemCount(itemSet.size());
        return wifiItemCount;
    }

    public void setWifiItemCount(Integer wifiItemCount) {
        this.wifiItemCount = wifiItemCount;
    }

    public Integer getWifihoshuItemCount() {
        ItemSearchCondition iCon = new ItemSearchCondition();
        iCon.setCustomer(this.customerEjb.find(6));
        Set<String> itemSet = new TreeSet<>();
        for (Item item : this.itemEjb.findAll(iCon)) {
            if (item.getItemcd().startsWith("order_work")) {
                itemSet.add(item.getItemcd());
            }
        }
        this.setWifihoshuItemCount(itemSet.size());
        return wifihoshuItemCount;
    }

    public void setWifihoshuItemCount(Integer wifihoshuItemCount) {
        this.wifihoshuItemCount = wifihoshuItemCount;
    }

    public String calcCsv() {
        CsvConfig csvConfig = new CsvConfig();
        csvConfig.setSeparator(',');
        csvConfig.setQuoteDisabled(false);
        csvConfig.setQuote('"');
        csvConfig.setSkipLines(1);
        try {
            File csvFile = this.getFile("/tmp/w2wifi.csv");
            List<String[]> csv = Csv.load(csvFile, csvConfig, new StringArrayListHandler());
            if (csv.size() > 0) {
                for (String[] cols : csv) {
                    if (cols[0].length() == 0) {
                        break;
                    }
                    this.itemController.prepareCreate();
                    this.itemController.getSelected().setItemcd(cols[0]);
                    this.itemController.getSelected().setCustomerid(this.customerEjb.find(6));
                    this.itemController.getSelected().setUserid(this.userEjb.find("ariie"));
                    StringBuilder detail = new StringBuilder();
                    detail.append("/****** w2 Wi-Fi (保守) ******/");
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("工事オーダーNo: ");
                    detail.append(cols[0]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("設置場所名: ");
                    detail.append(cols[4]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("住所: ");
                    detail.append(cols[6]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("案件名: ");
                    detail.append(cols[10]);
                    this.itemController.getSelected().setDetail(detail.toString());
                    this.itemController.getSelected().setMemo("");
                    this.itemController.create();
                }
            }
            csvFile.delete();
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    private File getFile(String tempFile) {
        try {
            this.dataFile.write(tempFile);

        } catch (IOException e) {

        }
        File file = new File(tempFile);
        return file;
    }
}
