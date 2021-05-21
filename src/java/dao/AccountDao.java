/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Account;
import entity.Brand;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jdbc.SQLServerConnection;

/**
 *
 * @author TRANTATDAT
 */
public class AccountDao {  

    public Account login(String email, String password) {
        String query = "SELECT * FROM Account WHERE Email = ? and Password = ?";
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, email);
            ps.setObject(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account ac = Account.builder()
                        .id(rs.getInt(1))
                        .email(rs.getString(2))
                        .password(rs.getString(3))
                        .activeCode(rs.getString(4))
                        .role(rs.getInt(5))
                        .status(rs.getInt(6))
                        .build();
                return ac;
            }
        } catch (Exception e) {
        }
        return null;
    }
}
