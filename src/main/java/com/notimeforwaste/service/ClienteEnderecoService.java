/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.service;

import com.notimeforwaste.dao.ClienteEnderecoDao;
import com.notimeforwaste.model.Cliente_Endereco;
import com.notimeforwaste.model.Endereco;

import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arthur
 */
@Service
public class ClienteEnderecoService {

    private final ClienteEnderecoDao clienteEnderecoDao;
    private final EnderecoService enderecoService;

    public ClienteEnderecoService(Jdbi jdbi, EnderecoService enderecoService) {
        this.clienteEnderecoDao = jdbi.onDemand(ClienteEnderecoDao.class);
        this.enderecoService = enderecoService;
    }

    public Cliente_Endereco save(Cliente_Endereco clienteEndereco) {
        clienteEnderecoDao.insert(clienteEndereco.getIdEndereco(),
                clienteEndereco.getIdCliente());
        return clienteEndereco;
    }

    public List<Cliente_Endereco> findByIdCliente(int id) {
        return clienteEnderecoDao.findByIdCliente(id);
    }

    public List<Cliente_Endereco> findByIdEndereco(int id) {
        return clienteEnderecoDao.findByIdEndereco(id);
    }

    public List<Cliente_Endereco> findByIdEnderecoAndIdCLiente(int idEndereco, int idCliente) {
        return clienteEnderecoDao.findByIdEnderecoAndIdCliente(idEndereco, idCliente);
    }

    public List<Endereco> findEnderecosByIdCliente(int idCliente) {
        List<Cliente_Endereco> clienteEnderecoList = findByIdCliente(idCliente);
        List<Endereco> enderecos = new ArrayList<Endereco>();
        for (int i = 0; i < clienteEnderecoList.size(); i++) {
            Endereco endereco = enderecoService.findById(clienteEnderecoList.get(i).getIdEndereco());
            enderecos.add(endereco);
        }
        return enderecos;
    }

    public void delete(int idEndereco, int idCliente) {
        // List<ClienteEndereco> enderecoClienteList =
        // clienteEnderecoDao.findByIdEnderecoAndIdCliente(idEndereco, idCliente);

        // for (ClienteEndereco enderecoCliente : enderecoClienteList) {
        // }
        clienteEnderecoDao.delete(idEndereco, idCliente);

    }

    public void deleteAllByIdCliente(int idCliente) {
        // List<ClienteEndereco> enderecoClienteList =
        // clienteEnderecoDao.findByIdCliente(idCliente);

        // for (ClienteEndereco enderecoCliente : enderecoClienteList) {
        // }
        clienteEnderecoDao.deleteByIdCliente(idCliente);
    }

    public void deleteAllByIdEndereco(int idEndereco) {
        // List<ClienteEndereco> enderecoClienteList =
        // clienteEnderecoDao.findByIdCliente(idCliente);

        // for (ClienteEndereco enderecoCliente : enderecoClienteList) {
        // }
        clienteEnderecoDao.deleteByIdEndereo(idEndereco);

    }

}
