/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.controller;

import com.notimeforwaste.dto.ClienteDTO;
import com.notimeforwaste.model.Cliente;
import com.notimeforwaste.model.Empresa;
import com.notimeforwaste.response.EmpresaResponse;
import com.notimeforwaste.service.ClienteService;
import jakarta.validation.Valid;
import java.util.List;  
import org.springframework.beans.BeanUtils;
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
@CrossOrigin(origins = "*")
@RequestMapping("/api/notimeforwaste/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping({ "", "/" })
    public ResponseEntity<Object> save(@RequestBody @Valid ClienteDTO clienteDTO) {
        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteDTO, cliente);
        System.out.print(clienteDTO);
        if (clienteService.existsByEmail(cliente.getEmail()) > 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Esse e-mail já foi cadastrado!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
    }

    // @GetMapping
    // public ResponseEntity<List<Cliente>> getAllClientes() {
    // return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
    // }

      @GetMapping("login/{email}/{senha}")
    public ResponseEntity<Object> login(@PathVariable(value = "email") String email,
            @PathVariable(value = "senha") String senha) {
        Cliente cliente = clienteService.findByEmail(email);
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email inválido.");
        }

        Cliente result = clienteService.login(email, senha);
        cliente.setSenha("");
    
        return result != null ? ResponseEntity.status(HttpStatus.OK).body(result)
                : ResponseEntity.status(HttpStatus.CONFLICT).body("Senha inválida!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClienteById(@PathVariable(value = "id") int id) {
        Cliente cliente = clienteService.findById(id);
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
        cliente.setSenha("");
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    // @DeleteMapping("/deletar/{id}/{email}/{senha}")
    // public ResponseEntity<Object> deleteCliente(@PathVariable(value = "id") int
    // id,
    // @PathVariable(value = "senha") String senha, @PathVariable(value = "email")
    // String email) {
    // Cliente cliente = clienteService.findById(id);
    // if (cliente == null) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não
    // encontrado.");
    // }
    // int status = clienteService.delete(cliente.getIdCliente(), email, email);

    // return status > 0 ? ResponseEntity.status(HttpStatus.OK).body(cliente)
    // : ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao deletar
    // cliente.");
    // }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable(value = "id") int id,
            @RequestBody @Valid ClienteDTO clienteDto) {
        Cliente clienteOptional = clienteService.findById(id);
        if (clienteOptional == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteDto, cliente);
        cliente.setIdCliente(clienteOptional.getIdCliente());
        int status = clienteService.update(
                cliente.getIdCliente(), cliente.getNmCliente(), cliente.getSenha(), cliente.getEmail());
        return status > 0 ? ResponseEntity.status(HttpStatus.OK).body(cliente)
                : ResponseEntity.status(HttpStatus.CONFLICT).body("Não foi possível tulizar os dados do cliente!");
    }
}
