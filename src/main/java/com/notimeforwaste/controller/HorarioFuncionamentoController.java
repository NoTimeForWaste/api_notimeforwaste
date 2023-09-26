/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.controller;

import com.notimeforwaste.dto.HorarioFuncionamentoDTO;
import com.notimeforwaste.model.HorarioFuncionamento;
import com.notimeforwaste.service.EmpresaService;
import com.notimeforwaste.service.HorarioFuncionamentoService;
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa não encontrada.");
        }
        HorarioFuncionamento ret = horarioFuncionamentoService.save(horarioFuncionamento);
        return ret != null ? ResponseEntity.status(HttpStatus.CREATED).body(horarioFuncionamento)
                : ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao salvar.");
    }

    @GetMapping({ "/", "" })
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(horarioFuncionamentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") int id) {
        HorarioFuncionamento ret = horarioFuncionamentoService.findById(id);
        return ret != null ? ResponseEntity.status(HttpStatus.OK).body(ret)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horário não encontrado");
    }

    @GetMapping("/empresa/{id}")
    public ResponseEntity<Object> findByEmpresaId(@PathVariable("id") int id) {
        List<HorarioFuncionamento> horariosList = horarioFuncionamentoService.findByEmpresaId(id);
        return horariosList != null ? ResponseEntity.status(HttpStatus.OK).body(horariosList)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Essa empresa não possui horários cadastrados.");
    }

    @PutMapping({ "", "/" })
    public ResponseEntity<Object> alter(@RequestBody HorarioFuncionamento horarioFuncionamento) {
        HorarioFuncionamento horario = horarioFuncionamentoService.findById(horarioFuncionamento.getIdHorario());
        if (horario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horario não encontrado.");
        }
        int ret = horarioFuncionamentoService.update(horarioFuncionamento);

        return ret > 0 ? ResponseEntity.status(HttpStatus.OK).body(horario)
                : ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao alterar.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") int id) {
        HorarioFuncionamento horario = horarioFuncionamentoService.findById(id);
        if (horario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horario não encontrado.");
        }
        int ret = horarioFuncionamentoService.delete(horario.getIdHorario());
        return ret > 0 ? ResponseEntity.status(HttpStatus.OK).body(horario)
                : ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao deletar.");
    }

}
