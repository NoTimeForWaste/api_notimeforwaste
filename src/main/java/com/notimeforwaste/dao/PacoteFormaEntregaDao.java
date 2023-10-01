package com.notimeforwaste.dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.notimeforwaste.model.PacoteFormaEntrega;
import com.notimeforwaste.model.PacoteFormaPagamento;

@RegisterBeanMapper(PacoteFormaEntrega.class)
public interface PacoteFormaEntregaDao {

    
    @SqlUpdate("INSERT INTO Pacote_FormaEntrega (idPacote, idFormaEntrega) VALUES (:idPacote, :idFormaEntrega)")
    int insert(@BindBean PacoteFormaEntrega pacoteFormaEntrega);

    @SqlQuery("SELECT * FROM Pacote_FormaEntrega WHERE idPacote = :idPacote")
    List<PacoteFormaEntrega> findByIdPacote(@Bind("idPacote") int idPacote);

    @SqlQuery("SELECT * FROM Pacote_FormaEntrega WHERE idFormEntrega = :idFormaEntrega")
    List<PacoteFormaEntrega> findByIdFormaEntrega(@Bind("idFormaEntrega") int idFormaEntrega);

    @SqlQuery("SELECT * FROM Pacote_FormaEntrega WHERE idPacote = :idPacote AND idFormaEntrega == :idFormaEntrega")
    PacoteFormaEntrega findByIdPacoteAndIdFormaEntrega(@BindBean PacoteFormaEntrega pacoteFormaEntrega);;

    @SqlUpdate("DELETE FROM Pacote_FormaEntrega WHERE idPacote = :idPacote AND idFormaEntrega = :idFormaEntrega")
    int delete(@BindBean PacoteFormaEntrega pacoteFormaEntrega);
}
