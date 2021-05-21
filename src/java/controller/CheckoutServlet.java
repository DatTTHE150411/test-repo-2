/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OrderDao;
import dao.OrderDetailsDao;
import entity.Cart;
import entity.Order;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TRANTATDAT
 */
@WebServlet(name = "CneckoutServlet", urlPatterns = {"/check-out"})
public class CheckoutServlet extends HttpServlet {

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
        try {
            String name = request.getParameter("name");
            String mobile = request.getParameter("mobile");
            String address = request.getParameter("address");
            String note = request.getParameter("note");

            HttpSession session = request.getSession();
            session.removeAttribute("lsProductInStore");

            //add order
            OrderDao od = new OrderDao();
            int orderId = od.addOrder(
                    Order.builder()
                            .name(name)
                            .mobile(mobile)
                            .address(address)
                            .note(note)
                            .totalMoney((double) session.getAttribute("totalMoney"))
                            .status(1)
                            .build()
            );

            //add order details
            if (orderId > 0) {
                OrderDetailsDao odd = new OrderDetailsDao();
                boolean result = odd.add((List<Cart>) session.getAttribute("listCart"), orderId);               
                if (result) {
                    //order sucessful, remove cart, totalmoney, totalProduct
                    session.removeAttribute("listCart");
                    session.removeAttribute("totalMoney");
                    session.removeAttribute("totalProduct");

                    response.sendRedirect("thanks.jsp");
                } else {
                    //remove order
                    //write code here
                    od.removeOrder(orderId);
                    response.sendRedirect("checkout.jsp");
                }
            } else {
                response.sendRedirect("checkout.jsp");
            }

        } catch (Exception e) {
            response.sendRedirect("error.jsp");
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
