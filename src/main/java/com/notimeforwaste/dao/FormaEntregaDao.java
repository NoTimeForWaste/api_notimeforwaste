/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.notimeforwaste.dao;

import com.notimeforwaste.model.FormaEntrega;
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
@RegisterBeanMapper(FormaEntrega.class)
public interface FormaEntregaDao {

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO FormaEntrega (nome) VALUES (:nome)")
    int insert(@BindBean FormaEntrega formaEntrega);

    @SqlQuery("SELECT * FROM FormaEntrega")
    List<FormaEntrega> findAll();

    @SqlQuery("SELECT * FROM FormaEntrega WHERE idFormaEntrega = :idFormaEntrega")
    FormaEntrega findById(@Bind("idFormaEntrega") int idFormaEntrega);

    @SqlUpdate("UPDATE FormaEntrega SET nome = :nome WHERE idFormaEntrega = :idFormaEntrega")
    int update(@BindBean FormaEntrega formaEntrega);

    @SqlUpdate("DELETE FROM FormaEntrega WHERE idFormaEntrega = :idFormaEntrega")
    int delete(@Bind("idFormaEntrega") int idFormaEntrega);

    @SqlUpdate("SELECT COUNT(*) FROM FormaEntrega  WHERE idFormaEntrega = :idFormaEntrega")
    int existsById(@Bind("idFormaEntrega") int idFormaEntrega);

}
