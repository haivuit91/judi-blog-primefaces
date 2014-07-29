/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.olddao.UserDAO;
import model.olddao.service.UserDAOService;
import model.oldentities.User;

/**
 *
 * @author cong0_000
 */
@WebServlet(name = "ActiveAccount", urlPatterns = {"/active"})
public class ActiveAccount extends HttpServlet {

    private final UserDAOService USER_SERVICE = UserDAO.getInstance();

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
        int dem = 0;
        String idActive = request.getParameter("id");
//        response.getWriter().print(idActive);
        List<User> userList = USER_SERVICE.getAllUser();
        if (idActive != null) {
            for (User user : userList) {
                if (user.getActive() == 0) {
                    if (user.getIdActive().equals(idActive)) {
                        if (USER_SERVICE.restoreUser(user.getUserID())) {
                            dem++;
                        }
                    }
                }
            }
        }
        if (dem > 0) {
            response.sendRedirect(util.Constants.CONTEXT_PATH + "/module/active-success.jsf");
        } else {
            response.sendRedirect(util.Constants.CONTEXT_PATH + "/module/active-success.jsf");
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
