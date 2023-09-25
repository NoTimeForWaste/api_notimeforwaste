package com.notimeforwaste.model;

import java.io.Serializable;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pedido {
    private int idPedido;
    private int idCliente;
    private int idPacote;
    private int idEndereco;
    private String status;
    private int idFormaPagamento;
    private int idFormaEntrega;
    private double frete;
    private Boolean cancelado;
    private Date dtPedido;
}
