package com.example.market.domain.service;

import com.example.market.domain.dto.PagoDTO;
import com.example.market.domain.repository.IPago;
import com.example.market.infraestructure.crud.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private IPago pagoRepository;

    @Autowired
    private OrdenRepository ordenRepository;

    public List<PagoDTO> getAll() {
        return pagoRepository.getAll();
    }

    public Optional<PagoDTO> getPago(Long id) {
        return pagoRepository.getPago(id);
    }

    public PagoDTO save(PagoDTO pago) {
        if (pago.getFechaPago() == null) {
            pago.setFechaPago(LocalDateTime.now());
        }

        if (pago.getEstado() == null) {
            pago.setEstado("PENDIENTE");
        }

        if (pago.getOrdenId() != null) {
            ordenRepository.findById(pago.getOrdenId()).ifPresent(orden -> {
                pago.setMonto(orden.getTotal());
            });
        }

        return pagoRepository.save(pago);
    }

    public Optional<PagoDTO> update(Long id, PagoDTO pago) {
        if (pagoRepository.getPago(id).isPresent()) {
            pago.setId(id);

            if (pago.getOrdenId() != null) {
                ordenRepository.findById(pago.getOrdenId()).ifPresent(orden -> {
                    pago.setMonto(orden.getTotal());
                });
            }

            return Optional.of(pagoRepository.save(pago));
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        if (pagoRepository.getPago(id).isPresent()) {
            pagoRepository.delete(id);
            return true;
        }
        return false;
    }

    public List<PagoDTO> getByOrdenId(Long ordenId) {
        return pagoRepository.getByOrdenId(ordenId);
    }
}