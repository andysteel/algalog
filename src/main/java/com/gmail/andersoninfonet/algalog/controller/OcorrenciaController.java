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

import com.gmail.andersoninfonet.algalog.domain.dto.request.OcorrenciaRequest;
import com.gmail.andersoninfonet.algalog.domain.dto.response.OcorrenciaResponse;
import com.gmail.andersoninfonet.algalog.domain.model.Entrega;
import com.gmail.andersoninfonet.algalog.domain.service.EntregaService;
import com.gmail.andersoninfonet.algalog.domain.service.RegistroOcorrenciaService;

import lombok.RequiredArgsConstructor;

/**
 * {@summary Ocorrencia resource endpoints}
 * @param entregaService EntregaService
 * @param registroOcorrenciaService RegistroOcorrenciaService
 * @since 0.0.1
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private final EntregaService entregaService;
    private final RegistroOcorrenciaService registroOcorrenciaService;
    
    /**
     * {@summary POST endpoint to create Ocorrencia} 
     * Example: http://localhost/v1/entregas/50/ocorrencias
     * @param entregaId Long
     * @param req {@link HttpServletRequest}
     * @param ocorrenciaRequest OcorrenciaRequest
     * @return {@code ResponseEntity<OcorrenciaResponse>}
     * @throws URISyntaxException
     * @since 0.0.1
     */
    @PostMapping
    public ResponseEntity<OcorrenciaResponse> registrar(@PathVariable Long entregaId, HttpServletRequest req,
                                                @Valid @RequestBody OcorrenciaRequest ocorrenciaRequest) throws URISyntaxException {
        OcorrenciaResponse ocorrenciaResponse = this.registroOcorrenciaService.registrar(entregaId, ocorrenciaRequest.getDescricao()).toDTO(OcorrenciaResponse.class);
        return ResponseEntity.created(new URI(req.getRequestURI())).body(ocorrenciaResponse);
    }

    /**
     * {@summary GET endpoint to List Ocorrencias from an Entrega} 
     * Example: http://localhost/v1/entregas/50/ocorrencias
     * @param entregaId Long
     * @return {@code ResponseEntity<List<OcorrenciaResponse>>}
     * @since 0.0.1
     */
    @GetMapping
    public ResponseEntity<List<OcorrenciaResponse>> listar(@PathVariable Long entregaId) {
        Entrega entrega = this.entregaService.buscar(entregaId);
        List<OcorrenciaResponse> ocorrencias = entrega.getOcorrencias()
                                            .stream()
                                            .map((ocorrencia -> ocorrencia.toDTO(OcorrenciaResponse.class)))
                                            .toList();
        return ResponseEntity.ok(ocorrencias);
    }
}
