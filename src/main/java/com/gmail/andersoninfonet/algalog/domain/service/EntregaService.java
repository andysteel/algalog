package com.gmail.andersoninfonet.algalog.domain.service;

import org.springframework.stereotype.Service;

import com.gmail.andersoninfonet.algalog.domain.model.Entrega;
import com.gmail.andersoninfonet.algalog.domain.repository.EntregaRepository;
import com.gmail.andersoninfonet.algalog.exception.EntidadeNaoEncontradaException;

import lombok.RequiredArgsConstructor;

/**
 * {@summary Service class to manipulate Entrega entity}
 * @since 0.0.1
 */
@RequiredArgsConstructor
@Service
public class EntregaService {
    
    private final EntregaRepository entregaRepository;

    /**
     * @param entregaId Long
     * @return Entrega
     * @since 0.0.1
     */
    public Entrega buscar(final Long entregaId) {
        return this.entregaRepository.findById(entregaId)
        .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada."));
    }
}
