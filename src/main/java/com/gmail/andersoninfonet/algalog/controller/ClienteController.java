package com.gmail.andersoninfonet.algalog.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.andersoninfonet.algalog.domain.dto.request.ClienteRequest;
import com.gmail.andersoninfonet.algalog.domain.dto.response.ClienteResponse;
import com.gmail.andersoninfonet.algalog.domain.model.Cliente;
import com.gmail.andersoninfonet.algalog.domain.repository.ClienteRepository;
import com.gmail.andersoninfonet.algalog.domain.service.ClienteService;

import lombok.RequiredArgsConstructor;

/**
 * {@summary Cliente resource endpoints}
 * @param clienteRepository ClienteRepository
 * @param clienteService ClienteService
 * @since 0.0.1
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;
    
    /**
     * {@summary GET endpoint to page Cliente} 
     * Example: http://localhost/v1/clientes
     * @param pageable {@link org.springframework.data.domain.Pageable}
     * @return {@code ResponseEntity<Page<ClienteResponse>>}
     * @since 0.0.1
     */
    @GetMapping
    public ResponseEntity<Page<ClienteResponse>> listar(final Pageable pageable) {
        return ResponseEntity.ok(
            this.clienteRepository.findAll(pageable)
            .map(cliente -> cliente.toDTO(ClienteResponse.class)));
    }
    
    /**
     * {@summary GET endpoint to search Cliente by ID} 
     * Example: http://localhost/v1/clientes/50
     * @param clienteId Long
     * @return {@code ResponseEntity<Cliente>}
     * @since 0.0.1
     */
    @GetMapping("{clienteId}")
    public ResponseEntity<ClienteResponse> buscar(@PathVariable final Long clienteId) {
        return clienteRepository.findById(clienteId)
            .map(cliente -> ResponseEntity.ok(cliente.toDTO(ClienteResponse.class)))       
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@summary POST endpoint to create Cliente with JSON body} 
     * Example: http://localhost/v1/clientes
     * @param clienteRequest ClienteRequest
     * @param request {@link javax.servlet.http.HttpServletRequest}
     * @return {@code ResponseEntity<ClienteResponse>}
     * @throws URISyntaxException
     * @since 0.0.1
     */
    @PostMapping
    public ResponseEntity<ClienteResponse> adicionar(@RequestBody @Valid final ClienteRequest clienteRequest, final HttpServletRequest request) throws URISyntaxException {
        final Cliente cliente = clienteRequest.toEntity(Cliente.class);
        final ClienteResponse clienteResponse = this.clienteService.salvar(cliente).toDTO(ClienteResponse.class);
        return ResponseEntity.created(new URI(request.getRequestURI())).body(clienteResponse);
    }

    /**
     * {@summary PUT endpoint to update Cliente with JSON body} 
     * Example: http://localhost/v1/clientes/50
     * @param clienteId Long
     * @param clienteRequest ClienteRequest
     * @return {@code ResponseEntity<Cliente>}
     * @since 0.0.1
     */
    @PutMapping("{clienteId}")
    public ResponseEntity<ClienteResponse> atualizar(@PathVariable final Long clienteId, @RequestBody @Valid final ClienteRequest clienteRequest) {
        
        if(!this.clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        final Cliente cliente = clienteRequest.toEntity(Cliente.class);
        cliente.setId(clienteId);
        final ClienteResponse clienteResponse = this.clienteService.salvar(cliente).toDTO(ClienteResponse.class);

        return ResponseEntity.ok(clienteResponse);
    }

    /**
     * {@summary DELETE endpoint to remove Cliente} 
     * Example: http://localhost/v1/clientes/50
     * @param clienteId Long
     * @return {@code ResponseEntity<Void>}
     * @since 0.0.1
     */
    @DeleteMapping("{clienteId}")
    public ResponseEntity<Void> remover(@PathVariable final Long clienteId) {
        if(!this.clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        this.clienteService.excluir(clienteId);
        return ResponseEntity.noContent().build();
    }
    
}
