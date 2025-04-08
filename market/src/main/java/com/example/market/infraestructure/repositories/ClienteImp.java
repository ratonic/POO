package com.example.market.infraestructure.repositories;

import com.example.market.domain.dto.ClienteDTO;
import com.example.market.domain.repository.ICliente;
import com.example.market.infraestructure.crud.ClienteRepository;
import com.example.market.infraestructure.entity.Cliente;
import com.example.market.infraestructure.mapper.ClienteMapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class ClienteImp implements ICliente {

    private final ClienteRepository crud;
    private final ClienteMapper mapper;

    public ClienteImp(ClienteRepository crud, ClienteMapper mapper) {
        this.crud = crud;
        this.mapper = mapper;
    }

    @Override
    public List<ClienteDTO> getAll() {
        return StreamSupport.stream(crud.findAll().spliterator(), false)
                .map(mapper::toClienteDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClienteDTO> getCliente(Long id) {
        return crud.findById(id).map(mapper::toClienteDTO);
    }

    @Override
    public ClienteDTO save(ClienteDTO clienteDTO) {
        Cliente cliente = mapper.toCliente(clienteDTO);
        return mapper.toClienteDTO(crud.save(cliente));
    }

    @Override
    public void delete(Long id) {
        crud.deleteById(id);
    }
}
