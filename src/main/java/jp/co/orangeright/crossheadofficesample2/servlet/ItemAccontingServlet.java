/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.orangeright.crossheadofficesample2.servlet;

import com.orangesignal.csv.Csv;
import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.handlers.StringArrayListHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jp.co.orangeright.crossheadofficesample2.ejb.CustomerFacade;
import jp.co.orangeright.crossheadofficesample2.ejb.ItemFacade;
import jp.co.orangeright.crossheadofficesample2.entity.Item;
import jp.co.orangeright.crossheadofficesample2.jsf.LoginController;
import jp.co.orangeright.crossheadofficesample2.jsf.item.ItemSearchCondition;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 *
 * @author yosh
 */
@WebServlet(name = "ItemAccontingServlet", urlPatterns = {"/item/ItemAccontingServlet"})
public class ItemAccontingServlet extends HttpServlet {

    @EJB
    private ItemFacade itemEjb;
    @EJB
    private CustomerFacade customerEjb;
    @Inject
    private LoginController loginController;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (this.loginController.getStatus()) {
            try (OutputStream out = response.getOutputStream()) {
                ItemSearchCondition itemCondition = new ItemSearchCondition();
                itemCondition.setAccounting(Boolean.FALSE);
                itemCondition.setWorked(Boolean.TRUE);

                CsvConfig csvConfig = new CsvConfig();
                csvConfig.setSeparator(',');
                csvConfig.setQuoteDisabled(false);
                csvConfig.setQuote('"');
                List<String[]> csvColumns = new ArrayList<>();
                int accountingCount = this.itemEjb.count(itemCondition);

                int PAGE_SIZE = 10;
                for (int i = 0; i < accountingCount; i += PAGE_SIZE + 1) {
                    for (Item item : this.itemEjb.findRange(new int[]{i, i + PAGE_SIZE}, itemCondition)) {
                        String[] temp = {item.getItemid().toString(),
                            item.getItemcd(),
                            new SimpleDateFormat("yyyy/MM/dd").format(item.getAdddate()),
                            item.getCustomerid().getCustomername(),
                            item.getUserid().getUsername(),
                            item.getScheid() == null ? "" : new SimpleDateFormat("yyyy/MM/dd").format(item.getScheid().getDatefrom()),
                            item.getMemo(),
                            item.getDirectpayment() ? "直収" : "本部請求",
                            item.getDetail().contains("○受付窓口名：")
                            ? item.getDetail().substring(item.getDetail().indexOf("○受付窓口名：") + "○受付窓口名：".length(), item.getDetail().indexOf("○", item.getDetail().indexOf("○受付窓口名：") + "○受付窓口名：".length()) - 2)
                            : "",
                            item.getDetail().contains("○名称：")
                            ? item.getDetail().substring(item.getDetail().indexOf("○名称：") + "○名称：".length(), item.getDetail().indexOf("○", item.getDetail().indexOf("○名称：") + "○名称：".length()) - 2)
                            : ""
                        };

                        csvColumns.add(temp);
                    }

                }

                response.setContentType("application/force-download");
                response.setHeader("Content-Disposition", "attachment; filename*=\"" + URLEncoder.encode("itemaccounting.csv", "UTF-8") + "\"");
                Csv.save(csvColumns, out, "UTF-8", csvConfig, new StringArrayListHandler());

            }
        } else {
            response.sendRedirect("/CrossHeadOfficeSample2/faces/index.xhtml");
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
