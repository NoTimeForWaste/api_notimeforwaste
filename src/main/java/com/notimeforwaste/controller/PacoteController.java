/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.controller;

import com.notimeforwaste.dto.PacoteDTO;
import com.notimeforwaste.model.Pacote;

import com.notimeforwaste.response.PacoteResponse;
import com.notimeforwaste.service.EmpresaService;
import com.notimeforwaste.service.PacoteService;

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
        Pacote ret = pacoteService.save(pacote);
        return ret != null ? ResponseEntity.status(HttpStatus.CREATED).body(pacote) : ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao salvar");
    }

    @GetMapping
    public ResponseEntity<Object> findAllWithoutOrders() {
        List<PacoteResponse> pacotes = pacoteService.findAllWithoutOrders();
        return ResponseEntity.status(HttpStatus.OK).body(pacotes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") int id) {
        PacoteResponse pacoteResponse = pacoteService.getResponseById(id);
        return pacoteResponse != null ? ResponseEntity.status(HttpStatus.OK).body(pacoteResponse)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pacote não encontrado.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable(value = "id") int id) {
        Pacote pacote = pacoteService.findById(id);
        if (pacote == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pacote não encontrado.");
        }
        int ret = pacoteService.delete(pacote.getIdPacote());
        return ret > 0 ? ResponseEntity.status(HttpStatus.OK).body(pacote) : ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao deletar.") ;
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
