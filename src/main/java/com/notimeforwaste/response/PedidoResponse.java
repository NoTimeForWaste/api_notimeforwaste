package com.notimeforwaste.response;

import java.sql.Date;

import com.notimeforwaste.dao.FormaPagamentoDao;
import com.notimeforwaste.model.Endereco;
import com.notimeforwaste.model.FormaEntrega;
import com.notimeforwaste.model.FormaPagamento;
import com.notimeforwaste.model.Pedido;

import jakarta.validation.OverridesAttribute.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PedidoResponse {
    private int idPedido;
    private int idCliente;
    private String nmCliente;
    private Endereco endereco;
    private String status;
    private Boolean cancelado;
    private Double frete;
    private int idFormaEntrega;
    private int idFomPagamento;
    private PacoteResponse pacote;
}
