package com.notimeforwaste.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notimeforwaste.dto.ClienteDTO;
import com.notimeforwaste.model.Cliente;
import com.notimeforwaste.model.PacoteFormaPagamento;
import com.notimeforwaste.service.FormaPagamentoService;
import com.notimeforwaste.service.PacoteFormaPagamentoService;
import com.notimeforwaste.service.PacoteService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/notimeforwaste/pacote/formapagamento")
public class PacoteFormaPagamentoController {
    private final PacoteFormaPagamentoService pacoteFormaPagamentoService;
    private final PacoteService pacoteService;
    private final FormaPagamentoService pagamentoService;

    PacoteFormaPagamentoController(PacoteFormaPagamentoService pacoteFormaPagamentoService,
            PacoteService pacoteService, FormaPagamentoService formaPagamentoService) {
        this.pacoteFormaPagamentoService = pacoteFormaPagamentoService;
        this.pacoteService = pacoteService;
        this.pagamentoService = formaPagamentoService;
    }

    @PostMapping({ "", "/" })
    public ResponseEntity<Object> save(@RequestBody PacoteFormaPagamento pacoteFormaPagamento) {

        if (pacoteService.findById(pacoteFormaPagamento.getIidPacote()) == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Pacote não cadastrado no sistema!");
        }
        if (pagamentoService.findById(pacoteFormaPagamento.getIdFormaPagamento()) == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Forma de Pagamento não cadastrada no sistema!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pacoteFormaPagamentoService.save(pacoteFormaPagamento));
    }

    @GetMapping("/pacote/{id}")
    public ResponseEntity<Object> getByIdPacote(@PathVariable(value = "id") int id) {
        List<PacoteFormaPagamento> pacoteFormaPagamentoList = pacoteFormaPagamentoService.findByIdFormaPagamento(id);
        if (pacoteFormaPagamentoList == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pacoteFormaPagamentoList);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteCliente(@RequestBody PacoteFormaPagamento pacoteFormaPagamento) {
        if (pacoteFormaPagamentoService.findByIdFormaPagamentoAndidPacote(pacoteFormaPagamento) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao deletar.");
        }
        pacoteFormaPagamentoService.delete(pacoteFormaPagamento);
        return ResponseEntity.status(HttpStatus.OK).body(pacoteFormaPagamento);
    }

}