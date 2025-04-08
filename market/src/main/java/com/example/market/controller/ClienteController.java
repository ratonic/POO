package com.example.market.controller;


import org.springframework.web.bind.annotation.*;

import com.example.market.domain.dto.ClienteDTO;
import com.example.market.domain.service.ClienteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClienteDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<ClienteDTO> getCliente(@PathVariable Long id) {
        return service.getCliente(id);
    }

    @PostMapping
    public ClienteDTO save(@RequestBody ClienteDTO cliente) {
        return service.save(cliente);
    }

    @PutMapping("/{id}")
    public ClienteDTO update(@PathVariable Long id, @RequestBody ClienteDTO cliente) {
        cliente.setId(id);
        return service.save(cliente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
