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
@WebServlet(name = "RemoveCartServlet", urlPatterns = {"/remove-cart"})
public class RemoveCartServlet extends HttpServlet {

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
            int id = Integer.parseInt(request.getParameter("id"));
            HttpSession session = request.getSession();
            
            List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
            if (id == 0) {
                for (Cart cart : listCart) {
                    Product p = new ProductDao().getOne(cart.getId());
                    p.setQuantity(p.getQuantity() + cart.getQuantity());
                    new ProductDao().update(cart.getId(), p);
                }

                //xoa sach gio hang
                session.removeAttribute("listCart");
                session.removeAttribute("lsProductInStore");
                session.removeAttribute("totalMoney");
                session.removeAttribute("totalProduct");

                //remove all session(xoa sach toan bo session)
                //session.invalidate(); dang xuat, logout ...
            } else {                
                for (int i = 0; i < listCart.size(); ++i) {
                    Product product = new ProductDao().getOne(id);
                    if (listCart.get(i).getId() == id) {
                        product.setQuantity(product.getQuantity() + listCart.get(i).getQuantity());
                        new ProductDao().update(id, product);
                        listCart.remove(i);
                        break;
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
            }
            
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
