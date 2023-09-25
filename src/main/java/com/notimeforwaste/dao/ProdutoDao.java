/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.notimeforwaste.dao;

import com.notimeforwaste.model.Produto;
import java.util.List;
import java.util.Optional;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author Arthur
 */
@RegisterBeanMapper(Produto.class)
public interface ProdutoDao {

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO Produto (nmProduto, idPacote, dtValidade, descricao) VALUES (:nmProduto, :idPacote, :dtValidade, :descricao)")
    int insert(@BindBean Produto produto);

    @SqlQuery("SELECT * FROM Produto")
    List<Produto> findAll();

    @SqlQuery("SELECT * FROM Produto WHERE idProduto = :idProduto")
    Produto findById(@Bind("idProduto") int idProduto);

    @SqlUpdate("UPDATE Produto SET nmProduto = :nmProduto, descricao = :descricao idPacote = :idPacote, dtValidade = :dtValidade WHERE idProduto = :idProduto")
    int update(@BindBean Produto produto);

    @SqlUpdate("DELETE FROM Produto WHERE idProduto = :idProduto")
    int delete(@Bind("idProduto") int idProduto);

    @SqlQuery("SELECT * FROM Produto  WHERE idPacote = :idPacote")
    List<Produto> findProdutosByIdPacote(@Bind("idPacote") int idPacote);

}
