package com.gmail.andersoninfonet.algalog.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmail.andersoninfonet.algalog.domain.model.Entrega;

/**
 * {@summary A Entrega repository presentation for data access and manipulation}
 * @since 0.0.1
 */
@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
    
}
