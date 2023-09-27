/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.service;

import com.notimeforwaste.dao.ProdutoDao;
import com.notimeforwaste.model.Produto;
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
public class ProdutoService {

    private final ProdutoDao produtoDao;

    public ProdutoService(Jdbi jdbi) {
        this.produtoDao = jdbi.onDemand(ProdutoDao.class);
    }

    public Produto save(Produto produto) {
        produto.setIdProduto(produtoDao.insert(produto));
        return produto.getIdProduto() > 0 ? produto : null;
    }

    public int update(Produto produto) {
       return produtoDao.update(produto);
    }

    public List<Produto> findAll() {
        return produtoDao.findAll();
    }

    public Produto findById(int idProduto) {
        return produtoDao.findById(idProduto);
    }

    public int delete(int idProduto) {
        return produtoDao.delete(idProduto);
    }

    public List<Produto> findByIdPacote(int idPacote) {
        return produtoDao.findProdutosByIdPacote(idPacote);
    }
}
