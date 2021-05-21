/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jdbc.SQLServerConnection;

/**
 * @purpose Product CURD
 * @author TRANTATDAT
 */
public class ProductDao implements IMethod<Product> {

    //curd cua Product
    //duy tri, mo ket noi
    //private Connection con;
    //ghi cau lenh
    //private PreparedStatement ps;
    //luu ket qua
    //private ResultSet rs;
    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM Products";
        List<Product> ls = new ArrayList<>();
        //Java 7 ve truoc
//        try {
//            con = SQLServerConnection.getConnection();
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();

        //try with resources
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .brandId(rs.getInt(2))
                        .categoryId(rs.getInt(3))
                        .name(rs.getString(4))
                        .price(rs.getDouble(5))
                        .quantity(rs.getInt(6))
                        .imgName(rs.getString(7))
                        .description(rs.getString(8))
                        .note(rs.getString(9))
                        .status(rs.getInt(10))
                        .build();
                ls.add(p);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
//        } finally {
//            
//// Java 7 tro ve truoc de dong cong, don dep tai nguyen
////            if(ps!=null) {
////                try {
////                    ps.close();
////                } catch (SQLException ex) { }
////            }
////            if(con!=null) {
////                try {
////                    con.close();
////                } catch (SQLException ex) { }
////            }
//
//            
//        }
            return null;
        }
    }

    @Override
    public Product getOne(int id) {
        //ko insert truc tiep gia tri id vao query(ko duoc cong chuoi truc tiep)
        //de tranh sql injection
        String query = "SELECT * FROM Products where id = ?";

        //VD
        //String query = "SELECT * FROM product where id = ? and name = ?";
        //=> ps.setInt(1, id); 
        //   ps.setString(2, "Balo1");
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            //dua id vao cau query, convert sang string, tranh sql injection,
            //1 la vi tri dau hoi, cac dau hoi dem tu 1
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .brandId(rs.getInt(2))
                        .categoryId(rs.getInt(3))
                        .name(rs.getString(4))
                        .price(rs.getDouble(5))
                        .quantity(rs.getInt(6))
                        .imgName(rs.getString(7))
                        .description(rs.getString(8))
                        .note(rs.getString(9))
                        .status(rs.getInt(10))
                        .build();
                return p;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }
    
    public List<Product> getThreeNew() {
        String query = "SELECT top (3) * from Products ORDER BY id DESC";
        List<Product> ls = new ArrayList<>();
        
        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .brandId(rs.getInt(2))
                        .categoryId(rs.getInt(3))
                        .name(rs.getString(4))
                        .price(rs.getDouble(5))
                        .quantity(rs.getInt(6))
                        .imgName(rs.getString(7))
                        .description(rs.getString(8))
                        .note(rs.getString(9))
                        .status(rs.getInt(10))
                        .build();
                ls.add(p);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean add(Product obj) {
        String query = "Insert into Products values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, obj.getBrandId());
            ps.setObject(2, obj.getCategoryId());
            ps.setObject(3, obj.getName());
            ps.setObject(4, obj.getPrice());
            ps.setObject(5, obj.getQuantity());
            ps.setObject(6, obj.getImgName());
            ps.setObject(7, obj.getDescription());
            ps.setObject(8, obj.getNote());
            ps.setObject(9, obj.getStatus());
            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }

    @Override
    public boolean update(int id, Product obj) {
        String query = "Update Products set brand_id = ?,"
                + " category_id = ?,"
                + " name = ?,"
                + " price = ?,"
                + " quantity = ?,"
                + " img_name = ?,"
                + " description = ?,"
                + " note = ?,"
                + " status = ? where id = ?";
        int check = 0;

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(10, id);
            ps.setObject(1, obj.getBrandId());
            ps.setObject(2, obj.getCategoryId());
            ps.setObject(3, obj.getName());
            ps.setObject(4, obj.getPrice());
            ps.setObject(5, obj.getQuantity());
            ps.setObject(6, obj.getImgName());
            ps.setObject(7, obj.getDescription());
            ps.setObject(8, obj.getNote());
            ps.setObject(9, obj.getStatus());
            check = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return check > 0;
    }

    @Override
    public boolean remove(int id) {
        String query = "Delete from Products where id = ?";
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

    public List<Product> searchByName(String text) {
        String query = "SELECT * FROM Products WHERE name LIKE ?";
        List<Product> ls = new ArrayList<>();
        String searchText = "%" + text + "%";

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, searchText);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .brandId(rs.getInt(2))
                        .categoryId(rs.getInt(3))
                        .name(rs.getString(4))
                        .price(rs.getDouble(5))
                        .quantity(rs.getInt(6))
                        .imgName(rs.getString(7))
                        .description(rs.getString(8))
                        .note(rs.getString(9))
                        .status(rs.getInt(10))
                        .build();
                ls.add(p);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<Product> filterByBrand(int id) {
        String query = "SELECT * FROM Products WHERE brand_id LIKE ?";
        List<Product> ls = new ArrayList<>();
        String searchText = "%" + id + "%";

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, searchText);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .brandId(rs.getInt(2))
                        .categoryId(rs.getInt(3))
                        .name(rs.getString(4))
                        .price(rs.getDouble(5))
                        .quantity(rs.getInt(6))
                        .imgName(rs.getString(7))
                        .description(rs.getString(8))
                        .note(rs.getString(9))
                        .status(rs.getInt(10))
                        .build();
                ls.add(p);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<Product> filterByCategory(int id) {
        String query = "SELECT * FROM Products WHERE category_id LIKE ?";
        List<Product> ls = new ArrayList<>();
        String searchText = "%" + id + "%";

        try (Connection con = SQLServerConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, searchText);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = Product.builder()
                        .id(rs.getInt(1))
                        .brandId(rs.getInt(2))
                        .categoryId(rs.getInt(3))
                        .name(rs.getString(4))
                        .price(rs.getDouble(5))
                        .quantity(rs.getInt(6))
                        .imgName(rs.getString(7))
                        .description(rs.getString(8))
                        .note(rs.getString(9))
                        .status(rs.getInt(10))
                        .build();
                ls.add(p);
            }
            return ls;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
