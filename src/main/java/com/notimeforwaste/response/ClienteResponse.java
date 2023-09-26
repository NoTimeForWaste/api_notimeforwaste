package com.notimeforwaste.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {
    protected int idCliente;
    protected String email;
    protected String nmCliente;
}
