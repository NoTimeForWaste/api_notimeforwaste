/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.notimeforwaste.dao;

import com.notimeforwaste.model.Cliente_Endereco;
import java.util.List;
import java.util.Optional;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author Arthur
 */
@RegisterBeanMapper(Cliente_Endereco.class)
public interface ClienteEnderecoDao {

    @SqlUpdate("INSERT INTO Cliente_Endereco (idEndereco, idCliente) VALUES (:idEndereco, :idCliente)")
    int insert(@Bind("idEndereco") int idEndereco, @Bind("idCliente") int idCliente);

    @SqlQuery("SELECT * FROM Cliente_Endereco WHERE idCliente = :idCliente")
    List<Cliente_Endereco> findByIdCliente(@Bind("idCliente") int idCliente);

    @SqlQuery("SELECT * FROM Cliente_Endereco WHERE idEndereco = :idEndereco")
    Cliente_Endereco findByIdEndereco(@Bind("idEndereco") int idEndereco);

    @SqlQuery("SELECT * FROM Cliente_Endereco WHERE idEndereco = :idEndereco AND idCliente = :idCliente")
    List<Cliente_Endereco> findByIdEnderecoAndIdCliente(@Bind("idEndereco") int idEndereco, int idCliente);

    @SqlUpdate("DELETE FROM Cliente_Endereco WHERE idEndereco = :idEndereco AND idCliente = :idCliente")
    int delete(@Bind("idEndereco") int idEndereco, @Bind("idCliente") int idCliente);

    @SqlUpdate("DELETE FROM Cliente_Endereco WHERE idCliente = :idCliente")
    int deleteByIdCliente(@Bind("idCliente") int idCliente);

    @SqlUpdate("DELETE FROM Cliente_Endereco WHERE idEndereco = :idEndereco")
    int deleteByIdEndereo(@Bind("idEndereco") int idEndereco);

}
