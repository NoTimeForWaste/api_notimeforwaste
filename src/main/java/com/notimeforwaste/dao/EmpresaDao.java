/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.notimeforwaste.dao;

import com.notimeforwaste.model.Cliente;
import com.notimeforwaste.model.Empresa;
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
@RegisterBeanMapper(Empresa.class)
public interface EmpresaDao {

        @GetGeneratedKeys
        @SqlUpdate("insert into Empresa (nmEmpresa, CNPJ, senha,idFoto, idEndereco, email, telefone) values (:nmEmpresa, :CNPJ, :senha, :idFoto, :idEndereco, :email, :telefone)")
        int insert(@BindBean Empresa empresa);

        @SqlQuery("select * from Empresa")
        List<Empresa> findAll();

        @SqlQuery("select * from Empresa where idEmpresa = :idEmpresa")
        Empresa findById(@Bind("idEmpresa") int idEmpresa);

        @SqlQuery("select * from Empresa where email = :email")
        Empresa findByEmail(@Bind("email") String email);

        @SqlQuery("select * from Empresa where email = :email and senha = :senha")
        Empresa login(@Bind("email") String email, @Bind("senha") String senha);

        @SqlQuery("select count(*) "
                        + " from Empresa "
                        + " where email = :email;")
        boolean existsByEmail(@Bind("email") String email);

        @SqlQuery("select count(*) "
                        + " from Empresa "
                        + " where CNPJ = :CNPJ;")
        boolean existsByCNPJ(@Bind("CNPJ") String CNPJ);

        @SqlUpdate("update Empresa "
                        + " set nmEmpresa = :nmEmpresa, "
                        + "     senha = :senha, "
                        + "     idFoto = :idFoto, "
                        + "     idEndereco = :idEndereco "
                        + "     telefone = :telefone "
                        + " where idEmpresa = :idEmpresa and email;")
        int update(@BindBean Empresa empresa);

        @SqlUpdate("delete "
                        + " from Empresa "
                        + " where idEmpresa = :idEmpresa and senha = :senh and email = :email;")
        int delete(@Bind("idEmpresa") int idEmpresa, @Bind("email") String email, @Bind("senha") String senha);

}
