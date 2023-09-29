package com.notimeforwaste.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor

public class Produto {

    private int idProduto;
    private String nmProduto;
    private int idPacote;
    private Date dtValidade;
    private String descricao
    ;
}
