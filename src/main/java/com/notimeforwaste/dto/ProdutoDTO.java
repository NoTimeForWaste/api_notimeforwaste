/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Date;
import java.util.List;
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
public class ProdutoDTO {
    @NotNull
    @Size(max=100)
    private String nmProduto;
    @NotNull
    private int idPacote;
    @NotNull
    private Date dtValidade;
    @Size(max=200)
    private String descicao;
}
