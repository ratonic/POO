package com.example.market.infraestructure.repositories;

import com.example.market.domain.dto.OrdenDTO;
import com.example.market.domain.repository.IOrden;
import com.example.market.infraestructure.crud.OrdenRepository;
import com.example.market.infraestructure.mapper.OrdenMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class OrdenImp implements IOrden {

    private final OrdenRepository ordenRepo;
    private final OrdenMapper mapper;

    public OrdenImp(OrdenRepository ordenRepo, OrdenMapper mapper) {
        this.ordenRepo = ordenRepo;
        this.mapper = mapper;
    }

    @Override
    public List<OrdenDTO> getAll() {
        return StreamSupport.stream(ordenRepo.findAll().spliterator(), false)
                .map(mapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrdenDTO> getOrden(Long id) {
        return ordenRepo.findById(id).map(mapper::toOrderDTO);
    }

    @Override
    public OrdenDTO save(OrdenDTO dto) {
        throw new UnsupportedOperationException("Use OrdenService to save orders.");
    }

    @Override
    public void delete(Long id) {
        ordenRepo.deleteById(id);
    }

    @Override
    public List<OrdenDTO> getOrdenesPorCliente(Long clienteId) {
        return ordenRepo.findByClienteId(clienteId).stream()
                .map(mapper::toOrderDTO)
                .collect(Collectors.toList());
    }
}
