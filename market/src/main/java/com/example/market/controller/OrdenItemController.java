package com.example.market.controller;

import com.example.market.domain.dto.OrdenItemDTO;
import com.example.market.domain.service.OrdenItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ordenes/{ordenId}/items")
public class OrdenItemController {

    private final OrdenItemService service;

    public OrdenItemController(OrdenItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<OrdenItemDTO> getAll(@PathVariable Long ordenId) {
        return service.getByOrden(ordenId);
    }

    @GetMapping("/{id}")
    public Optional<OrdenItemDTO> getItem(@PathVariable Long id) {
        return service.getItem(id);
    }

    @PostMapping
    public OrdenItemDTO create(@PathVariable Long ordenId, @RequestBody OrdenItemDTO item) {
        item.setOrderId(ordenId);
        return service.save(item);
    }

    @PutMapping("/{id}")
    public OrdenItemDTO update(@PathVariable Long id, @PathVariable Long ordenId, @RequestBody OrdenItemDTO item) {
        item.setId(id);
        item.setOrderId(ordenId);
        return service.save(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
