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
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(this.clienteRepository.findAll());
    }
    
    /**
     * @param clienteId Long
     * @return ResponseEntity<Cliente>
     */
    @GetMapping("{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable final Long clienteId) {
        return clienteRepository.findById(clienteId)
            .map(ResponseEntity::ok)       
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> adicionar(@RequestBody @Valid final Cliente cliente, final HttpServletRequest request) throws URISyntaxException {
        return ResponseEntity.created(new URI(request.getRequestURI())).body(this.clienteService.salvar(cliente));
    }

    /**
     * @param clienteId Long
     * @param cliente Cliente
     * @return ResponseEntity<Cliente>
     */
    @PutMapping("{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable final Long clienteId, @RequestBody final Cliente cliente) {
        
        if(!this.clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteId);
        return ResponseEntity.ok(this.clienteService.salvar(cliente));
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
