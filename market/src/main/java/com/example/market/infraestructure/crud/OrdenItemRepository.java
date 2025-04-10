package com.example.market.infraestructure.crud;

import com.example.market.infraestructure.entity.OrdenItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdenItemRepository extends JpaRepository<OrdenItem, Long> {
    List<OrdenItem> findByOrdenId(Long ordenId);
}
