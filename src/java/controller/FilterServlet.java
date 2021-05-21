/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BrandDao;
import dao.CategoryDao;
import dao.ProductDao;
import entity.Brand;
import entity.Category;
import entity.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TRANTATDAT
 */
@WebServlet(name = "FilterServlet", urlPatterns = {"/filter"})
public class FilterServlet extends HttpServlet {

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
            int flagOrder = 0;
            int pageIndex = 1;
            if (request.getParameter("flag") != null) {
                flagOrder = Integer.parseInt(request.getParameter("flag"));
            }

            int id = Integer.parseInt(request.getParameter("id"));
            int type = Integer.parseInt(request.getParameter("type"));
            List<Product> lsProduct = new ArrayList<>();
            List<Product> lsProductIncreasePrice = new ArrayList<>();
            List<Product> lsProductDecreasePrice = new ArrayList<>();

            if (type == 1) {
                lsProduct = new ProductDao().filterByBrand(id);
                lsProductIncreasePrice = new ProductDao().filterByBrand(id);
                lsProductDecreasePrice = new ProductDao().filterByBrand(id);
                lsProductIncreasePrice.sort(Comparator.comparingDouble(Product::getPrice));
                lsProductDecreasePrice.sort(Comparator.comparingDouble(Product::getPrice).reversed());
            } else {
                lsProduct = new ProductDao().filterByCategory(id);
                lsProductIncreasePrice = new ProductDao().filterByCategory(id);
                lsProductDecreasePrice = new ProductDao().filterByCategory(id);
                lsProductIncreasePrice.sort(Comparator.comparingDouble(Product::getPrice));
                lsProductDecreasePrice.sort(Comparator.comparingDouble(Product::getPrice).reversed());
            }
          
            // set attribute
            request.setAttribute("lsProduct", lsProduct);
            request.setAttribute("flagOrder", flagOrder);
            request.setAttribute("pageIndex", pageIndex);
            request.setAttribute("id", id);
            request.setAttribute("type", type);
            //order by increase price
            request.setAttribute("lsProductIncreasePrice", lsProductIncreasePrice);
            //order by decrease price
            request.setAttribute("lsProductDecreasePrice", lsProductDecreasePrice);            
            //gui du lieu qua file jsp
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception ex) {
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
