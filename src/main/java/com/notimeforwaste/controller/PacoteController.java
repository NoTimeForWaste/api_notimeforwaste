/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.controller;

import com.notimeforwaste.dto.PacoteDTO;
import com.notimeforwaste.model.Empresa;
import com.notimeforwaste.model.FormaEntrega;
import com.notimeforwaste.model.FormaPagamento;
import com.notimeforwaste.model.Foto;
import com.notimeforwaste.model.Pacote;
import com.notimeforwaste.model.PacoteFormaEntrega;
import com.notimeforwaste.model.PacoteFormaPagamento;
import com.notimeforwaste.model.Produto;
import com.notimeforwaste.response.PacoteResponse;
import com.notimeforwaste.service.ClienteService;
import com.notimeforwaste.service.EmpresaService;
import com.notimeforwaste.service.EnderecoService;
import com.notimeforwaste.service.FormaEntregaService;
import com.notimeforwaste.service.FormaPagamentoService;
import com.notimeforwaste.service.FotoService;
import com.notimeforwaste.service.PacoteFormaEntregaService;
import com.notimeforwaste.service.PacoteFormaPagamentoService;
import com.notimeforwaste.service.PacoteService;
import com.notimeforwaste.service.PedidoService;
import com.notimeforwaste.service.ProdutoService;

import jakarta.validation.Valid;

import java.util.ArrayList;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/notimeforwaste/pacote")
public class PacoteController {

    private final PacoteService pacoteService;
    private final EmpresaService empresaService;

    public PacoteController(
            PacoteService pacoteService,
            EmpresaService empresaService) {
        this.pacoteService = pacoteService;
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid PacoteDTO pacoteDTO) {
        var pacote = new Pacote();
        BeanUtils.copyProperties(pacoteDTO, pacote);
        if (empresaService.findById(pacote.getIdEmpresa()) == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao cadastrar! Empresa não encontrada.!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pacoteService.save(pacote));
    }

    @GetMapping
    public ResponseEntity<List<Pacote>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(pacoteService.findAll());
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<Object> getById(@PathVariable(value = "id") int id) {
    // Pacote pacote = pacoteService.findById(id);
    // if (pacote == null) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pacote não
    // encontrado.");
    // }
    // return ResponseEntity.status(HttpStatus.OK).body(pacote);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") int id) {
        Pacote pacote = pacoteService.findById(id);
        if (pacote == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pacote não encontrado.");
        }
        PacoteResponse pacoteResponse = pacoteService.getResponseById(id);
        return ResponseEntity.status(HttpStatus.OK).body(pacoteResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable(value = "id") int id) {
        Pacote pacote = pacoteService.findById(id);
        if (pacote == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pacote não encontrado.");
        }
        pacoteService.delete(pacote.getIdPacote());
        return ResponseEntity.status(HttpStatus.OK).body("Pacote deletado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable(value = "id") int id,
            @RequestBody @Valid PacoteDTO pacoteDTO) {
        Pacote pacoteOptional = pacoteService.findById(id);
        if (pacoteOptional == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pacote não encontrado.");
        }
        var pacote = new Pacote();
        BeanUtils.copyProperties(pacoteDTO, pacote);
        pacote.setIdPacote(pacoteOptional.getIdPacote());
        pacoteService.update(pacote);
        return ResponseEntity.status(HttpStatus.OK).body(pacote);
    }
}
