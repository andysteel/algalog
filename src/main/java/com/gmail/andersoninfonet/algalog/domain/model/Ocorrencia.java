package com.gmail.andersoninfonet.algalog.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * {@summary A representation of Ocorrencia Entity}
 * @since 0.0.1
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "ocorrencia", schema = "log")
@Entity
public class Ocorrencia implements ConverterModel {
    
    @Id
    @SequenceGenerator(name = "seq_ocorrencia", schema = "log", sequenceName = "seq_ocorrencia", allocationSize = 1)
    @GeneratedValue(generator = "seq_ocorrencia", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entrega_id", nullable = false)
    private Entrega entrega;

    @Column(name = "descricao", nullable = false, length = 200)
    private String descricao;

    @Column(name = "data_registro", nullable = false)
    private LocalDateTime dataRegistro;
}
