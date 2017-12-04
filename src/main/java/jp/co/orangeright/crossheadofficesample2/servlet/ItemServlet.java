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
import jp.co.orangeright.crossheadofficesample2.ejb.KeihiFacade;
import jp.co.orangeright.crossheadofficesample2.entity.Item;
import jp.co.orangeright.crossheadofficesample2.entity.Keihi;
import jp.co.orangeright.crossheadofficesample2.jsf.ItemController;
import jp.co.orangeright.crossheadofficesample2.jsf.LoginController;

/**
 *
 * @author yosh
 */
@WebServlet(name = "ItemServlet", urlPatterns = {"/item/ItemServlet"})
public class ItemServlet extends HttpServlet {

    @EJB
    private ItemFacade itemEjb;
    @EJB
    private CustomerFacade customerEjb;
    @EJB
    private KeihiFacade keihiEjb;
    @Inject
    private LoginController loginController;
    @Inject
    private ItemController itemContoroller;

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
                response.setContentType("application/force-download");
                response.setHeader("Content-Disposition", "attachment; filename*=\"" + URLEncoder.encode("item.csv", "UTF-8") + "\"");

                CsvConfig csvConfig = new CsvConfig();
                csvConfig.setSeparator(',');
                csvConfig.setQuoteDisabled(false);
                csvConfig.setQuote('"');
                List<String[]> csvColumns = new ArrayList<>();
                int accountingCount = this.itemEjb.count(this.itemContoroller.getCondition());
                String[] header = {"ItemID",
                    "案件コード",
                    "案件追加日",
                    "提携先",
                    "作業者",
                    "訪問予定",
                    "メモ",
                    "交通費",
                    "送料",};
                csvColumns.add(header);

                int PAGE_SIZE = 10;
                for (int i = 0; i < accountingCount; i += PAGE_SIZE + 1) {
                    for (Item item : this.itemEjb.findRange(new int[]{i, i + PAGE_SIZE}, this.itemContoroller.getCondition())) {
                        Keihi keihiTemp = this.getKeihiInstance(item.getItemid());
                        String[] temp = {item.getItemid().toString(),
                            item.getItemcd(),
                            new SimpleDateFormat("yyyy/MM/dd").format(item.getAdddate()),
                            item.getCustomerid().getCustomername(),
                            item.getUserid().getUsername(),
                            item.getScheid() == null ? "" : new SimpleDateFormat("yyyy/MM/dd").format(item.getScheid().getDatefrom()),
                            item.getMemo(),
                            keihiTemp.getKotsuhi().toString(),
                            keihiTemp.getNidukuriunchin().toString(),};

                        csvColumns.add(temp);
                    }

                }

                Csv.save(csvColumns, out, "Windows-31J", csvConfig, new StringArrayListHandler());

            } catch (IOException e) {
                response.sendRedirect("/CrossHeadOfficeSample2/faces/index.xhtml");

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

    private Keihi getKeihiInstance(Integer id) {
        Keihi keihi = this.keihiEjb.find(id);
        if (keihi == null) {
            keihi = new Keihi();
            keihi.setItemid(id);
            keihi.setKotsuhi(0);
            keihi.setNidukuriunchin(0);
        }
        return keihi;
    }
}
