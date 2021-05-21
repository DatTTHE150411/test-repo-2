/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductDao;
import entity.Cart;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/update-cart"})
public class UpdateCartServlet extends HttpServlet {

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
            HttpSession session = request.getSession();

            int id = Integer.parseInt(request.getParameter("id"));
            int flag = Integer.parseInt(request.getParameter("flag"));
            List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");

            Product product = new ProductDao().getOne(id);

            for (Cart cart : listCart) {
                if (cart.getId() == id) {
                    if (flag == 1) {
                        cart.setQuantity(cart.getQuantity() - 1);
                        product.setQuantity(product.getQuantity() + 1);
                        new ProductDao().update(id, product);
                        break;
                    } else {                       
                        cart.setQuantity(cart.getQuantity() + 1);
                        product.setQuantity(product.getQuantity() - 1);
                        new ProductDao().update(id, product);
                        break;
                    }
                }
            }

            int number = 0;
            double totalMoney = 0;
            for (Cart cart : listCart) {
                number += cart.getQuantity();
                totalMoney += cart.getQuantity() * cart.getPrice();
            }

            session.setAttribute("totalProduct", number);
            session.setAttribute("totalMoney", totalMoney);
            session.setAttribute("listCart", listCart);

            response.sendRedirect("cart.jsp");
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
