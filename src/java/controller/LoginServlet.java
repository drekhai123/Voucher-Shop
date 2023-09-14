/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import users.UserDAO;
import users.UserDTO;

/**
 *
 * @author ASUS
 */
public class LoginServlet extends HttpServlet {

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
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("action");
            String signupAction = request.getParameter("signupAction");
            
            UserDAO dao = new UserDAO();
            if (action != null && action.equals("logout")) {
                // Log out the user
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                response.sendRedirect("homepage.html");
            } else if (action != null && action.equals("Sign Up")) {
                    // User is trying to sign up
                    String username = request.getParameter("user");

                    // Check if the username already exists in the database
                    if (dao.doesUsernameExist(username)) {
                        // Username already exists, show an error message
                        request.setAttribute("error", "Username already exists. Please choose a different username.");
                        RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
                        rd.forward(request, response);
                    } else {
                        // Username is unique, proceed with sign-up
                        Integer id = null;
                        String email = request.getParameter("email");
                        String password = request.getParameter("password");

                        try {
                            id = dao.maxId() + 1;

                            if (id != null) {
                                UserDTO user = new UserDTO(id, username, email, password);
                                boolean isSuccess = dao.insert(user);

                                if (isSuccess) {
                                    // Sign-up successful, forward to login page
                                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                                    rd.forward(request, response);
                                } else {
                                    // Sign-up failed, show an error message
                                    request.setAttribute("error", "Sign-up failed. Please try again.");
                                    RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
                                    rd.forward(request, response);
                                }
                            }
                        } catch (NumberFormatException ex) {
                            log(ex.getMessage());
                        }
                    }
                } else {
                // User is trying to log in
                String username = request.getParameter("user");
                String password = request.getParameter("password");
                   UserDTO user = dao.login(username, password);
                       if(user != null){
                           HttpSession session = request.getSession(true);
                           session.setAttribute("usersession", user);
                           response.sendRedirect("homepage.jsp");   
                       } else{
                           request.setAttribute("error", "wrong username or password");
                           RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                           rd.forward(request, response);
                       }
            }

//           String action = request.getParameter("action");
//           String username = request.getParameter("user");
//           String password = request.getParameter("password");
//           
//           if(action != null  && action.equals("logout")){
//               HttpSession session = request.getSession(false);
//               
//               if(session != null){
//                   session.invalidate();
//               }
//               response.sendRedirect("homepage.html");
//               } else{
//                   log("Debug user: " + username + " " + password);
//                   if(username == null && password == null){
//                       log("Debug user: Go to login" + username + " " + password);
//                       RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
//                       rd.forward(request, response);
//                   } else{
//                       log("Debug user: Go to here" + username + " " + password);
//                       UserDAO dao = new UserDAO();
//                       UserDTO user = dao.login(username, password);
//                       if(user != null){
//                           HttpSession session = request.getSession(true);
//                           session.setAttribute("usersession", user);
//                           response.sendRedirect("homepage.jsp");   
//                       } else{
//                           request.setAttribute("error", "wrong username or password");
//                           RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
//                           rd.forward(request, response);
//                       }
//                   }
//               }  
//        }
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
