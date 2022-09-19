package com.gmail.andersoninfonet.algalog.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmail.andersoninfonet.algalog.domain.model.Cliente;

/**
 * {@summary A Cliente repository presentation for data access and manipulation}
 * @since 0.0.1
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    /**
     * @param nome String
     * @return {@code List<Cliente>}
     * @since 0.0.1
     */
    List<Cliente> findByNomeContaining(String nome);

    /**
     * @param email String
     * @return {@code Optional<Cliente>}
     * @since 0.0.1
     */
    Optional<Cliente> findByEmail(String email);
}
