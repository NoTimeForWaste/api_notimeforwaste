/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.notimeforwaste.dao;

import com.notimeforwaste.model.Cliente;
import com.notimeforwaste.model.Endereco;
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
@RegisterBeanMapper(Endereco.class)
public interface EnderecoDao {

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO Endereco (rua, bairro, cidade, estado, cep, numero, complemento, apelido) VALUES (:rua, :bairro, :cidade, :estado, :cep, :numero, :complemento, :apelido)")
    int insert(@BindBean Endereco endereco);

    @SqlQuery("SELECT * FROM Endereco")
    List<Endereco> findAll();

    @SqlQuery("SELECT * FROM Endereco WHERE idEndereco = :idEndereco")
    Endereco findById(@Bind("idEndereco") int idEndereco);

    @SqlUpdate("UPDATE Endereco SET rua = :rua, bairro = :bairro, cidade = :cidade, estado = :estado, cep = :cep, numero = :numero, complemento = :complemento, apelido = :apelido WHERE idEndereco = :idEndereco")
    int update(@BindBean Endereco endereco);

    @SqlUpdate("DELETE FROM Endereco WHERE idEndereco = :idEndereco")
    int delete(@Bind("idEndereco") int idEndereco);

    @SqlQuery("select count(*) "
            + " from Endereco "
            + " where idEndereco = :idEndereco;")
    int existsById(@Bind("idEndereco") int idEndereco);

}
