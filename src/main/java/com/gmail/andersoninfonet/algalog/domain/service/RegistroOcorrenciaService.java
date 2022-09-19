package com.gmail.andersoninfonet.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.andersoninfonet.algalog.domain.model.Entrega;
import com.gmail.andersoninfonet.algalog.domain.model.Ocorrencia;

import lombok.RequiredArgsConstructor;

/**
 * {@summary Service class to register Ocorrencia}
 * @since 0.0.1
 */
@RequiredArgsConstructor
@Service
public class RegistroOcorrenciaService {

    private final EntregaService entregaService;
    
    /**
     * @param entregaId Long
     * @param descricao String
     * @return Ocorrencia
     * @since 0.0.1
     */
    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {
        Entrega entrega = this.entregaService.buscar(entregaId);

        return entrega.adicionarOcorrencia(descricao);
    }
}
