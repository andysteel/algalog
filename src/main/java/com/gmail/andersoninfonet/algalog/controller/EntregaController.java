package com.gmail.andersoninfonet.algalog.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.andersoninfonet.algalog.domain.dto.request.EntregaRequest;
import com.gmail.andersoninfonet.algalog.domain.dto.response.EntregaResponse;
import com.gmail.andersoninfonet.algalog.domain.model.Entrega;
import com.gmail.andersoninfonet.algalog.domain.repository.EntregaRepository;
import com.gmail.andersoninfonet.algalog.domain.service.FinalizacaoEntregaService;
import com.gmail.andersoninfonet.algalog.domain.service.SolicitacaoEntregaService;

import lombok.RequiredArgsConstructor;

/**
 * {@summary Entrega resource endpoints}
 * @param entregaRepository EntregaRepository
 * @param solicitacaoEntregaService SolicitacaoEntregaService
 * @param finalizacaoEntregaService FinalizacaoEntregaService
 * @since 0.0.1
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/entregas")
public class EntregaController {
    
    private final EntregaRepository entregaRepository;
    private final SolicitacaoEntregaService solicitacaoEntregaService;
    private final FinalizacaoEntregaService finalizacaoEntregaService;

    /**
     * {@summary POST endpoint to create Entrega} 
     * Example: http://localhost/v1/entregas
     * @param entregaRequest EntregaRequest
     * @param req {@link javax.servlet.http.HttpServletRequest}
     * @return {@code ResponseEntity<EntregaResponse>}
     * @throws URISyntaxException
     * @since 0.0.1
     */
    @PostMapping
    public ResponseEntity<EntregaResponse> solicitar(@Valid @RequestBody final EntregaRequest entregaRequest, final HttpServletRequest req) throws URISyntaxException {
        
        final Entrega entrega = entregaRequest.toEntity(Entrega.class);
        final EntregaResponse entregaResponse = solicitacaoEntregaService.solicitar(entrega).toDTO(EntregaResponse.class);

        return ResponseEntity.created(new URI(req.getRequestURI()))
                .body(entregaResponse);
    }

    /**
     * {@summary PATCH endpoint to finish Entrega} 
     * Example: http://localhost/v1/entregas/50
     * @param entregaId Long
     * @return {@code ResponseEntity<Void>}
     * @since 0.0.1
     */
    @PatchMapping("{entregaId}/finalizacao")
    public ResponseEntity<Void> finalizar(@PathVariable final Long entregaId) {
        this.finalizacaoEntregaService.finalizar(entregaId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@summary GET endpoint to page Entrega} 
     * Example: http://localhost/v1/entregas
     * @param pageable Pageable
     * @return {@code ResponseEntity<Page<EntregaResponse>>}
     * @since 0.0.1
     */
    @GetMapping
    public ResponseEntity<Page<EntregaResponse>> listar(final Pageable pageable) {

        return ResponseEntity.ok(
            this.entregaRepository.findAll(pageable)
            .map(entrega -> entrega.toDTO(EntregaResponse.class)));
    }

    
    /**
     * {@summary GET endpoint to search Entrega By ID} 
     * Example: http://localhost/v1/entregas/50
     * @param entregaId Long
     * @return {@code ResponseEntity<EntregaResponse>}
     * @since 0.0.1
     */
    @GetMapping("{entregaId}")
    public ResponseEntity<EntregaResponse> buscar(@PathVariable final Long entregaId) {
        return this.entregaRepository.findById(entregaId)
                .map(entrega -> ResponseEntity.ok(entrega.toDTO(EntregaResponse.class)))
                .orElse(ResponseEntity.notFound().build());
    }
}
