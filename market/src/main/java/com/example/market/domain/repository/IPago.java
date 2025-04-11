package com.example.market.domain.repository;

import com.example.market.domain.dto.PagoDTO;
import java.util.List;
import java.util.Optional;

public interface IPago {
    List<PagoDTO> getAll();
    Optional<PagoDTO> getPago(Long id);
    PagoDTO save(PagoDTO pago);
    void delete(Long id);
    List<PagoDTO> getByOrdenId(Long ordenId);
}