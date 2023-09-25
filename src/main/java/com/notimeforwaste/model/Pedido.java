package com.notimeforwaste.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
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
@Entity
@Table(name = "Pedido")
public class Pedido implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPedido;
    @Column(nullable = false)
    private int idCliente;
    @Column(nullable = false, length=15)
    private String status;
    @Column(nullable = false)
    private int idFormaPagamento;
    @Column(nullable = false)
    private int idFormaEntrega;
    @Column(nullable = false)
    private double frete;
    @Column
    private Boolean cancelado;
}
