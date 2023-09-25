/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.controller;

import com.notimeforwaste.dto.ProdutoDTO;
import com.notimeforwaste.model.Produto;
import com.notimeforwaste.service.ProdutoService;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Arthur
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/notimeforwaste/pacotes/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ProdutoDTO produtoDTO) {
        var produto = new Produto();
        BeanUtils.copyProperties(produtoDTO, produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") int id) {
        Produto produto = produtoService.findById(id);
        if (produto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") int id) {
        Produto produto = produtoService.findById(id);
        if (produto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        produtoService.delete(produto.getIdProduto());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") int id,
            @RequestBody @Valid ProdutoDTO produtoDTO) {
        Produto produtoOptional = produtoService.findById(id);
        if (produtoOptional == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        var produto = new Produto();
        BeanUtils.copyProperties(produtoOptional, produto);
        produtoService.update(produto);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }
}
