/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.service;

import com.notimeforwaste.dao.ClienteDao;
import com.notimeforwaste.model.Cliente;
import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arthur
 */
@Service
public class ClienteService {

    private final ClienteDao clienteDao;

    public ClienteService(Jdbi jdbi) {
        this.clienteDao = jdbi.onDemand(ClienteDao.class);
    }

    public Cliente save(Cliente cliente) {
        // String senhaCriptografada = BCrypt.hashpw(cliente.getSenha(),
        // BCrypt.gensalt());
        // cliente.setSenha(senhaCriptografada);
        cliente.setIdCliente(clienteDao.insert(cliente));
        return cliente;
    }

    public int update(int idCliente, String nmCliente, String senha, String email) {
        Cliente clienteExistente = clienteDao.findById(idCliente);
        if (clienteExistente != null) {
            // String senhaCriptografada = BCrypt.hashpw(senha, BCrypt.gensalt());
            return clienteDao.update(idCliente, nmCliente, senha, email);
        } else {
            return -1;
        }
    } 

    public Cliente login(String email, String senha) {
        // Verifique se a senha fornecida corresponde à senha criptografada
        return clienteDao.login(email, senha);

    }

    public int existsByEmail(String email) {
        return clienteDao.existsByEmail(email);
    }

    public int existsById(int idCliente) {
        return clienteDao.existsById(idCliente);
    }

    public List<Cliente> findAll() {
        return clienteDao.findAll();
    }

    public Cliente findById(int idCliente) {
        return clienteDao.findById(idCliente);
    }

    public Cliente findByEmail(String email) {
        return clienteDao.findByEmail(email);
    }

    public int delete(int idCliente, String senha, String email) {
        return clienteDao.delete(idCliente, email, email);
    }

}
