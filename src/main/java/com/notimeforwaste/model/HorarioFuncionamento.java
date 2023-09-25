package com.notimeforwaste.model;

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

public class HorarioFuncionamento {

    protected int idHorario;
    protected String nome;
    protected Time horarioInicial;
    protected Time horarioFinal;
    private int idEmpresa;
}
