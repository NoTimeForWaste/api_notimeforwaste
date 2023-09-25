package com.notimeforwaste.model;

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
public class Pacote {

    private int idPacote;
    private String nmPacote;
    private float preco;
    private int idEmpresa;
    private int idFoto;

    // protected List<Integer> idsFormaPagamentos;
    // protected List<Integer> idsFormaEntregas;
}
