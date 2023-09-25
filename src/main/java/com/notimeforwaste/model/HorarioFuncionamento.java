package com.notimeforwaste.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "HorarioFuncionamento")
public class HorarioFuncionamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int idHorario;
    @Column(nullable = false, length=45)
    protected String nome;
    @Column(nullable = false)
    protected Time horarioInicial;
    @Column(nullable = false)
    protected Time horarioFinal;
    @Column(nullable = false)
    protected int idEmpresa;
}
