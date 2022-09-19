package com.gmail.andersoninfonet.algalog.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gmail.andersoninfonet.algalog.domain.model.enums.StatusEntrega;
import com.gmail.andersoninfonet.algalog.exception.NegocioException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * {@summary A representation of Entrega Entity}
 * @since 0.0.1
 */
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

    @OneToMany(mappedBy = "entrega", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Ocorrencia> ocorrencias = new HashSet<>();

    @Embedded
    private Destinatario destinatario;
    
    private BigDecimal taxa;

    @Enumerated(EnumType.STRING)
    private StatusEntrega statusEntrega;

    private LocalDateTime dataPedido;
    private LocalDateTime dataFinalizacao;

    public Ocorrencia adicionarOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(LocalDateTime.now());
        ocorrencia.setEntrega(this);

        this.getOcorrencias().add(ocorrencia);

        return ocorrencia;
    }

    public void finalizar() {
        if(!podeSerFinalizada()) {
            throw new NegocioException("Entrega não pode ser finalizada.");
        }
        setStatusEntrega(StatusEntrega.FINALIZADA);
        setDataFinalizacao(LocalDateTime.now());
    }

    private boolean podeSerFinalizada() {
        return StatusEntrega.PENDENTE.equals(getStatusEntrega());
    }
}
