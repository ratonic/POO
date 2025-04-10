package com.example.market.domain.repository;

import com.example.market.domain.dto.OrdenItemDTO;

import java.util.List;
import java.util.Optional;

public interface IOrdenItem {
    List<OrdenItemDTO> getItemsByOrder(Long ordenId);
    Optional<OrdenItemDTO> getItem(Long id);
    OrdenItemDTO save(OrdenItemDTO dto);
    void delete(Long id);
}
