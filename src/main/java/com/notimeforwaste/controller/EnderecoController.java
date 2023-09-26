/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.controller;

import com.notimeforwaste.dto.EnderecoDTO;
import com.notimeforwaste.model.Endereco;
import com.notimeforwaste.service.EnderecoService;
import jakarta.validation.Valid;
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
        Endereco ret = enderecoService.save(endereco);
        return ret != null ? ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.save(endereco)) :  ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao salvar.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEnderecoById(@PathVariable(value = "id") int id) {
        Endereco endereco = enderecoService.findById(id);
        if (endereco == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEndereco(@PathVariable(value = "id") int id) {
        Endereco endereco = enderecoService.findById(id);
        if (endereco == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado.");
        }
        int ret = enderecoService.delete(endereco.getIdEndereco());
        return ret > 0 ? ResponseEntity.status(HttpStatus.OK).body("Endereço deletado com sucesso.") :  ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao deletar endereço.")  ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEndereco(@PathVariable(value = "id") int id,
            @RequestBody @Valid EnderecoDTO enderecoDTO) {
        Endereco enderecoRet = enderecoService.findById(id);
        if (enderecoRet == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado.");
        }
        var endereco = new Endereco();
        BeanUtils.copyProperties(enderecoDTO, endereco);
        endereco.setIdEndereco(enderecoRet.getIdEndereco());
        endereco = enderecoService.update(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
    }
}
