/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.service;

import com.notimeforwaste.dao.EmpresaDao;
import com.notimeforwaste.model.Empresa;
import com.notimeforwaste.response.EmpresaResponse;

import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Jdbi;
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
        return empresa.getIdEmpresa() > 0 ? empresa : null;
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

    public List<EmpresaResponse> findAll() {
        List<Empresa> empresas = empresaDao.findAll();
        List<EmpresaResponse> empresasResponses = new ArrayList<EmpresaResponse>();
        if (empresas != null) {

            for (Empresa empresa : empresas) {
                EmpresaResponse empmResponse = new EmpresaResponse(empresa.getIdEmpresa(), empresa.getNmEmpresa(),
                        empresa.getCNPJ(), empresa.getEmail(), empresa.getIdFoto(), empresa.getIdEndereco(),
                        empresa.getTelefone());
                empresasResponses.add(empmResponse);
            }
        }
        return empresasResponses;
    }

    public EmpresaResponse findById(int id) {
        Empresa empresa = empresaDao.findById(id);
        EmpresaResponse empresaResponse = new EmpresaResponse();
        if (empresa != null) {
            empresaResponse.setCNPJ(empresa.getCNPJ());
            empresaResponse.setEmail(empresa.getEmail());
            empresaResponse.setIdEmpresa(empresa.getIdEmpresa());
            empresaResponse.setIdEndereco(empresa.getIdEndereco());
            empresaResponse.setIdFoto(empresa.getIdFoto());
            empresaResponse.setNmEmpresa(empresa.getNmEmpresa());
            empresaResponse.setTelefone(empresa.getTelefone());
        }
        return empresaResponse;
    }

    public EmpresaResponse findByEmail(String email) {

        Empresa empresa = empresaDao.findByEmail(email);
        EmpresaResponse empresaResponse = new EmpresaResponse();
        if (empresa != null) {
            empresaResponse.setCNPJ(empresa.getCNPJ());
            empresaResponse.setEmail(empresa.getEmail());
            empresaResponse.setIdEmpresa(empresa.getIdEmpresa());
            empresaResponse.setIdEndereco(empresa.getIdEndereco());
            empresaResponse.setIdFoto(empresa.getIdFoto());
            empresaResponse.setNmEmpresa(empresa.getNmEmpresa());
            empresaResponse.setTelefone(empresa.getTelefone());
        }
        return empresaResponse;
    }

    public EmpresaResponse login(String email, String senha) {
        Empresa empresa = empresaDao.login(email, senha);
        EmpresaResponse empresaResponse = new EmpresaResponse();
        if (empresa != null) {
            empresaResponse.setCNPJ(empresa.getCNPJ());
            empresaResponse.setEmail(empresa.getEmail());
            empresaResponse.setIdEmpresa(empresa.getIdEmpresa());
            empresaResponse.setIdEndereco(empresa.getIdEndereco());
            empresaResponse.setIdFoto(empresa.getIdFoto());
            empresaResponse.setNmEmpresa(empresa.getNmEmpresa());
            empresaResponse.setTelefone(empresa.getTelefone());
        }
        return empresaResponse;

    }

    public int delete(int idEmpresa, String email, String senha) {
        return empresaDao.delete(idEmpresa, email, senha);
    }
}
