package com.notimeforwaste.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notimeforwaste.model.PacoteFormaEntrega;
import com.notimeforwaste.model.PacoteFormaPagamento;
import com.notimeforwaste.service.FormaEntregaService;
import com.notimeforwaste.service.PacoteFormaEntregaService;
import com.notimeforwaste.service.PacoteService;

@RestController
@RequestMapping("/api/notimeforwaste/pacote/formaentrega")
public class PacoteFormaEntregaController {
    private final PacoteFormaEntregaService pacoteFormaEntregaService;
    private final PacoteService pacoteService;
    private final FormaEntregaService formaEntregaService;

    PacoteFormaEntregaController(PacoteFormaEntregaService pacoteFormaEntregaService, PacoteService pacoteService,
            FormaEntregaService formaEntregaService) {
        this.pacoteFormaEntregaService = pacoteFormaEntregaService;
        this.formaEntregaService = formaEntregaService;
        this.pacoteService = pacoteService;
    }

    @PostMapping({ "", "/" })
    public ResponseEntity<Object> save(@RequestBody PacoteFormaEntrega pacoteFormaEntrega) {

        if (pacoteService.findById(pacoteFormaEntrega.getIdPacote()) == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Pacote não cadastrado no sistema!");
        }
        if (formaEntregaService.findById(pacoteFormaEntrega.getIdFormaEntrega()) == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Forma de entrega não cadastrada no sistema!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pacoteFormaEntregaService.save(pacoteFormaEntrega));
    }

    @GetMapping("/pacote/{id}")
    public ResponseEntity<Object> getByIdPacote(@PathVariable(value = "id") int id) {
        List<PacoteFormaEntrega> pacoteFormaEntregaList = pacoteFormaEntregaService.findByIdFormaEntrega(id);
        if (pacoteFormaEntregaList == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pacoteFormaEntregaList);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteCliente(@RequestBody PacoteFormaEntrega pacoteFormaEntrega) {
        if (pacoteFormaEntregaService.findByIdFormaEntregaAndidPacote(pacoteFormaEntrega) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao deletar.");
        }
        pacoteFormaEntregaService.delete(pacoteFormaEntrega);
        return ResponseEntity.status(HttpStatus.OK).body(pacoteFormaEntrega);
    }

}