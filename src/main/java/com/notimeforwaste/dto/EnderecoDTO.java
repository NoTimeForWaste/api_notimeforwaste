/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class EnderecoDTO {
    @NotBlank
    @Size(max=100)
    protected String rua;
    @NotBlank
    @Size(max=100)
    protected String bairro;
    @NotBlank
    @Size(max=100)
    protected String cidade;
    @NotBlank
    @Size(max=100)
    protected String estado;
    @NotBlank
    @Size(max=8)
    protected String cep;
    @NotNull 
    protected int numero;
    @NotBlank
    @Size(max=100)
    protected String complemento;
    @NotBlank
    @Size(max=45)
    protected String apelido;
}
