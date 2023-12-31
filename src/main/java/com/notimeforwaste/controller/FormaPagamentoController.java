/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.controller;

import com.notimeforwaste.model.FormaPagamento;
import com.notimeforwaste.service.FormaPagamentoService;
import java.util.List;
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
@RequestMapping("/api/notimeforwaste/pacote/formapagamento")
public class FormaPagamentoController {
    private final FormaPagamentoService formaPagamentoService;

    public FormaPagamentoController(FormaPagamentoService formaPagamentoService) {
        this.formaPagamentoService = formaPagamentoService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody FormaPagamento formaPagamento) {
        FormaPagamento ret = formaPagamentoService.save(formaPagamento);
        return ret != null ? ResponseEntity.status(HttpStatus.CREATED).body(formaPagamento) : ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao salvar.");
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") int id) {
        FormaPagamento formaPagamento = formaPagamentoService.findById(id);
        return formaPagamento != null ? ResponseEntity.status(HttpStatus.OK).body(formaPagamento) :  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Forma de Pagamento não diisponível.");
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") int id) {
        FormaPagamento formaPagamento = formaPagamentoService.findById(id);
        if (formaPagamento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Forma de Pagamento não encontrada.");
        }
        int ret = formaPagamentoService.delete(formaPagamento.getIdFormaPagamento());
        return ret > 0 ? ResponseEntity.status(HttpStatus.OK).body(formaPagamento) : ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao deletar.");
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<Object> update(@PathVariable(value = "id") int id,
    //         @RequestBody FormaPagamento formaPagamento) {
    //     FormaPagamento formaPagamentoOptional = formaPagamentoService.findById(id);
    //     if (formaPagamentoOptional == null) {
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Forma de Pagamento não encontrada.");
    //     }

    //     formaPagamento.setIdFormaPagamento(formaPagamentoOptional.getIdFormaPagamento());
    //     formaPagamentoService.update(formaPagamento);
    //     return ResponseEntity.status(HttpStatus.OK).body(formaPagamento);
    // }
}
