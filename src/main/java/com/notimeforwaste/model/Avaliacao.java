package com.notimeforwaste.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.io.Serializable;
import java.util.UUID;

@SuperBuilder
@Data
@Entity
@Table(name = "Avaliacao")
public class Avaliacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID idAvaliacao;
    @Column(length = 500)
    protected String comentario;
    @Column(nullable = false)
    protected int nota;
    @Column(nullable = false)
    protected int idEmpresa;
    @Column(nullable = false)
    protected int idCliente;
    @Column(nullable = false)
    protected Date dtAvaliacao;
}
