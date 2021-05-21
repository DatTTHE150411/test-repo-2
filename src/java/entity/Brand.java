/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author TRANTATDAT
 */
@Getter
@Setter
@Builder
@ToString
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private int status;

}
