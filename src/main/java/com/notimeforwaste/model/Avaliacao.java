package com.notimeforwaste.model;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.io.Serializable;
import java.util.UUID;

@SuperBuilder
@Data
public class Avaliacao {

    protected UUID idAvaliacao;
    protected String comentario;
    protected int nota;
    protected int idEmpresa;
    protected int idCliente;
    protected Date dtAvaliacao;
}
