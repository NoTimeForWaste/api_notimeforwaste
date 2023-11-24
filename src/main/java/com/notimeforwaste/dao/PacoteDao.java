/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.notimeforwaste.dao;

import com.notimeforwaste.model.Pacote;
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
@RegisterBeanMapper(Pacote.class)
public interface PacoteDao {

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO Pacote (nmPacote, preco, idEmpresa, idFoto) VALUES (:nmPacote, :preco, :idEmpresa, :idFoto)")
    int insert(@BindBean Pacote pacote);

    @SqlQuery("SELECT * FROM Pacote p WHERE NOT EXISTS (SELECT 1 FROM Pedido ped WHERE ped.idPacote = p.idPacote)")
    List<Pacote> findAll();

    @SqlQuery("SELECT * FROM Pacote WHERE idEmpresa = :idEmpresa AND idPacote NOT IN (SELECT idPacote FROM Pedido)")
    List<Pacote> findByIdEmpresa(@Bind("idEmpresa") int idEmpresa);

    @SqlQuery("SELECT * FROM Pacote WHERE idPacote = :idPacote")
    Pacote findById(@Bind("idPacote") int idPacote);

    @SqlUpdate("UPDATE Pacote SET nmPacote = :nmPacote, preco = :preco, idFoto = :idFoto WHERE idPacote = :idPacote")
    int update(@BindBean Pacote pacote);

    @SqlUpdate("DELETE FROM Pacote WHERE idPacote = :idPacote")
    int delete(@Bind("idPacote") int idPacote);

}
