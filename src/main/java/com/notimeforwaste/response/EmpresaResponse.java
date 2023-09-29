package com.notimeforwaste.response;

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
public class EmpresaResponse {
    protected int idEmpresa;
    protected String nmEmpresa;
    protected String CNPJ;
    protected String email;
    protected int idFoto;
    protected int idEndereco;
    protected String telefone;

}