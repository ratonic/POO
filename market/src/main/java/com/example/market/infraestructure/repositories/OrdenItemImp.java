package com.example.market.infraestructure.repositories;

import com.example.market.domain.dto.OrdenItemDTO;
import com.example.market.domain.repository.IOrdenItem;
import com.example.market.infraestructure.crud.OrdenItemRepository;
import com.example.market.infraestructure.mapper.OrdenItemMapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrdenItemImp implements IOrdenItem {

    private final OrdenItemRepository itemRepo;
    private final OrdenItemMapper mapper;

    public OrdenItemImp(OrdenItemRepository itemRepo, OrdenItemMapper mapper) {
        this.itemRepo = itemRepo;
        this.mapper = mapper;
    }

    @Override
    public List<OrdenItemDTO> getItemsByOrder(Long ordenId) {
        return mapper.toDTOs(itemRepo.findByOrdenId(ordenId));
    }

    @Override
    public Optional<OrdenItemDTO> getItem(Long id) {
        return itemRepo.findById(id).map(mapper::toDTO);
    }

    @Override
    public OrdenItemDTO save(OrdenItemDTO dto) {
        throw new UnsupportedOperationException("Use OrdenItemService to save items.");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Use OrdenItemService to delete items.");
    }
}
