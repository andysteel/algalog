package com.gmail.andersoninfonet.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.andersoninfonet.algalog.domain.model.Cliente;
import com.gmail.andersoninfonet.algalog.domain.repository.ClienteRepository;
import com.gmail.andersoninfonet.algalog.exception.NegocioException;

import lombok.RequiredArgsConstructor;

/**
 * {@summary Classe de serviço de operações de banco de dados}
 * @param clienteRepository ClienteRepository
 * @since 0.0.1
 */
@Service
@RequiredArgsConstructor
public class ClienteService {
    
    private final ClienteRepository clienteRepository;

    /**
     * @param cliente Cliente
     * @return Cliente
     * @since 0.0.1
     */
    @Transactional
    public Cliente salvar(final Cliente cliente) {

        final boolean emailEmUso = this.clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

            if(emailEmUso) {
                throw new NegocioException("Já existe um cliente cadastrado com este email");
            }

        return this.clienteRepository.save(cliente);
    }

    /**
     * @param clienteId Long
     * @since 0.0.1
     */
    @Transactional
    public void excluir(final Long clienteId) {
        this.clienteRepository.deleteById(clienteId);
    }

    /**
     * @param clienteId Long
     * @return Cliente
     * @throws NegocioException
     * @since 0.0.1
     */
    public Cliente buscar(final Long clienteId) {
        return this.clienteRepository.findById(clienteId)
            .orElseThrow(() -> new NegocioException("Cliente não encontrado."));
    }
}
