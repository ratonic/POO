package com.example.market.domain.repository;

import com.example.market.domain.dto.OrdenDTO;

import java.util.List;
import java.util.Optional;

public interface IOrden {
    List<OrdenDTO> getAll();
    Optional<OrdenDTO> getOrden(Long id);
    OrdenDTO save(OrdenDTO order);
    void delete(Long id);
    List<OrdenDTO> getOrdenesPorCliente(Long customerId);
}
