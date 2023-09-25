package com.notimeforwaste.model;

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
public class Endereco {

    private int idEndereco;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private int numero;
    private String complemento;
    private String apelido;
}
