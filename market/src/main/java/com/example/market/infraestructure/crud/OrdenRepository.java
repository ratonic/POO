package com.example.market.infraestructure.crud;

import com.example.market.infraestructure.entity.Orden;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
    List<Orden> findByClienteId(Long clienteId);
}

