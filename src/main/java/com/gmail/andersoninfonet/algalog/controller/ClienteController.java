package com.gmail.andersoninfonet.algalog.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final ClienteService clienteService;
    
    /**
     * @return ResponseEntity<List<Cliente>>
     */
    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listar() {
        return ResponseEntity.ok(
            this.clienteRepository.findAll()
            .stream()
            .map(cliente -> cliente.toDTO(ClienteResponse.class))
            .toList());
    }
    
    /**
     * @param clienteId Long
     * @return ResponseEntity<Cliente>
     */
    @GetMapping("{clienteId}")
    public ResponseEntity<ClienteResponse> buscar(@PathVariable final Long clienteId) {
        return clienteRepository.findById(clienteId)
            .map(cliente -> ResponseEntity.ok(cliente.toDTO(ClienteResponse.class)))       
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> adicionar(@RequestBody @Valid final ClienteRequest clienteRequest, final HttpServletRequest request) throws URISyntaxException {
        Cliente cliente = clienteRequest.toEntity(Cliente.class);
        ClienteResponse clienteResponse = this.clienteService.salvar(cliente).toDTO(ClienteResponse.class);
        return ResponseEntity.created(new URI(request.getRequestURI())).body(clienteResponse);
    }

    /**
     * @param clienteId Long
     * @param cliente Cliente
     * @return ResponseEntity<Cliente>
     */
    @PutMapping("{clienteId}")
    public ResponseEntity<ClienteResponse> atualizar(@PathVariable final Long clienteId, @RequestBody final ClienteRequest clienteRequest) {
        
        if(!this.clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        Cliente cliente = clienteRequest.toEntity(Cliente.class);
        cliente.setId(clienteId);
        ClienteResponse clienteResponse = this.clienteService.salvar(cliente).toDTO(ClienteResponse.class);

        return ResponseEntity.ok(clienteResponse);
    }

    /**
     * @param clienteId Long
     * @return void
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
