/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class PacoteDTO {
    @NotNull
    @Size(max = 100)
    private String nmPacote;
    @NotNull
    @DecimalMin("0.0")
    private float preco;
    @NotNull
    private int idEmpresa;
    @NotNull
    private int idFoto;
}
