/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.controller;

import com.notimeforwaste.dto.ClienteDTO;
import com.notimeforwaste.model.Cliente_Endereco;
import com.notimeforwaste.model.Endereco;
import com.notimeforwaste.model.HorarioFuncionamento;
import com.notimeforwaste.service.ClienteEnderecoService;
import com.notimeforwaste.service.ClienteService;
import com.notimeforwaste.service.HorarioFuncionamentoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Arthur
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/notimeforwaste/cliente/endereco")
public class ClienteEnderecoController {

    private final ClienteEnderecoService clienteEnderecoService;
    private final ClienteService clienteService;

    public ClienteEnderecoController(ClienteEnderecoService clienteEnderecoService, ClienteService clienteService) {
        this.clienteEnderecoService = clienteEnderecoService;
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdCliente(@PathVariable("id") int id) {
        List<Cliente_Endereco> ret = clienteEnderecoService.findByIdCliente(id);
        return ResponseEntity.status(HttpStatus.OK).body(ret);
    }

    @GetMapping("{idCliente}/enderecosbycliente")
    public ResponseEntity<Object> findEnderecosByIdCliente(@PathVariable("idCliente") int idCliente) {
        List<Endereco> ret = clienteEnderecoService.findEnderecosByIdCliente(idCliente);
        return ResponseEntity.status(HttpStatus.OK).body(ret);

    }

    @PostMapping({ "", "/" })
    public ResponseEntity<Object> save(@RequestBody Cliente_Endereco clienteEndereco) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteEnderecoService.save(clienteEndereco));

    }

    @DeleteMapping("/{idEndereco}/byendereco")
    public ResponseEntity<Object> deletar(@PathVariable("idEndereco") int idEndereco) {
        clienteEnderecoService.deleteAllByIdEndereco(idEndereco);
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso");
    }
}
