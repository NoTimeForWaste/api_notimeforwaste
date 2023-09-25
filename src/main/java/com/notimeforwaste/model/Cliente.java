/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author Arthur
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
//    private static final long serialVersionUID = 1L;

    protected int idCliente;
    protected String email;
    protected String nmCliente;
    private String senha;
}
