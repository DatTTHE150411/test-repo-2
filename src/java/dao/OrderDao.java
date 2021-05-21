/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jdbc.SQLServerConnection;

/**
 *
 * @author TRANTATDAT
 */
public class OrderDao {

    //Get id of an insert order
    public int addOrder(Order order) {

        String query = "Insert into Orders values(?, ?, ?, ?, ?, ?)";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, order.getName());
            ps.setObject(2, order.getMobile());
            ps.setObject(3, order.getAddress());
            ps.setObject(4, order.getTotalMoney());
            ps.setObject(5, order.getNote());
            ps.setObject(6, order.getStatus());

            check = ps.executeUpdate();
            if (check > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return 0;
    }
    
    public boolean removeOrder(int orderId) {
        String query = "Delete from Orders where id = ?";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, orderId);

            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }
}
