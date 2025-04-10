package com.example.market.controller;

import com.example.market.domain.dto.OrdenDTO;
import com.example.market.domain.service.OrdenService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {

    private final OrdenService service;

    public OrdenController(OrdenService service) {
        this.service = service;
    }

    @GetMapping
    public List<OrdenDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<OrdenDTO> getById(@PathVariable Long id) {
        return service.getOrden(id);
    }

    @PostMapping
    public OrdenDTO create(@RequestBody OrdenDTO orden) {
        return service.save(orden);
    }

    @PutMapping("/{id}")
    public OrdenDTO update(@PathVariable Long id, @RequestBody OrdenDTO orden) {
        orden.setId(id);
        return service.save(orden);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<OrdenDTO> getByCliente(@PathVariable Long clienteId) {
        return service.getByCliente(clienteId);
    }
}
