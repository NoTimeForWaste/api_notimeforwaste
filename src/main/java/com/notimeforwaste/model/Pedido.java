package com.notimeforwaste.model;

import java.sql.Timestamp;
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
    private int status;
    private int idFormaPagamento;
    private int idFormaEntrega;
    private double frete;
    private Boolean cancelado;
    private Timestamp  dtPedido;
    private String observacao;
}
