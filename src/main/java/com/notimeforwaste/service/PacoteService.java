/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.service;

import com.notimeforwaste.dao.PacoteDao;
import com.notimeforwaste.model.Empresa;
import com.notimeforwaste.model.FormaEntrega;
import com.notimeforwaste.model.FormaPagamento;
import com.notimeforwaste.model.Foto;
import com.notimeforwaste.model.Pacote;
import com.notimeforwaste.model.PacoteFormaEntrega;
import com.notimeforwaste.model.PacoteFormaPagamento;
import com.notimeforwaste.model.Produto;
import com.notimeforwaste.response.EmpresaResponse;
import com.notimeforwaste.response.PacoteResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arthur
 */
@Service
public class PacoteService {
    private final PacoteDao pacoteDao;
    private final FormaPagamentoService foraPagamentoService;
    private final FormaEntregaService formaEntregaService;
    private final EmpresaService empresaService;
    private final FotoService fotoService;
    private final ProdutoService produtoService;
    private final PacoteFormaEntregaService pacoteFormaEntregaService;
    private final PacoteFormaPagamentoService pacoteFormaPagamentoService;

    public PacoteService(Jdbi jdbi,
            ProdutoService produtoService,
            FormaPagamentoService foraPagamentoService,
            FormaEntregaService formaEntregaService,
            FotoService fotoService,
            PacoteFormaEntregaService pacoteFormaEntregaService,
            PacoteFormaPagamentoService pacoteFormaPagamentoService,
            EmpresaService empresaService) {
        this.pacoteDao = jdbi.onDemand(PacoteDao.class);
        this.produtoService = produtoService;
        this.fotoService = fotoService;
        this.formaEntregaService = formaEntregaService;
        this.foraPagamentoService = foraPagamentoService;
        this.empresaService = empresaService;
        this.pacoteFormaEntregaService = pacoteFormaEntregaService;
        this.pacoteFormaPagamentoService = pacoteFormaPagamentoService;
    }

    public Pacote save(Pacote pacote) {
        pacote.setIdPacote(pacoteDao.insert(pacote));
        System.out.println("Aqui:" + pacote);
        return pacote.getIdPacote() > 0 ? pacote : null;
    }

    public List<PacoteResponse> findAll() {
        List<Pacote> pacotes = pacoteDao.findAll();
        List<PacoteResponse> pacotesResponses = new ArrayList<PacoteResponse>();
        for (int i = 0; i < pacotes.size(); i++) {
            PacoteResponse pacoteResponse = this.getResponseById(pacotes.get(i).getIdPacote());
            pacotesResponses.add(pacoteResponse);
        }

        return pacotesResponses;
    }

    public List<PacoteResponse> findAllByIdEmpresa(int idEmpresa) {
        System.out.println("fyugchdkxmlsç," + idEmpresa);
        List<Pacote> pacotes = pacoteDao.findByIdEmpresa(idEmpresa);
        List<PacoteResponse> pacotesResponses = new ArrayList<PacoteResponse>();
        for (int i = 0; i < pacotes.size(); i++) {
            PacoteResponse pacoteResponse = this.getResponseById(pacotes.get(i).getIdPacote());
            pacotesResponses.add(pacoteResponse);
        }

        return pacotesResponses;
    }

    public Pacote findById(int id) {
        return pacoteDao.findById(id);
    }

    public int delete(int idPacote) {
        try {
            Pacote pacote = pacoteDao.findById(idPacote);
            if (pacote == null) {
                return -1;
            }

            List<Produto> produtos = produtoService.findByIdPacote(idPacote);
            for (Produto produto : produtos) {
                produtoService.delete(produto.getIdProduto());
            }

            List<PacoteFormaEntrega> pacoteFormaEntregas = pacoteFormaEntregaService.findByIdPacote(idPacote);
            for (PacoteFormaEntrega formaEntrega : pacoteFormaEntregas) {
                pacoteFormaEntregaService.delete(formaEntrega);
            }

            List<PacoteFormaPagamento> pacoteFormaPagamentos = pacoteFormaPagamentoService.findByIdPacote(idPacote);
            for (PacoteFormaPagamento formaPagamento : pacoteFormaPagamentos) {
                pacoteFormaPagamentoService.delete(formaPagamento);
            }

            pacoteDao.delete(idPacote);
            fotoService.delete(pacote.getIdFoto());
            return 1;
        } catch (Exception e) {

            return -1;
        }
    }

    public int update(Pacote pacote) {
        return pacoteDao.update(pacote);
    }

    public PacoteResponse getResponseById(int idPacote) {

        Pacote pacote = findById(idPacote);

        PacoteResponse pacoteResponse = new PacoteResponse();
        EmpresaResponse empresa = empresaService.findById(pacote.getIdEmpresa());
        Foto foto = fotoService.findById(pacote.getIdFoto());
        List<Produto> produtos = produtoService.findByIdPacote(pacote.getIdPacote());
        List<PacoteFormaEntrega> pacoteFormaEntregaList = pacoteFormaEntregaService.findByIdPacote(idPacote);
        List<PacoteFormaPagamento> pacoteFormaPagamentoList = pacoteFormaPagamentoService.findByIdPacote(idPacote);
        List<FormaPagamento> formaPagamentosList = new ArrayList<FormaPagamento>();
        List<FormaEntrega> formaEntregaList = new ArrayList<FormaEntrega>();

        for (int i = 0; i < pacoteFormaPagamentoList.size(); i++) {
            FormaPagamento formaPagamento = foraPagamentoService
                    .findById(pacoteFormaPagamentoList.get(i).getIdFormaPagamento());
            formaPagamentosList.add(formaPagamento);
        }
        pacoteResponse.setFormasPagamentos(formaPagamentosList);

        for (int i = 0; i < pacoteFormaEntregaList.size(); i++) {
            FormaEntrega formaEntrega = formaEntregaService.findById(pacoteFormaEntregaList.get(i).getIdFormaEntrega());
            formaEntregaList.add(formaEntrega);
        }
        pacoteResponse.setFormasEntregas(formaEntregaList);

        pacoteResponse.setIdPacote(pacote.getIdPacote());
        pacoteResponse.setFoto(foto);
        pacoteResponse.setIdEmpresa(empresa.getIdEmpresa());
        pacoteResponse.setNmPacote(pacote.getNmPacote());
        pacoteResponse.setNmEmpresa(empresa.getNmEmpresa());
        pacoteResponse.setPreco(pacote.getPreco());
        pacoteResponse.setProdutos(produtos);
        return pacoteResponse;
    }
}
