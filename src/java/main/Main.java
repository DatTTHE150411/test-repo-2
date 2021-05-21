/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dao.*;
import dao.ProductDao;
import entity.*;
import entity.Product;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author TRANTATDAT
 */
public class Main {
    
    public static void main(String[] args) {
        
        Order o = Order.builder()
                .name("Dat")
                .mobile("1234567")
                .address("Hanoi")
                .totalMoney(2000000)
                .note("-")
                .status(1)
                .build();
        int id = new OrderDao().addOrder(o);
        System.out.println(id);
        

//        List<Product> ls = new ProductDao().getAll();
//        
//        ls.sort(Comparator.comparingDouble(Product::getPrice));
//        
//        
//        ls.forEach(System.out::println);

        //System.out.println(new ImageDao().getOne(5));
//        Image b = Image.builder()
//                .product_id(5)
//                .imgName("Hi2")
//                .note(null)
//                .build();
//        System.out.println(new ImageDao().update(51, b));
        //System.out.println(new ImageDao().remove(51));
//        System.out.println(new CategoryDao().getOne(5));
        //System.out.println(new ProductDao().remove(5));
    }
    
}
