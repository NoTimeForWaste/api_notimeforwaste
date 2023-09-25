/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.controller;

import com.notimeforwaste.dto.ClienteDTO;
import com.notimeforwaste.dto.EnderecoDTO;
import com.notimeforwaste.model.Cliente;
import com.notimeforwaste.model.Endereco;
import com.notimeforwaste.service.ClienteService;
import com.notimeforwaste.service.EnderecoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
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
@RequestMapping("/api/notimeforwaste/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        var endereco = new Endereco();
        BeanUtils.copyProperties(enderecoDTO, endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(endereco));
    }

    @GetMapping({ "/", "" })

    public ResponseEntity<List<Endereco>> getAllEnderecos() {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEnderecoById(@PathVariable(value = "id") int id) {
        Endereco enderecoOptional = enderecoService.findById(id);
        if (enderecoOptional == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(enderecoOptional);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEndereco(@PathVariable(value = "id") int id) {
        Endereco enderecoOptional = enderecoService.findById(id);
        if (enderecoOptional == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado.");
        }
        enderecoService.delete(enderecoOptional.getIdEndereco());
        return ResponseEntity.status(HttpStatus.OK).body("Endereco deletado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEndereco(@PathVariable(value = "id") int id,
            @RequestBody @Valid EnderecoDTO enderecoDTO) {
        Endereco enderecoOptional = enderecoService.findById(id);
        if (enderecoOptional == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado.");
        }
        var endereco = new Endereco();
        BeanUtils.copyProperties(enderecoDTO, endereco);
        endereco.setIdEndereco(enderecoOptional.getIdEndereco());
        endereco = enderecoService.update(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
    }
}
