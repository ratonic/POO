package com.example.market.infraestructure.crud;

import com.example.market.infraestructure.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByOrdenId(Long ordenId);
}