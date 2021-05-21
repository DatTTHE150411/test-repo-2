/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author TRANTATDAT
 */
public interface IMethod<E> {
    
    //tra ve toan bo record
    List<E> getAll();
    
    //tra ve 1 record
    E getOne(int id);
    
    //them 1 record
    boolean add(E obj);
    
    //update 1 record
    boolean update(int id, E obj);
    
    //xoa 1 record
    boolean remove(int id);
}
