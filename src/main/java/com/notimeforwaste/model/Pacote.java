package com.notimeforwaste.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
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
@Table(name = "Pacote")
public class Pacote implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPacote;
    @Column(nullable = false, length=100)
    private String nome;
    @Column(nullable = false)
    private float preco;
    @Column(nullable = false)
    private int idEmpresa;
    @Column(nullable = false)
    private int idFotos;
    @Column(nullable = false)
    private int idPedido;

    protected List<PacoteFormaPagamento> formaPagamentos;
    protected List<PacoteFormaEntrega> formaEntregas;
}
