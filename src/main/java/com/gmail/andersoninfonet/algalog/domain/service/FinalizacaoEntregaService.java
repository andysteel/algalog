package com.gmail.andersoninfonet.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.andersoninfonet.algalog.domain.model.Entrega;
import com.gmail.andersoninfonet.algalog.domain.repository.EntregaRepository;

import lombok.RequiredArgsConstructor;

/**
 * {@summary Service class to finish Entrega}
 * @since 0.0.1
 */
@RequiredArgsConstructor
@Service
public class FinalizacaoEntregaService {
    
    private final EntregaService entregaService;
    private final EntregaRepository entregaRepository;

    /**
     * @param entregaId Long
     * @since 0.0.1
     */
    @Transactional
    public void finalizar(Long entregaId) {
        Entrega entrega = this.entregaService.buscar(entregaId);

        entrega.finalizar();

        this.entregaRepository.save(entrega);
    }
}
