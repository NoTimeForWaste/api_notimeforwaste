package com.notimeforwaste.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Service;

import com.notimeforwaste.dao.PacoteFormaPagamentoDao;
import com.notimeforwaste.model.PacoteFormaPagamento;

@Service
public class PacoteFormaPagamentoService {
    private final PacoteFormaPagamentoDao pacoteFormaPagamentoDao;

    PacoteFormaPagamentoService(Jdbi jdbi) {
        this.pacoteFormaPagamentoDao = jdbi.onDemand(PacoteFormaPagamentoDao.class);
    }

    public PacoteFormaPagamento save(PacoteFormaPagamento pacoteFormaPagamento) {

       int ret = pacoteFormaPagamentoDao.insert(pacoteFormaPagamento);
        return ret > 0 ? pacoteFormaPagamento : null;
    }

    public List<PacoteFormaPagamento> findByIdPacote(int idPacote) {
        return pacoteFormaPagamentoDao.findByIdPacote(idPacote);
    }

    public List<PacoteFormaPagamento> findByIdFormaPagamento(int idFormaPagamento) {
        return pacoteFormaPagamentoDao.findByIdFormaPagamento(idFormaPagamento);
    }

    public PacoteFormaPagamento findByIdFormaPagamentoAndidPacote(PacoteFormaPagamento pacoteFormaPagamento) {
        return pacoteFormaPagamentoDao.findByIdPacoteAndIdFormaPagamento(pacoteFormaPagamento);
    }

    public int delete(PacoteFormaPagamento pacoteFormaPagamento) {
       return pacoteFormaPagamentoDao.delete(pacoteFormaPagamento);
    }
}
