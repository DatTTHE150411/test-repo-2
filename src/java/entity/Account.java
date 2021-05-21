/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author TRANTATDAT
 */
@Builder
@Getter
@Setter
@ToString
public class Account {

    private int id;
    private String email;
    private String password;
    private String activeCode;
    private int role;
    private int status;
}
