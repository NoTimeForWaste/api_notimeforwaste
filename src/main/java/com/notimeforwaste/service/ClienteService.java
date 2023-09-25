/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.service;

import com.notimeforwaste.dao.ClienteDao;
import com.notimeforwaste.model.Cliente;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arthur
 */
@Service
public class ClienteService {

    @Autowired
    private final ClienteDao clienteDao;

    public ClienteService(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }
    
    @Transactional     
    public Cliente save(Cliente cliente) {
        return clienteDao.save(cliente);
    }

    public boolean existsByEmail(String email) {
        return clienteDao.existsByEmail(email);
    }
    
    
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteDao.findAll(pageable);
    }
    
    
    public Optional<Cliente> findById(int id) {
        return clienteDao.findById(id);
    }

    @Transactional
    public void delete( Cliente cliente) {
        clienteDao.delete(cliente);
    }
}
