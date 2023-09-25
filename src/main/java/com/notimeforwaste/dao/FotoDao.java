/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.dao;

import com.notimeforwaste.model.Foto;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
@RegisterBeanMapper(Foto.class)
public interface FotoDao {

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO Foto (fotoUrl) VALUES (:fotoUrl)")
    int insert(@BindBean Foto foto);

    @SqlQuery("SELECT * FROM Foto")
    List<Foto> findAll();

    @SqlQuery("SELECT * FROM Foto WHERE idFoto = :idFoto")
    Foto findById(@Bind("idFoto") int idFoto);

    @SqlUpdate("UPDATE Foto SET fotoUrl = :fotoUrl WHERE idFoto = :idFoto")
    int update(@Bind("idFoto") int idFoto, @Bind("fotoUrl") String fotoUrl);

    @SqlUpdate("DELETE FROM Foto WHERE idFoto = :idFoto")
    int delete(@Bind("idFoto") int idFoto);

}
