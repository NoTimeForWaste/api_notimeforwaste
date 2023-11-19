/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.notimeforwaste.dao;

import com.notimeforwaste.model.FormaEntrega;
import com.notimeforwaste.model.FormaPagamento;
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
@RegisterBeanMapper(FormaPagamento.class)
public interface FormaPagamentoDao {

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO FormaPagamento (nome) VALUES (:nome)")
    int insert(@BindBean FormaPagamento formaPagamento);

    @SqlQuery("SELECT * FROM FormaPagamento")
    List<FormaPagamento> findAll();

    @SqlQuery("SELECT * FROM FormaPagamento WHERE idFormaPagamento = :idFormaPagamento")
    FormaPagamento findById(@Bind("idFormaPagamento") int idFormaPagamento);

    @SqlUpdate("UPDATE FormaPagamento SET nome = :nome WHERE idFormaPagamento = :idFormaPagamento")
    int update(@BindBean FormaPagamento formaPagamento);

    @SqlUpdate("DELETE FROM FormaPagamento WHERE idFormaPagamento = :idFormaPagamento")
    int delete(@Bind("idFormaPagamento") int idFormaPagamento);

    @SqlQuery("SELECT COUNT(*) FROM FormaPagamento WHERE idFormaPagamento = :idFormaPagamento;")
    int existsById(@Bind("idFormaPagamento") int idFormaPagamento);
}
