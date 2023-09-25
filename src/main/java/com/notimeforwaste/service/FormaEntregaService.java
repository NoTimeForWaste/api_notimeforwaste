/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.service;

import com.notimeforwaste.dao.FormaEntregaDao;
import com.notimeforwaste.model.FormaEntrega;
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
public class FormaEntregaService {

    private final FormaEntregaDao formaEntregaDao;

    public FormaEntregaService(Jdbi jdbi) {
        this.formaEntregaDao = jdbi.onDemand(FormaEntregaDao.class);
    }

    public FormaEntrega save(FormaEntrega formaEntrega) {
        formaEntrega.setIdFormaEntrega(formaEntregaDao.insert(formaEntrega));
        return formaEntrega;
    }

    public void update(FormaEntrega formaEntrega) {
        formaEntregaDao.update(formaEntrega);
    }

    public List<FormaEntrega> findAll() {
        return formaEntregaDao.findAll();
    }

    public FormaEntrega findById(int idFormaEntrega) {
        return formaEntregaDao.findById(idFormaEntrega);
    }

    public void delete(int idFormaEntrega) {
        formaEntregaDao.delete(idFormaEntrega);
    }

    public int existsById(int idFormaEntrega) {
        return formaEntregaDao.existsById(idFormaEntrega);
    }
}
