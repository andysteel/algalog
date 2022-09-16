package com.gmail.andersoninfonet.algalog.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gmail.andersoninfonet.algalog.domain.model.enums.StatusEntrega;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Table(name = "entrega", schema = "log")
@Entity
public class Entrega implements ConverterModel {
    
    @EqualsAndHashCode.Include
    @Id
    @SequenceGenerator(name = "seq_entrega", schema = "log", sequenceName = "seq_entrega", allocationSize = 1)
    @GeneratedValue(generator = "seq_entrega", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Embedded
    private Destinatario destinatario;
    
    private BigDecimal taxa;

    @Enumerated(EnumType.STRING)
    private StatusEntrega statusEntrega;

    private LocalDateTime dataPedido;
    private LocalDateTime dataLocalizacao;
}
