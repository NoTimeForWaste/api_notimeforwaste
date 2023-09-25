/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.service;

import com.notimeforwaste.dao.FormaPagamentoDao;
import com.notimeforwaste.model.FormaEntrega;
import com.notimeforwaste.model.FormaPagamento;
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
public class FormaPagamentoService {

    private final FormaPagamentoDao formaPagamentoDao;

    public FormaPagamentoService(Jdbi jdbi) {
        this.formaPagamentoDao = jdbi.onDemand(FormaPagamentoDao.class);
    }

    public FormaPagamento save(FormaPagamento formaPagamento) {
        formaPagamento.setIdFormaPagamento(formaPagamentoDao.insert(formaPagamento));
        return formaPagamento;
    }

    public void update(FormaPagamento formaPagamento) {
        formaPagamentoDao.update(formaPagamento);
    }

    public List<FormaPagamento> findAll() {
        return formaPagamentoDao.findAll();
    }

    public FormaPagamento findById(int idFormaPagamento) {
        return formaPagamentoDao.findById(idFormaPagamento);
    }

    public void delete(int idFormaPagamento) {
        formaPagamentoDao.delete(idFormaPagamento);
    }

    public int existsById(int idFormaPagamento) {
        return formaPagamentoDao.existsById(idFormaPagamento);
    }
}
