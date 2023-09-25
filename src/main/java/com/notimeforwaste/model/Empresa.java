package com.notimeforwaste.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Empresa")
public class Empresa implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idEmpresa;
    @Column(nullable = false, length=100)
    private String nmEmpresa;
    @Column(nullable = false, length=14)
    private String CNPJ;
    @Column(nullable = false, length=15)
    private String senha;
    @Column(nullable = false)
    private int idFoto;
    @Column(nullable = false)
    private int idEndereco;
}
