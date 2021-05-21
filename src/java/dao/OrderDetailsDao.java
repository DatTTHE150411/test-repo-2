/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Cart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 *
 * @author TRANTATDAT
 */
public class OrderDetailsDao {

    public boolean add(List<Cart> listCart, int orderId) {

        String query = "Insert into Order_Details values(?, ?, ?, ?, ?)";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            for (Cart c : listCart) {
                ps.setObject(1, orderId);
                ps.setObject(2, c.getId());
                ps.setObject(3, c.getName());
                ps.setObject(4, c.getPrice());
                ps.setObject(5, c.getQuantity());
                //add nhieu record trong 1 lan mo cong
                ps.addBatch();
            }

            ps.executeBatch();
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return false;
    }
}
