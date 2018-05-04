/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.orangeright.crossheadofficesample2.jsf;

import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.Csv;
import com.orangesignal.csv.handlers.StringArrayListHandler;
import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.Part;
import jp.co.orangeright.crossheadofficesample2.ejb.CustomerFacade;
import jp.co.orangeright.crossheadofficesample2.ejb.ItemFacade;
import jp.co.orangeright.crossheadofficesample2.ejb.UserMFacade;
import jp.co.orangeright.crossheadofficesample2.entity.Item;
import jp.co.orangeright.crossheadofficesample2.entity.UserM;
import jp.co.orangeright.crossheadofficesample2.jsf.item.ItemSearchCondition;
import jp.co.orangeright.crossheadofficesample2.jsf.util.JsfUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

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
    private Integer miraitoWifiItemCount;
    private Integer todenHomeItemCount;
    private Integer harmoItemCount;
    @Inject
    transient private OrangeRightMailBean mailBean;
    @Inject
    private MessageController messageController;
    @Inject
    private LoginController loginController;

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
            File csvFile = this.getFile("auwifi" + this.dataFile.getSubmittedFileName());
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
                    this.itemController.getSelected().setUserid(this.userEjb.find("mitanto"));
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
                    this.itemController.getSelected().setDetail(detail.toString());
                    this.itemController.getSelected().setMemo(cols[5].trim() + cols[6].trim());
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
    
    public Integer getWifiItemCount() {
        ItemSearchCondition iCon = new ItemSearchCondition();
        iCon.setCustomer(this.customerEjb.find(14541));
        iCon.setItemcd("tsk-");
        this.setWifiItemCount(this.itemEjb.countItemid(iCon));
        return this.wifiItemCount;
    }
    
    public void setWifiItemCount(Integer wifiItemCount) {
        this.wifiItemCount = wifiItemCount;
    }
    
    public String createWifiHoshItem() {
        int count = 0;
        CsvConfig csvConfig = new CsvConfig();
        csvConfig.setSeparator(',');
        csvConfig.setQuoteDisabled(false);
        csvConfig.setQuote('"');
        csvConfig.setSkipLines(1);
        try {
            File csvFile = this.getFile("w2wifi" + this.dataFile.getSubmittedFileName());
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
                    this.itemController.getSelected().setUserid(this.userEjb.find("mitanto"));
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
                    String jusho = cols[6].trim();
                    detail.append(jusho);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("案件名: ");
                    detail.append(cols[10]);
                    this.itemController.getSelected().setDetail(detail.toString());
                    this.itemController.getSelected().setMemo(jusho.substring(8, jusho.length()));
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
    
    public Integer getWifihoshuItemCount() {
        ItemSearchCondition iCon = new ItemSearchCondition();
        iCon.setCustomer(this.customerEjb.find(6));
        this.setWifihoshuItemCount(this.itemEjb.countItemid(iCon));
        return wifihoshuItemCount;
    }
    
    public void setWifihoshuItemCount(Integer wifihoshuItemCount) {
        this.wifihoshuItemCount = wifihoshuItemCount;
    }
    
    public String createMiraitoWifiItem() {
        int count = 0;
        CsvConfig csvConfig = new CsvConfig();
        csvConfig.setSeparator(',');
        csvConfig.setQuoteDisabled(false);
        csvConfig.setQuote('"');
        csvConfig.setSkipLines(1);
        try {
            File csvFile = this.getFile("miraitoauwifi" + this.dataFile.getSubmittedFileName());
            List<String[]> csv = Csv.load(csvFile, csvConfig, new StringArrayListHandler());
            for (String[] cols : csv) {
                if (cols[1].length() == 0) {
                    break;
                }
                ItemSearchCondition itemCondition = new ItemSearchCondition();
                itemCondition.setItemcd(cols[1]);
                List<Item> itemList = this.itemEjb.findAll(itemCondition);
                if (itemList.size() > 0) {
                    
                } else {
                    this.itemController.prepareCreate();
                    this.itemController.getSelected().setItemcd(cols[1]);
                    this.itemController.getSelected().setCustomerid(this.customerEjb.find(15820));
                    this.itemController.getSelected().setUserid(this.userEjb.find("mitanto"));
                    StringBuilder detail = new StringBuilder();
                    detail.append("/****** au Wi-Fi 保守 ******/");
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("対応ID: ");
                    detail.append(cols[1]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("拠点名: ");
                    detail.append(cols[4]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("住所: ");
                    detail.append(cols[5]);
                    detail.append(cols[6]);
                    detail.append(cols[7]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("依頼メモ: ");
                    detail.append(System.lineSeparator());
                    detail.append(cols[9]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
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
    
    public Integer getMiraitoWifiItemCount() {
        ItemSearchCondition iCon = new ItemSearchCondition();
        iCon.setCustomer(this.customerEjb.find(15820));
        iCon.setItemcd("tsk-");
        this.setMiraitoWifiItemCount(this.itemEjb.countItemid(iCon));
        return this.miraitoWifiItemCount;
    }
    
    public void setMiraitoWifiItemCount(Integer miraitoWifiItemCount) {
        this.miraitoWifiItemCount = miraitoWifiItemCount;
    }
    
    public String createTodenHomeItem() {
        return this.createTodenHomeItemCsv();
    }
    
    public String createTodenHomeItemExcel() {
        int count = 0;
        try {
            File excelFile = this.getFile("todenhome" + this.dataFile.getSubmittedFileName());
            Workbook workbook = WorkbookFactory.create(excelFile);
            Sheet sheet = workbook.getSheet("Sheet1");
            for (int rowNumber = sheet.getFirstRowNum() + 1; rowNumber <= sheet.getLastRowNum(); rowNumber++) {
                String itemCd = this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(0));
                ItemSearchCondition itemCondition = new ItemSearchCondition();
                itemCondition.setItemcd(itemCd);
                List<Item> itemList = this.itemEjb.findAll(itemCondition);
                if (itemList.size() > 0) {
                    //登録があったら何もしない。
                } else {
                    this.itemController.prepareCreate();
                    this.itemController.getSelected().setItemcd(itemCd);
                    this.itemController.getSelected().setCustomerid(this.customerEjb.find(31925));
                    this.itemController.getSelected().setUserid(this.userEjb.find("mitanto"));
                    StringBuilder detail = new StringBuilder();
                    detail.append("/****** 東電　おうちで安心 ******/");
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("契約番号: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(0)));
                    detail.append(System.lineSeparator());
                    detail.append("枝番: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(1)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("エネルギーセンサー: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(13)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("スマートホームハブ: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(14)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("スマートタグ: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(15)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("マルチセンサーブリッジ: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(16)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("マルチセンサー子機: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(17)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("マルチファンクションライト_ライト: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(18)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("マルチファンクションライト_ユニット: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(19)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("タブレット: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(20)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("WiFiルーター: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(21)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("IoTサービスNo: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(57)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("設置宅ID: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(58)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("工事担当者アカウント: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(59)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("工事担当者パスワード: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(60)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("Notionアカウント: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(61)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("Notionパスワード: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(62)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("契約ステータス: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(79)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("契約者氏名_カナ: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(80)));
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(81)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("契約者氏名_漢字: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(82)));
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(83)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("性別: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(84)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("契約者生年月日: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(85)));
                    detail.append("/");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(86)));
                    detail.append("/");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(87)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("設置先情報_郵便番号: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(107)));
                    detail.append(System.lineSeparator());
                    detail.append("設置先情報_住所: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(108)));
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(109)));
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(110)));
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(111)));
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(112)));
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(113)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("設置先情報_連絡先: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(114)));
                    detail.append("-");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(115)));
                    detail.append("-");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(116)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("設置先情報_連絡先_携帯番号: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(117)));
                    detail.append("-");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(118)));
                    detail.append("-");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(119)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("備考: ");
                    detail.append(this.getTodenExcelCellValue(sheet.getRow(rowNumber).getCell(120)));
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    this.itemController.getSelected().setDetail(detail.toString());
                    this.itemController.getSelected().setMemo("");
                    this.itemController.create();
                    count++;
                }
            }
            excelFile.delete();
            JsfUtil.addSuccessMessage(count + "件登録しました。");
        } catch (Exception e) {
            return null;
        }
        return null;
    }
    
    private String getTodenExcelCellValue(Cell cell) {
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_BLANK:
                return "";
            case Cell.CELL_TYPE_NUMERIC:
                return Integer.toString(Double.valueOf(cell.getNumericCellValue()).intValue());
            default:
                return cell.getStringCellValue();
        }
    }
    
    public String createTodenHomeItemCsv() {
        int count = 0;
        CsvConfig csvConfig = new CsvConfig();
        csvConfig.setSeparator(',');
        csvConfig.setQuoteDisabled(false);
        csvConfig.setQuote('"');
        csvConfig.setSkipLines(1);
        try {
            File csvFile = this.getFile("todenhome" + this.dataFile.getSubmittedFileName());
            List<String[]> csv = Csv.load(csvFile, "Windows-31J", csvConfig, new StringArrayListHandler());
            for (String[] cols : csv) {
                if (cols[0].length() == 0) {
                    break;
                }
                String itemCd = cols[0] + "-" + cols[1];
                ItemSearchCondition itemCondition = new ItemSearchCondition();
                itemCondition.setItemcd(itemCd);
                List<Item> itemList = this.itemEjb.findAll(itemCondition);
                if (itemList.size() > 0) {
                    //登録があったら何もしない。
                    JsfUtil.addErrorMessage(itemCd + " is exist.");
                } else {
                    this.itemController.prepareCreate();
                    this.itemController.getSelected().setItemcd(itemCd);
                    this.itemController.getSelected().setCustomerid(this.customerEjb.find(31925));
                    UserM user = this.validUser(cols[44].trim(), itemCd);
                    this.itemController.getSelected().setUserid(this.userEjb.find(user.getUserid()));
                    StringBuilder detail = new StringBuilder();
                    detail.append("/****** 東電　おうちで安心 ******/");
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("契約番号: ");
                    detail.append(cols[0]);
                    detail.append(System.lineSeparator());
                    detail.append("枝番: ");
                    detail.append(cols[1]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("エネルギーセンサー: ");
                    detail.append(cols[13]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("スマートホームハブ: ");
                    detail.append(cols[14]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("スマートタグ: ");
                    detail.append(cols[15]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("マルチセンサーブリッジ: ");
                    detail.append(cols[16]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("マルチセンサー子機: ");
                    detail.append(cols[17]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("マルチファンクションライト_ライト: ");
                    detail.append(cols[18]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("マルチファンクションライト_ユニット: ");
                    detail.append(cols[19]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("タブレット: ");
                    detail.append(cols[20]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("WiFiルーター: ");
                    detail.append(cols[21]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("IoTサービスNo: ");
                    detail.append(cols[57]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("設置宅ID: ");
                    detail.append(cols[58]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("工事担当者アカウント: ");
                    detail.append(cols[59]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("工事担当者パスワード: ");
                    detail.append(cols[60]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("Notionアカウント: ");
                    detail.append(cols[61]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("Notionパスワード: ");
                    detail.append(cols[62]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("ロックアカウント: ");
                    detail.append(cols[63]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("ロックパスワード: ");
                    detail.append(cols[64]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("契約ステータス: ");
                    detail.append(cols[81]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("契約者氏名_カナ: ");
                    detail.append(cols[82]);
                    detail.append(cols[83]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("契約者氏名_漢字: ");
                    detail.append(cols[84]);
                    detail.append(cols[85]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("性別: ");
                    detail.append(cols[86]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("契約者生年月日: ");
                    detail.append(cols[87]);
                    detail.append("/");
                    detail.append(cols[88]);
                    detail.append("/");
                    detail.append(cols[89]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("設置先住所区分: ");
                    detail.append(cols[104]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("設置先情報_郵便番号: ");
                    detail.append(cols[109]);
                    detail.append(System.lineSeparator());
                    detail.append("設置先情報_住所: ");
                    detail.append(cols[110]);
                    detail.append(cols[111]);
                    detail.append(cols[112]);
                    detail.append(cols[113]);
                    detail.append(cols[114]);
                    detail.append(cols[115]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("設置先情報_連絡先: ");
                    detail.append(cols[116]);
                    detail.append("-");
                    detail.append(cols[117]);
                    detail.append("-");
                    detail.append(cols[118]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("設置先情報_連絡先_携帯番号: ");
                    detail.append(cols[119]);
                    detail.append("-");
                    detail.append(cols[120]);
                    detail.append("-");
                    detail.append(cols[121]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("備考: ");
                    detail.append(cols[122]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    this.itemController.getSelected().setDetail(detail.toString());
                    this.itemController.getSelected().setMemo(cols[111] + "アポ1「」アポ2「」アポ3「」");
                    this.itemController.create();
                    String subject = this.loginController.getLoginUserName() + "さんからの伝言メモ";
                    String body = "下記案件が登録になりました。" + System.getProperty("line.separator")
                            + System.getProperty("line.separator")
                            + detail.toString();
                    String to = user.getMobilemail();
                    this.mailBean.setSub(subject);
                    this.mailBean.setBody(body);
                    this.mailBean.setTo(to);
                    if (user.getMobilemail() != null && !"".equals(user.getMobilemail())) {
                        this.mailBean.send();
                    }
                    if (!"mitanto".equals(user.getUserid())) {
                        this.messageController.prepareCreate();
                        this.messageController.getSelected().setSubject(subject);
                        this.messageController.getSelected().setBody(body);
                        this.messageController.getSelected().setMessageto(user.getUserid());
                        this.messageController.getSelected().setMessagefrom("jimu");
                        this.messageController.getSelected().setMessagetime(new Date());
                        this.messageController.getSelected().setReadst(false);
                        this.messageController.getSelected().setValidrow(true);
                        this.messageController.getSelected().setAddcode(this.loginController.getLoginUser().getUserid());
                        this.messageController.getSelected().setAdddate(new Date());
                        this.messageController.getSelected().setAddprogram(this.getClass().getName());
                        this.messageController.getSelected().setUpdatecode(this.loginController.getLoginUser().getUserid());
                        this.messageController.getSelected().setUpdatedate(new Date());
                        this.messageController.getSelected().setUpdateprogram(this.getClass().getName());
                        this.messageController.create();
                    }
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
    
    public Integer getTodenHomeItemCount() {
        ItemSearchCondition iCon = new ItemSearchCondition();
        iCon.setCustomer(this.customerEjb.find(31925));
        iCon.setItemcd("CO");
        this.setTodenHomeItemCount(this.itemEjb.countItemid(iCon));
        return todenHomeItemCount;
    }
    
    public void setTodenHomeItemCount(Integer todenHomeItemCount) {
        this.todenHomeItemCount = todenHomeItemCount;
    }
    
    private boolean isTantofuriwake(String userid) {
        if (userid == null || userid.equals("")) {
            return false;
        } else {
            UserM user = this.userEjb.find(userid);
            return user != null;
        }
    }
    
    private UserM validUser(String userid, String itemCd) {
        UserM user = this.userEjb.find(userid);
        if (user == null) {
                    JsfUtil.addErrorMessage(itemCd + ": " + userid + " is not exist.");
            return this.userEjb.find("mitanto");
        } else {
            return user;
        }
    }
    
    public String createHarmoItem() {
        int count = 0;
        CsvConfig csvConfig = new CsvConfig();
        csvConfig.setSeparator(',');
        csvConfig.setQuoteDisabled(false);
        csvConfig.setQuote('"');
        csvConfig.setSkipLines(3);
        try {
            File csvFile = this.getFile("harmo" + this.dataFile.getSubmittedFileName());
            List<String[]> csv = Csv.load(csvFile, csvConfig, new StringArrayListHandler());
            for (String[] cols : csv) {
                if (cols[1].length() == 0) {
                    break;
                }
                String itemCd = cols[1];
                ItemSearchCondition itemCondition = new ItemSearchCondition();
                itemCondition.setItemcd(itemCd);
                List<Item> itemList = this.itemEjb.findAll(itemCondition);
                if (itemList.size() > 0) {
                    //登録があったら何もしない。
                } else {
                    this.itemController.prepareCreate();
                    this.itemController.getSelected().setItemcd(itemCd);
                    this.itemController.getSelected().setCustomerid(this.customerEjb.find(50227));
                    this.itemController.getSelected().setUserid(this.userEjb.find("mitanto"));
                    StringBuilder detail = new StringBuilder();
                    detail.append("/****** harmo薬局 ******/");
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("案件番号: ");
                    detail.append(cols[1]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("契約者・薬局・病院名: ");
                    detail.append(cols[2]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("PCメーカー: ");
                    detail.append(cols[4]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("PC型番: ");
                    detail.append(cols[5]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("タブレット枚数: ");
                    detail.append(cols[6]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("導入するタブレットの枚数: ");
                    detail.append(cols[7]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("設置先郵便番号: ");
                    detail.append(cols[9]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("設置先住所: ");
                    detail.append(cols[10]);
                    detail.append(cols[11]);
                    detail.append(cols[12]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("電話番号: ");
                    detail.append(cols[13]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("FAX番号: ");
                    detail.append(cols[14]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    detail.append("備考: ");
                    detail.append(cols[16]);
                    detail.append(System.lineSeparator());
                    detail.append(System.lineSeparator());
                    this.itemController.getSelected().setDetail(detail.toString());
                    this.itemController.getSelected().setMemo("");
                    this.itemController.create();
                    count++;
                }
            }
            csvFile.delete();
            JsfUtil.addSuccessMessage(count + "件登録しました。");
            
        } catch (Exception e) {
            String msg = e.getLocalizedMessage();
            return null;
        }
        return null;
    }
    
    public Integer getHarmoItemCount() {
        ItemSearchCondition iCon = new ItemSearchCondition();
        iCon.setCustomer(this.customerEjb.find(50227));
        this.setHarmoItemCount(this.itemEjb.countItemid(iCon));
        return harmoItemCount;
    }
    
    public void setHarmoItemCount(Integer harmoItemCount) {
        this.harmoItemCount = harmoItemCount;
    }
    
    private File getFile(String tempFileName) {
        File file = new File(System.getProperty("java.io.tmpdir"), tempFileName);
        String tempFile = file.getAbsolutePath();
        try {
            this.dataFile.write(tempFile);
            
        } catch (IOException e) {
            
        }
        return file;
    }
}
