package com.example.market.controller;

import com.example.market.domain.dto.PagoDTO;
import com.example.market.domain.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {
    @Autowired
    private PagoService service;

    @GetMapping
    public ResponseEntity<List<PagoDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> getPago(@PathVariable Long id) {
        return service.getPago(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PagoDTO> create(@RequestBody PagoDTO pago) {
        return ResponseEntity.ok(service.save(pago));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> update(@PathVariable Long id, @RequestBody PagoDTO pago) {
        return service.update(id, pago)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/orden/{ordenId}")
    public ResponseEntity<List<PagoDTO>> getByOrdenId(@PathVariable Long ordenId) {
        return ResponseEntity.ok(service.getByOrdenId(ordenId));
    }
}