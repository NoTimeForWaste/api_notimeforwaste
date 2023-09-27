package com.notimeforwaste.service;

import java.util.List;

import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import com.notimeforwaste.dao.PacoteFormaEntregaDao;
import com.notimeforwaste.dao.PacoteFormaPagamentoDao;
import com.notimeforwaste.model.PacoteFormaEntrega;
import com.notimeforwaste.model.PacoteFormaPagamento;

@Service
public class PacoteFormaEntregaService {
    private final PacoteFormaEntregaDao pacoteFormaEntregaDao;

    PacoteFormaEntregaService(Jdbi jdbi) {
        this.pacoteFormaEntregaDao = jdbi.onDemand(PacoteFormaEntregaDao.class);
    }

    public PacoteFormaEntrega save(PacoteFormaEntrega pacoteFormaEntrega) {
        int ret = pacoteFormaEntregaDao.insert(pacoteFormaEntrega);
        return ret > 0 ? pacoteFormaEntrega : null;
    }

    public List<PacoteFormaEntrega> findByIdPacote(int idPacote) {
        return pacoteFormaEntregaDao.findByIdPacote(idPacote);
    }

    public List<PacoteFormaEntrega> findByIdFormaEntrega(int idFormaEntrega) {
        return pacoteFormaEntregaDao.findByIdFormaEntrega(idFormaEntrega);
    }

    public PacoteFormaEntrega findByIdFormaEntregaAndidPacote(PacoteFormaEntrega pacoteFormaEntrega) {
        return pacoteFormaEntregaDao.findByIdPacoteAndIdFormaEntrega(pacoteFormaEntrega);
    }

    public int delete(PacoteFormaEntrega pacoteFormaEntrega) {
        return pacoteFormaEntregaDao.delete(pacoteFormaEntrega);
    }

}
