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
@Table(name = "Pacote_FormaPagamento")
public class PacoteFormaPagamento implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int iidPacote;
    @Column(nullable = false)
    protected int idFormaPagamento;
}
