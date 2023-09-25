/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.controller;

import com.notimeforwaste.dto.HorarioFuncionamentoDTO;
import com.notimeforwaste.model.Endereco;
import com.notimeforwaste.model.HorarioFuncionamento;
import com.notimeforwaste.service.EmpresaService;
import com.notimeforwaste.service.HorarioFuncionamentoService;
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
@RequestMapping("/api/notimeforwaste/empresa/horariofuncionamento")
public class HorarioFuncionamentoController {

    private final HorarioFuncionamentoService horarioFuncionamentoService;
    private final EmpresaService empresaService;

    public HorarioFuncionamentoController(HorarioFuncionamentoService horarioFuncionamentoService,
            EmpresaService empresaService) {
        this.horarioFuncionamentoService = horarioFuncionamentoService;
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid HorarioFuncionamentoDTO horarioDTO) {
        var horarioFuncionamento = new HorarioFuncionamento();
        BeanUtils.copyProperties(horarioDTO, horarioFuncionamento);
        if (empresaService.findById(horarioFuncionamento.getIdEmpresa()) == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Empresa não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(horarioFuncionamentoService.save(horarioFuncionamento));
    }

    @GetMapping({ "/", "" })
    public ResponseEntity<List<HorarioFuncionamento>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(horarioFuncionamentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioFuncionamento> findById(@PathVariable("id") int id) {
        HorarioFuncionamento ret = horarioFuncionamentoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ret);
    }

    @GetMapping("/empresa/{id}")
    public List<HorarioFuncionamento> findByEmpresaId(@PathVariable("id") int id) {
        List<HorarioFuncionamento> horariosList = horarioFuncionamentoService.findByEmpresaId(id);
        return horariosList;
    }

    @PutMapping({ "", "/" })
    public ResponseEntity<Object> alter(@RequestBody HorarioFuncionamento horarioFuncionamento) {
        HorarioFuncionamento horario = horarioFuncionamentoService.findById(horarioFuncionamento.getIdHorario());
        if (horario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horario não encontrado.");
        }
        horarioFuncionamentoService.update(horarioFuncionamento);

        return ResponseEntity.status(HttpStatus.OK).body(horario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") int id) {
        HorarioFuncionamento horario = horarioFuncionamentoService.findById(id);
        if (horario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horario não encontrado.");
        }
        horarioFuncionamentoService.delete(horario.getIdHorario());
        return ResponseEntity.status(HttpStatus.OK).body("Horario deletado com sucesso.");
    }

}
