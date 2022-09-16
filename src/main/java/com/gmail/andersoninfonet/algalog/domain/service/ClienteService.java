package com.gmail.andersoninfonet.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.andersoninfonet.algalog.domain.model.Cliente;
import com.gmail.andersoninfonet.algalog.domain.repository.ClienteRepository;
import com.gmail.andersoninfonet.algalog.exception.NegocioException;

import lombok.RequiredArgsConstructor;

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
    public Cliente salvar(Cliente cliente) {

        boolean emailEmUso = this.clienteRepository.findByEmail(cliente.getEmail())
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
    public void excluir(Long clienteId) {
        this.clienteRepository.deleteById(clienteId);
    }
}
