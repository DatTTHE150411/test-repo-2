/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 * @purpose Image CURD
 * @author TRANTATDAT
 */
public class ImageDao implements IMethod<Image> {

    @Override
    public List<Image> getAll() {
        String query = "SELECT * FROM Images";
        List<Image> ls = new ArrayList<>();

        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Image i = Image.builder()
                        .id(rs.getInt(1))
                        .product_id(rs.getInt(2))
                        .imgName(rs.getString(3))
                        .note(rs.getString(4))
                        .build();
                ls.add(i);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Image> getAllByProductId(int id) {
        String query = "SELECT * FROM Images where product_id = ?";
        List<Image> ls = new ArrayList<>();

        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Image i = Image.builder()
                        .id(rs.getInt(1))
                        .product_id(rs.getInt(2))
                        .imgName(rs.getString(3))
                        .note(rs.getString(4))
                        .build();
                ls.add(i);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Image getOne(int id) {
        String query = "SELECT * FROM Images WHERE id = ?";

        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Image i = Image.builder()
                        .id(rs.getInt(1))
                        .product_id(rs.getInt(2))
                        .imgName(rs.getString(3))
                        .note(rs.getString(4))
                        .build();
                return i;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public boolean add(Image obj) {
        String query = "Insert into Images values(?, ?, ?)";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, obj.getProduct_id());
            ps.setObject(2, obj.getImgName());
            ps.setObject(3, obj.getNote());
            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }

    @Override
    public boolean update(int id, Image obj) {
        String query = "Update Images set product_id = ?,"
                + " img_name = ?,"
                + " note = ? where id = ?";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(4, id);
            ps.setObject(1, obj.getProduct_id());
            ps.setObject(2, obj.getImgName());
            ps.setObject(3, obj.getNote());
            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }

    @Override
    public boolean remove(int id) {
        String query = "Delete from Images where id = ?";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, id);

            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }

}
