package com.example.market.infraestructure.repositories;

import com.example.market.domain.dto.PagoDTO;
import com.example.market.domain.repository.IPago;
import com.example.market.infraestructure.crud.PagoRepository;
import com.example.market.infraestructure.mapper.PagoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PagoImp implements IPago {
    @Autowired
    private PagoRepository repository;

    @Autowired
    private PagoMapper mapper;

    @Override
    public List<PagoDTO> getAll() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public Optional<PagoDTO> getPago(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public PagoDTO save(PagoDTO pago) {
        return mapper.toDto(repository.save(mapper.toEntity(pago)));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<PagoDTO> getByOrdenId(Long ordenId) {
        return mapper.toDto(repository.findByOrdenId(ordenId));
    }
}