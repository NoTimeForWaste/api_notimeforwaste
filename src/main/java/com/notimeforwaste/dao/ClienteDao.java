/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.dao;

import com.notimeforwaste.model.Cliente;
import com.notimeforwaste.response.ClienteResponse;

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
// @RegisterBeanMapper(Cliente.class)
@RegisterBeanMapper(Cliente.class)
public interface ClienteDao {

        @GetGeneratedKeys
        @SqlUpdate("insert into Cliente (nmCliente, senha, email) values (:nmCliente, :senha, :email)")
        int insert(@BindBean Cliente cliente);

        @SqlQuery("select * from Cliente")
        List<ClienteResponse> findAll();

        @SqlQuery("select * from Cliente where idCliente = :idCliente")
        ClienteResponse findById(@Bind("idCliente") int idCliente);

        @SqlQuery("select * from Cliente where email = :email")
        ClienteResponse findByEmail(@Bind("email") String email);

        @SqlQuery("select count(*) "
                        + " from Cliente "
                        + " where email = :email;")
        int existsByEmail(@Bind("email") String email);

        @SqlQuery("select count(*) "
                        + " from Cliente "
                        + " where idCliente = :idCliente;")
        int existsById(@Bind("idCliente") int idCliente);

        @SqlQuery("select * from Cliente "
                        + " where email = :email and senha = :senha;")
        ClienteResponse login(@Bind("email") String email, @Bind("senha") String senha);

        @SqlUpdate("update Cliente "
                        + " set nmCliente = :nmCliente, "
                        + "     senha = :senha "
                        + " where idCliente = :idCliente and email = :email;")
        int update(@Bind("idCliente") int idCliente, @Bind("nmCliente") String nmCliente,
                        @Bind("senha") String senha, @Bind("email") String email);

        @SqlUpdate("delete "
                        + " from Cliente "
                        + " where idCliente = :idCliente and senha = :senh and email = :email;")
        int delete(@Bind("idCliente") int idCliente, @Bind("senha") String senha, @Bind("email") String email);

}
