/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.service;

import com.notimeforwaste.dao.FotoDao;
import com.notimeforwaste.model.FormaEntrega;
import com.notimeforwaste.model.Foto;
import java.util.List;
import java.util.Optional;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arthur
 */
@Service
public class FotoService {

    private final FotoDao fotoDao;

    public FotoService(Jdbi jdbi) {
        this.fotoDao = jdbi.onDemand(FotoDao.class);
    }

    public Foto save(Foto foto) {
        foto.setIdFoto(fotoDao.insert(foto));
        return foto.getIdFoto() > 0 ? foto : null;
    }

    public List<Foto> findAll() {
        return fotoDao.findAll();
    }

    public Foto findById(int id) {
        Foto foto = fotoDao.findById(id);
        return foto.getIdFoto() <= 0 ? null : foto;
    }

    public int delete(int idFoto) {
       return fotoDao.delete(idFoto);
    }

    public int update(Foto foto) {
        return fotoDao.update(foto.getIdFoto(), foto.getFotoUrl());
    }

}
