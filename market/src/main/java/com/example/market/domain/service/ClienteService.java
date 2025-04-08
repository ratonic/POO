package com.example.market.domain.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.market.domain.dto.ClienteDTO;
import com.example.market.domain.repository.ICliente;

@Service
public class ClienteService {
    private final ICliente clienteRepo;

    public ClienteService(ICliente clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    public List<ClienteDTO> getAll() {
        return clienteRepo.getAll();
    }

    public Optional<ClienteDTO> getCliente(Long id) {
        return clienteRepo.getCliente(id);
    }

    public ClienteDTO save(ClienteDTO cliente) {
        return clienteRepo.save(cliente);
    }

    public void delete(Long id) {
        clienteRepo.delete(id);
    }
}
