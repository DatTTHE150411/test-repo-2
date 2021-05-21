/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//thay the constructor, getter, setter, tostring
@Setter
@Getter
@Builder
@ToString


/**
 *
 * @author TRANTATDAT
 */
public class Product implements Serializable{
    //tim hieu design pattern
    
    //dao: database access object: bao gom
    //                             curd: create, read, update, delete
    
    private int id;
    private int brandId;
    private int categoryId;
    private String name;
    private double price;
    private int quantity;
    private String imgName;
    private String description;
    private String note;
    private int status;
}
