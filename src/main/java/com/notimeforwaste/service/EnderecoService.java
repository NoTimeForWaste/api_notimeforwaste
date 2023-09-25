/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.service;

import com.notimeforwaste.dao.EnderecoDao;
import com.notimeforwaste.model.Endereco;
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
public class EnderecoService {

    private final EnderecoDao enderecoDao;

    public EnderecoService(Jdbi jdbi) {
        this.enderecoDao = jdbi.onDemand(EnderecoDao.class);
    }

    public Endereco save(Endereco endereco) {
        endereco.setIdEndereco(enderecoDao.insert(endereco));
        return endereco;
    }

    public Endereco update(Endereco endereco) {
        endereco.setIdEndereco(enderecoDao.update(endereco));
        return endereco;
    }

    public List<Endereco> findAll() {
        return enderecoDao.findAll();
    }

    public Endereco findById(int id) {
        return enderecoDao.findById(id);
    }

    public void delete(int idEndereco) {
        enderecoDao.delete(idEndereco);
    }

    public int existsById(int idEndereco) {
        return enderecoDao.existsById(idEndereco);
    }
}
