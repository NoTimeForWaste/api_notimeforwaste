/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


/**
 *
 * @author Arthur
 */
@SuperBuilder
@Data

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {
    @NotBlank
    @Size(max=100)
    protected String nmCliente;
    @NotBlank
    @Size(max=70)
    private String email;
    @NotBlank
    @Size(max=15)
    private String senha;
}
