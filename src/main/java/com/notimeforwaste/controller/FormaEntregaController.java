/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.controller;

import com.notimeforwaste.model.FormaEntrega;
import com.notimeforwaste.service.FormaEntregaService;
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
@CrossOrigin(origins = "*")
@RequestMapping("/api/notimeforwaste/pacote/formaentrega")
public class FormaEntregaController {

    private final FormaEntregaService formaEntregaService;

    public FormaEntregaController(FormaEntregaService formaEntregaService) {
        this.formaEntregaService = formaEntregaService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody FormaEntrega formaEntrega) {
        return ResponseEntity.status(HttpStatus.CREATED).body(formaEntregaService.save(formaEntrega));
    }

    @GetMapping
    public ResponseEntity<List<FormaEntrega>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(formaEntregaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFormaEntregaById(@PathVariable(value = "id") int id) {
        FormaEntrega formaEntrega = formaEntregaService.findById(id);
        if (formaEntrega == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Forma de Entrega não disponível.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(formaEntrega);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") int id) {
        FormaEntrega formaEntrega = formaEntregaService.findById(id);
        if (formaEntrega == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Forma de Entrega não encontrada.");
        }
        formaEntregaService.delete(formaEntrega.getIdFormaEntrega());
        return ResponseEntity.status(HttpStatus.OK).body("Forma de Entrega deletada com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") int id,
            @RequestBody FormaEntrega formaEntrega) {
        FormaEntrega formaEntregaOptional = formaEntregaService.findById(id);
        if (formaEntregaOptional == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Forma de Entrega não encontrada.");
        }
        formaEntrega.setIdFormaEntrega(formaEntregaOptional.getIdFormaEntrega());
        formaEntregaService.update(formaEntrega);
        return ResponseEntity.status(HttpStatus.OK).body(formaEntrega);
    }

}
