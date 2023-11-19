/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.service;

import com.notimeforwaste.dao.PedidoDao;
import com.notimeforwaste.model.Cliente;
import com.notimeforwaste.model.Endereco;
import com.notimeforwaste.model.Pedido;
import com.notimeforwaste.response.PacoteResponse;
import com.notimeforwaste.response.PedidoResponse;

import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author arthu
 */
@Service
public class PedidoService {
    private final PedidoDao pedidoDao;
    private final PacoteService pacoteService;
    private final EnderecoService enderecoService;
    private final ClienteService clienteService;

    public PedidoService(Jdbi jdbi, PacoteService pacoteService, EnderecoService enderecoService,
            ClienteService clienteService) {
        this.pedidoDao = jdbi.onDemand(PedidoDao.class);
        this.enderecoService = enderecoService;
        this.clienteService = clienteService;
        this.pacoteService = pacoteService;
    }

    public Pedido save(Pedido pedido) {
        if(pedido.getIdFormaEntrega() == 1){
            pedido.setIdEndereco(-1);
        }
        int idPedido = pedidoDao.insert(pedido);
        pedido.setIdPedido(idPedido);
        return idPedido > 0 ? pedido : null;
    }

    public List<Pedido> findAll() {
        return pedidoDao.findAll();
    }

    public Pedido findById(int idPedido) {
        return pedidoDao.findById(idPedido);
    }

    public List<PedidoResponse> findByClientId(int idCliente) {
        List<Pedido> pedidos = pedidoDao.findByClientId(idCliente);
        List<PedidoResponse> pedidosResponses = new ArrayList<PedidoResponse>();
        if (pedidos != null) {
            for (int i = 0; i < pedidos.size(); i++) {
                pedidosResponses.add(this.findResponseByIdPedido(pedidos.get(i).getIdPedido()));
            }
        }
        return pedidosResponses;
    }

    public List<PedidoResponse> findByCompanyId(int idEmpresa) {
        List<Pedido> pedidos = pedidoDao.findByCompanyId(idEmpresa);
        List<PedidoResponse> pedidosResponses = new ArrayList<PedidoResponse>();
        if (pedidos != null) {
            for (int i = 0; i < pedidos.size(); i++) {
                pedidosResponses.add(this.findResponseByIdPedido(pedidos.get(i).getIdPedido()));
            }
        }
        return pedidosResponses;
    }

    public int update(Pedido pedido) {
        return pedidoDao.update(pedido);
    }

    public int updateStatus(int status, int idPedido) {
        return pedidoDao.updateStatus(status, idPedido);
    }

    public int delete(int idPedido) {
        return pedidoDao.delete(idPedido);
    }

     public int cancelar(int idPedido, Boolean cancelado) {
        return pedidoDao.cancelar(cancelado, idPedido);
    }


    public int existsByIdPacote(int idPacote) {
        return pedidoDao.existsByIdPacote(idPacote);
    }

    public PedidoResponse findResponseByIdPedido(int idPedido) {
        Pedido pedido = findById(idPedido);
        if (pedido == null) {
            return null;
        }
        Endereco endereco = enderecoService.findById(pedido.getIdEndereco());
        String nmCliente = clienteService.findById(pedido.getIdCliente()).getNmCliente();
        PacoteResponse pacoteResponse = pacoteService.getResponseById(pedido.getIdPacote());

        PedidoResponse pedidoResponse = new PedidoResponse();
        pedidoResponse.setIdPedido(pedido.getIdPedido());
        pedidoResponse.setCancelado(pedido.getCancelado());
        pedidoResponse.setFrete(pedido.getFrete());
        pedidoResponse.setIdCliente(pedido.getIdCliente());
        pedidoResponse.setNmCliente(nmCliente);
        pedidoResponse.setEndereco(endereco);
        pedidoResponse.setPacote(pacoteResponse);
        pedidoResponse.setStatus(pedido.getStatus());
        pedidoResponse.setIdFomPagamento(pedido.getIdFormaPagamento());
        pedidoResponse.setObservacao(pedido.getObservacao());
        pedidoResponse.setIdFormaEntrega(pedido.getIdFormaEntrega());
        return pedidoResponse;
    }
}
