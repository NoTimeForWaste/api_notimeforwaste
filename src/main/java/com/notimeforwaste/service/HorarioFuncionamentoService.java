/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.service;

import com.notimeforwaste.dao.HorarioFuncionamentoDao;
import com.notimeforwaste.model.HorarioFuncionamento;
import java.util.List;
import java.util.Optional;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arthur
 */
@Service
public class HorarioFuncionamentoService {

    private final HorarioFuncionamentoDao horarioFuncionamentoDao;

    public HorarioFuncionamentoService(Jdbi jdbi) {
        this.horarioFuncionamentoDao = jdbi.onDemand(HorarioFuncionamentoDao.class);
    }

    public HorarioFuncionamento save(HorarioFuncionamento horario) {
        horario.setIdHorario(horarioFuncionamentoDao.insert(horario));
        return horario;
    }

    public int update(HorarioFuncionamento horario) {
        return horarioFuncionamentoDao.update(horario);
    }

    public List<HorarioFuncionamento> findAll() {
        return horarioFuncionamentoDao.findAll();
    }

    public HorarioFuncionamento findById(int id) {
        return horarioFuncionamentoDao.findById(id);
    }

    public List<HorarioFuncionamento> findByIdEmpresa(int id) {
        return horarioFuncionamentoDao.findByIdEmpresa(id);
    }

    public int delete(int idHorario) {
        return horarioFuncionamentoDao.delete(idHorario);
    }
}
