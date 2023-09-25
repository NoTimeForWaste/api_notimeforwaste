/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.notimeforwaste.dao;

import com.notimeforwaste.model.HorarioFuncionamento;
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
@RegisterBeanMapper(HorarioFuncionamento.class)
public interface HorarioFuncionamentoDao {

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO HorarioFuncionamento (nome, horarioInicial, horarioFinal, idEmpresa) VALUES (:nome, :horarioInicial, :horarioFinal, :idEmpresa)")
    int insert(@BindBean HorarioFuncionamento horario);

    @SqlQuery("SELECT * FROM HorarioFuncionamento")
    List<HorarioFuncionamento> findAll();

    @SqlQuery("SELECT * FROM HorarioFuncionamento WHERE idHorario = :idHorario")
    HorarioFuncionamento findById(@Bind("idHorario") int idHorario);

    @SqlQuery("SELECT * FROM HorarioFuncionamento WHERE idEmpresa = :idEmpresa")
    List<HorarioFuncionamento> findByIdEmpresa(@Bind("idEmpresa") int idEmpresa);

    @SqlUpdate("UPDATE HorarioFuncionamento SET nome = :nome, horarioInicial = :horarioInicial, horarioFinal = :horarioFinal, idEmpresa = :idEmpresa WHERE idHorario = :idHorario")
    int update(@BindBean HorarioFuncionamento horario);

    @SqlUpdate("DELETE FROM HorarioFuncionamento WHERE idHorario = :idHorario")
    int delete(@Bind("idHorario") int idHorario);
}
