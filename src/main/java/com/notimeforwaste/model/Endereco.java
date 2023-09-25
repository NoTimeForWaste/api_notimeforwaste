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
@Table(name = "Endereco")
public class Endereco implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idEndereco;
    @Column(nullable = false, length=100)
    private String rua;
    @Column(nullable = false, length=100)
    private String bairro;
    @Column(nullable = false, length=100)
    private String cidade;
    @Column(nullable = false, length=100)
    private String estado;
    @Column(nullable = false, length=8)
    private String cep;
    @Column(nullable = false)
    private int numero;
    @Column(nullable = false, length=100)
    private String complemento;
    @Column(length=100)
    private String apelido;
}
