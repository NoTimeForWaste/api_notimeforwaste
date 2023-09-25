package com.notimeforwaste.response;

import java.util.List;

import com.notimeforwaste.model.FormaEntrega;
import com.notimeforwaste.model.FormaPagamento;
import com.notimeforwaste.model.Foto;
import com.notimeforwaste.model.Produto;

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
public class PacoteResponse {
    private int idPacote;
    private String nmPacote;
    private float preco;
    private int idEmpresa;
    private Foto foto;
    private String nmEmpresa;

    private List<FormaEntrega> formasEntregas;
    private List<FormaPagamento> formasPagamentos;
    private List<Produto> produtos;
}
