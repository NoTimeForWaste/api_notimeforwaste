/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.service;

import com.notimeforwaste.dao.EmpresaDao;
import com.notimeforwaste.model.Empresa;
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
public class EmpresaService {

    private final EmpresaDao empresaDao;

    public EmpresaService(Jdbi jdbi) {
        this.empresaDao = jdbi.onDemand(EmpresaDao.class);
    }

    public Empresa save(Empresa empresa) {
        empresa.setIdEmpresa(empresaDao.insert(empresa));
        return empresa;
    }

    public int update(Empresa empresa) {
        return empresaDao.update(empresa);
    }

    public boolean existsByEmail(String email) {
        return empresaDao.existsByEmail(email);
    }

    public boolean existsByCNPJ(String CNPJ) {
        return empresaDao.existsByCNPJ(CNPJ);
    }

    public List<Empresa> findAll() {
        return empresaDao.findAll();
    }

    public Empresa findById(int id) {
        return empresaDao.findById(id);
    }

    public Empresa findByEmail(String email) {
        return empresaDao.findByEmail(email);
    }

    public Empresa login(String email, String senha) {
        return empresaDao.login(email, senha);
    }

    public int delete(int idEmpresa, String email, String senha) {
        return empresaDao.delete(idEmpresa, email, senha);
    }
}
