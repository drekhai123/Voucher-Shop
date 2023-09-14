/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import users.UserDTO;
import voucher.VoucherDAO;
import voucher.VoucherDTO;

/**
 *
 * @author HP
 */
public class VoucherServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            String keyword = request.getParameter("keyword");
            


             //Check security
            HttpSession session = request.getSession(false);
            UserDTO currentUser = null;
            if(session != null){
                currentUser = (UserDTO)session.getAttribute("usersession");
            }
            log("Debug: " + currentUser);
            if (currentUser == null){
                log("Debug: Redirect to login page" + currentUser);            
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }   
            VoucherDAO dao = new VoucherDAO();
            if(action == null || action.equals("list") || (action.equals("Search") 
                    && (keyword == null || keyword.trim().isEmpty()))) {
                List<VoucherDTO> list = dao.list();
                request.setAttribute("list", list);
                RequestDispatcher rd = request.getRequestDispatcher("shop.jsp");
                rd.forward(request, response);
            } else if(action.equals("Search")){
                List<VoucherDTO> list = dao.searchVoucherByTitle(keyword);
                request.setAttribute("list", list);
                RequestDispatcher rd = request.getRequestDispatcher("shop.jsp");
                rd.forward(request, response);
            } else if(action.equals("Filter")){
                String selectedCategoryId = request.getParameter("category");
                Integer id = null;
                try{
                    id = Integer.parseInt(selectedCategoryId);
                } catch(NumberFormatException ex){
                    log(ex.getMessage());
                }
                List<VoucherDTO> filteredVoucherList = dao.listByCategoryID(id);
                request.setAttribute("list", filteredVoucherList);
                RequestDispatcher rd = request.getRequestDispatcher("shop.jsp");
                rd.forward(request, response);
            } else if(action.equals("details")){
                Integer id = null;
                try{
                    id = Integer.parseInt(request.getParameter("id"));
                } catch(NumberFormatException ex){
                    log(ex.getMessage());
                }
                VoucherDTO dto = null;
                if(id != null){
                    dto = dao.load(id);
                }
                request.setAttribute("object", dto);
                RequestDispatcher rd = request.getRequestDispatcher("voucherdetails.jsp");
                rd.forward(request, response);
            } else if ( action.equals("edit")){
                Integer id = null;
                try{
                    id = Integer.parseInt(request.getParameter("id"));
                } catch(NumberFormatException ex){
                    log(ex.getMessage());
                }
                VoucherDTO voucher = null;
                if(id != null){
                    voucher = dao.load(id);
                }
                request.setAttribute("object", voucher);
                request.setAttribute("nextaction", "update");
                RequestDispatcher rd = request.getRequestDispatcher("voucheredit.jsp");
                rd.forward(request, response);
               
            } else if ( action.equals("create")){
                request.setAttribute("nextaction", "insert");
                RequestDispatcher rd = request.getRequestDispatcher("voucheredit.jsp");
                rd.forward(request, response);
            } else if (action.equals("update")){
                    Integer id = null;
                    String title = null;
                    String des = null;
                    BigDecimal price = null;
                    String image = null;
                    try {
                        id = Integer.parseInt(request.getParameter("id"));
                        title = request.getParameter("title");
                        des = request.getParameter("description");
                        price = new BigDecimal(request.getParameter("price"));
                        image = request.getParameter("image");

                        VoucherDTO voucher = new VoucherDTO();
                        voucher.setId(id);
                        voucher.setTitle(title);
                        voucher.setDescription(des);
                        voucher.setPrice(price);
                        voucher.setImage(image);
                        

                        boolean isSuccess = dao.update(voucher);

                        if (isSuccess) {
                            request.setAttribute("err", "");
                            request.setAttribute("object", voucher);
                            RequestDispatcher rd = request.getRequestDispatcher("voucherdetails.jsp");
                            rd.forward(request, response);
                        } else {
                            request.setAttribute("err", "Update Fail!");
                            RequestDispatcher rd = request.getRequestDispatcher("voucheredit.jsp");
                            rd.forward(request, response);
                        }
                    } catch (NumberFormatException ex) {
                        log("Error at Update: " + ex.getMessage());
                        request.setAttribute("err", "Invalid ID");
                        RequestDispatcher rd = request.getRequestDispatcher("voucherdetails.jsp");
                        rd.forward(request, response);
                    }
                }
                else if (action.equals("insert")){
                    Integer id = null;
                    String title = null;
                    String des = null;
                    BigDecimal price = null;
                    String image = null;

                    try {
                        // Get the maximum existing ID and increment it by one
                        id = dao.maxId() + 1;
                        title = request.getParameter("title");
                        des = request.getParameter("description");
                        price = new BigDecimal(request.getParameter("des"));
                        image = request.getParameter("image");

                        if (id != null) {
                            VoucherDTO voucher = new VoucherDTO(id, title, des,price, image);
                            boolean isSuccess = dao.insert(voucher);

                            if (isSuccess) {
                                request.setAttribute("err", "Create Success!!!");
                                RequestDispatcher rd = request.getRequestDispatcher("voucherdetails.jsp");
                                rd.forward(request, response);
                            } else {
                                request.setAttribute("err", "Create Fail!");
                                RequestDispatcher rd = request.getRequestDispatcher("voucheredit.jsp");
                                rd.forward(request, response);
                            }
                        }
                    } catch (NumberFormatException ex) {    
                            log(ex.getMessage());
                    }
                    
                } else if (action.equals("delete")){
                
                Integer id = 0;
                try{
                    id = Integer.parseInt(request.getParameter("id"));      
                }catch (NumberFormatException ex){
                    log(ex.getMessage());
                }
                  
                if (id != null){
                    dao.delete(id);
                }

                List<VoucherDTO> list = dao.list();
                
                request.setAttribute("list", list);
                RequestDispatcher rd = request.getRequestDispatcher("shop.jsp");
                rd.forward(request, response);
            }
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
