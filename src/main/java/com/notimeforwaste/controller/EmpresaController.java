/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.controller;

import com.notimeforwaste.dto.EmpresaDTO;
import com.notimeforwaste.model.Empresa;
import com.notimeforwaste.response.EmpresaResponse;
import com.notimeforwaste.service.EmpresaService;
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
@RequestMapping("/api/notimeforwaste/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping({ "", "/" })
    public ResponseEntity<Object> save(@RequestBody @Valid EmpresaDTO empresaDto) {
        if (empresaService.existsByEmail(empresaDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Esse e-mail já foi cadastrado!");
        }
        if (empresaService.existsByCNPJ(empresaDto.getCNPJ())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Esse CNPJ já foi cadastrado!");
        }

        var empresa = new Empresa();
        BeanUtils.copyProperties(empresaDto, empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.save(empresa));
    }

    // @GetMapping
    // public ResponseEntity<List<Empresa>> getAllEmpresas() {
    // return ResponseEntity.status(HttpStatus.OK).body(empresaService.findAll());
    // }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmpresaById(@PathVariable(value = "id") int id) {
        Empresa empresaOptional = empresaService.findById(id);
        if (empresaOptional == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa não encontrada.");
        }
        empresaOptional.setSenha("");
        return ResponseEntity.status(HttpStatus.OK).body(empresaOptional);
    }

    @GetMapping("login/{email}/{senha}")
    public ResponseEntity<Object> login(@PathVariable(value = "email") String email,
            @PathVariable(value = "senha") String senha) {
        Empresa empresa = empresaService.findByEmail(email);
        if (empresa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email inválido.");
        }

        Empresa result = empresaService.login(email, senha);

        EmpresaResponse empresaResponse = new EmpresaResponse(empresa.getIdEmpresa(), empresa.getNmEmpresa(),
                empresa.getCNPJ(), empresa.getEmail(), empresa.getIdFoto(), empresa.getIdEndereco(),
                empresa.getTelefone());

        return result != null ? ResponseEntity.status(HttpStatus.OK).body(empresaResponse)
                : ResponseEntity.status(HttpStatus.CONFLICT).body("Senha inválida!");
    }

    // @DeleteMapping("delete//{id}/{email}/{senha}")
    // public ResponseEntity<Object> deleteEmpresa(@PathVariable(value = "id") int
    // id,
    // @PathVariable(value = "email") String email, @PathVariable(value = "senha")
    // String senha) {
    // Empresa empresaOptional = empresaService.findById(id);
    // if (empresaOptional == null) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa não
    // encontrada.");
    // }
    // int result = empresaService.delete(id, email, senha);
    // return result > 0 ?
    // ResponseEntity.status(HttpStatus.OK).body(empresaOptional)
    // : ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao deletar
    // empresa.");
    // }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmpresa(@PathVariable(value = "id") int id,
            @RequestBody @Valid EmpresaDTO empresaDto) {
        Empresa empresaOptional = empresaService.findById(id);
        if (empresaOptional == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa não encontrada.");
        }
        var empresa = new Empresa();
        BeanUtils.copyProperties(empresaDto, empresa);
        empresa.setIdEmpresa(empresaOptional.getIdEmpresa());
        int result = empresaService.update(empresa);
        return result > 0 ? ResponseEntity.status(HttpStatus.OK).body(empresa)
                : ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao alterar dados da empresa!");
    }
}
