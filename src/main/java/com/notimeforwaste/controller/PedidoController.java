/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.controller;

import com.notimeforwaste.model.Pacote;
import com.notimeforwaste.model.Pedido;
import com.notimeforwaste.response.PacoteResponse;
import com.notimeforwaste.response.PedidoResponse;
import com.notimeforwaste.service.ClienteService;
import com.notimeforwaste.service.EnderecoService;
import com.notimeforwaste.service.FormaEntregaService;
import com.notimeforwaste.service.FormaPagamentoService;
import com.notimeforwaste.service.PacoteService;
import com.notimeforwaste.service.PedidoService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author arthu
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/notimeforwaste/pedido")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ClienteService clienteService;
    private final PacoteService pacoteService;
    private final EnderecoService enderecoService;
    private final FormaPagamentoService foraPagamentoService;
    private final FormaEntregaService formaEntregaService;

    public PedidoController(
            PedidoService pedidoService,
            FormaPagamentoService foraPagamentoService,
            FormaEntregaService formaEntregaService,
            ClienteService clienteService,
            PacoteService pacoteService,
            EnderecoService enderecoService) {
        this.pedidoService = pedidoService;
        this.clienteService = clienteService;
        this.pacoteService = pacoteService;
        this.enderecoService = enderecoService;
        this.formaEntregaService = formaEntregaService;
        this.foraPagamentoService = foraPagamentoService;
    }

    @PostMapping({ "", "/" })
    @Transactional
    public ResponseEntity<Object> save(@RequestBody Pedido pedido) {

        Pacote pacote = this.pacoteService.findById(pedido.getIdPacote());

        if (pacote != null) {
            if (pedidoService.existsByIdPacote(pedido.getIdPacote()) <= 0) {
                if (clienteService.existsById(pedido.getIdCliente()) <= 0) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Cliente não encontrado.");
                }

                if (enderecoService.existsById(pedido.getIdEndereco()) <= 0) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Endereco inválido.!");
                }

                if (formaEntregaService.existsById(pedido.getIdFormaEntrega()) <= 0) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Forma de entrega inválida.");
                }

                if (foraPagamentoService.existsById(pedido.getIdFormaPagamento()) <= 0) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Forma de pagamento inválida.");
                }
                Pedido res = pedidoService.save(pedido);
                return res != null ?  ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(res)) : ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao salvar pedido!");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Lamentamos, mas esse pacote não está mais disponível para compra.");

            }
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O pacote selecionado não existe no sistema.");

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStatus(@PathVariable(value = "id") int id,
            @RequestBody String status) {
        if (pedidoService.findById(id) != null) {
            if (status.isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("O status do pedido não pode ser vzio!");
            }

            int res = pedidoService.updateStatus(status, id);
            return res > 0 ? ResponseEntity.status(HttpStatus.OK).body(status) : ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao atualizzar status");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Pedido não encontrado!");

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByIdPedido(@PathVariable(value = "id") int id) {
        Pedido pedido = pedidoService.findById(id);
        if (pedido != null) {
            PedidoResponse pedidoResponse = pedidoService.findResponseByIdPedido(pedido.getIdPedido());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(pedidoResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado!");
    }

    @GetMapping("/empresa/{idEmpresa}")
    public ResponseEntity<Object> getByIdEmpresa(@PathVariable(value = "idEmpresa") int id) {
        List<PedidoResponse> pedidosResponse = pedidoService.findByCompanyId(id);

        if (pedidosResponse != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(pedidosResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado!");
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<Object> getByIdCliente(@PathVariable(value = "idCliente") int id) {
        List<PedidoResponse> pedidosResponse = pedidoService.findByClientId(id);
        if (pedidosResponse != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(pedidosResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado!");
    }
}
