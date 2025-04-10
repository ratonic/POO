package com.example.market.domain.service;

import com.example.market.domain.dto.OrdenDTO;
import com.example.market.domain.repository.IOrden;
import com.example.market.infraestructure.crud.ClienteRepository;
import com.example.market.infraestructure.crud.OrdenRepository;
import com.example.market.infraestructure.entity.Cliente;
import com.example.market.infraestructure.entity.Orden;
import com.example.market.infraestructure.mapper.OrdenMapper;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenService {

    private final IOrden ordenRepo;
    private final OrdenRepository ordenJpa;
    private final ClienteRepository clienteRepo;
    private final OrdenMapper mapper;

    public OrdenService(IOrden ordenRepo,
                        OrdenRepository ordenJpa,
                        ClienteRepository clienteRepo,
                        OrdenMapper mapper) {
        this.ordenRepo = ordenRepo;
        this.ordenJpa = ordenJpa;
        this.clienteRepo = clienteRepo;
        this.mapper = mapper;
    }

    public List<OrdenDTO> getAll() {
        return ordenRepo.getAll();
    }

    public Optional<OrdenDTO> getOrden(Long id) {
        return ordenRepo.getOrden(id);
    }

    public OrdenDTO save(OrdenDTO dto) {
        Orden orden = mapper.toOrden(dto);

        Cliente cliente = clienteRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        orden.setCliente(cliente);

        if (orden.getFecha() == null) {
            orden.setFecha(LocalDateTime.now());
        }

        if (orden.getTotal() == null) {
            orden.setTotal(0.0); // La orden inicia con total 0
        }

        return mapper.toOrderDTO(ordenJpa.save(orden));
    }

    public void delete(Long id) {
        ordenRepo.delete(id);
    }

    public List<OrdenDTO> getByCliente(Long clienteId) {
        return ordenRepo.getOrdenesPorCliente(clienteId);
    }
}
