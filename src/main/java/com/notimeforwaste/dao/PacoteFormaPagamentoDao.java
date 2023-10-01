package com.notimeforwaste.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.notimeforwaste.model.PacoteFormaPagamento;

@RegisterBeanMapper(PacoteFormaPagamento.class)
public interface PacoteFormaPagamentoDao {
    
    @SqlUpdate("INSERT INTO Pacote_FormaPagamento (idPacote, idFormaPagamento) VALUES (:idPacote, :idFormaPagamento)")
    int insert(@BindBean PacoteFormaPagamento pacoteFormaPagamento);

    @SqlQuery("SELECT * FROM Pacote_FormaPagamento WHERE idPacote = :idPacote")
    List<PacoteFormaPagamento> findByIdPacote(@Bind("idPacote") int idPacote);

    @SqlQuery("SELECT * FROM Pacote_FormaPagamento WHERE idFormaPagamento = :idFormaPagamento")
    List<PacoteFormaPagamento> findByIdFormaPagamento(@Bind("idFormaPagamento") int idFormaPagamento);

    @SqlQuery("SELECT * FROM Pacote_FormaPagamento WHERE idPacote = :idPacote AND idFormaPagamento == :idFormaPagamento")
    PacoteFormaPagamento findByIdPacoteAndIdFormaPagamento(@BindBean PacoteFormaPagamento pacoteFormaPagamento);;

    @SqlUpdate("DELETE FROM Pacote_FormaPagamento WHERE idPacote = :idPacote AND idFormaPagamento = :idFormaPagamento")
    int delete(@BindBean PacoteFormaPagamento pacoteFormaPagamento);

}
