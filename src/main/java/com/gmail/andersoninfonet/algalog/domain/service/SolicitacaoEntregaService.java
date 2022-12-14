package com.gmail.andersoninfonet.algalog.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.andersoninfonet.algalog.domain.model.Cliente;
import com.gmail.andersoninfonet.algalog.domain.model.Entrega;
import com.gmail.andersoninfonet.algalog.domain.model.enums.StatusEntrega;
import com.gmail.andersoninfonet.algalog.domain.repository.EntregaRepository;

import lombok.RequiredArgsConstructor;

/**
 * {@summary Service class to register Ocorrencia}
 * @since 0.0.1
 */
@RequiredArgsConstructor
@Service
public class SolicitacaoEntregaService {
    
    private final ClienteService clienteService;
    private final EntregaRepository entregaRepository;

    /**
     * @param entrega Entrega
     * @return Entrega
     * @since 0.0.1
     */
    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Cliente cliente = this.clienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setDataPedido(LocalDateTime.now());
        entrega.setStatusEntrega(StatusEntrega.PENDENTE);
        return this.entregaRepository.save(entrega);
    }
}
