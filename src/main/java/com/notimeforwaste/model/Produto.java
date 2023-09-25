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
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Produto")
public class Produto implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idProduto;
    @Column(nullable = false, length=100)
    private String nome;
    @Column(nullable = false)
    private int idPacote;
    @Column(nullable = false)
    private Date validade;
    @Column(nullable = false, length = 300)
    private String descricao;
}
