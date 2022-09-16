package com.gmail.andersoninfonet.algalog.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.andersoninfonet.algalog.domain.dto.request.EntregaRequest;
import com.gmail.andersoninfonet.algalog.domain.dto.response.EntregaResponse;
import com.gmail.andersoninfonet.algalog.domain.model.Entrega;
import com.gmail.andersoninfonet.algalog.domain.repository.EntregaRepository;
import com.gmail.andersoninfonet.algalog.domain.service.SolicitacaoEntregaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/entregas")
public class EntregaController {
    
    private final EntregaRepository entregaRepository;
    private final SolicitacaoEntregaService solicitacaoEntregaService;

    @PostMapping
    public ResponseEntity<EntregaResponse> solicitar(@Valid @RequestBody EntregaRequest entregaRequest, HttpServletRequest req) throws URISyntaxException {
        
        Entrega entrega = entregaRequest.toEntity(Entrega.class);
        EntregaResponse entregaResponse = solicitacaoEntregaService.solicitar(entrega).toDTO(EntregaResponse.class);

        return ResponseEntity.created(new URI(req.getRequestURI()))
                .body(entregaResponse);
    }

    @GetMapping
    public ResponseEntity<List<EntregaResponse>> listar() {
        return ResponseEntity.ok(
            this.entregaRepository.findAll()
            .stream()
            .map(entrega -> entrega.toDTO(EntregaResponse.class))
            .toList());
    }

    @GetMapping("{entregaId}")
    public ResponseEntity<EntregaResponse> buscar(@PathVariable Long entregaId) {
        return this.entregaRepository.findById(entregaId)
                .map(entrega -> ResponseEntity.ok(entrega.toDTO(EntregaResponse.class)))
                .orElse(ResponseEntity.notFound().build());
    }
}
