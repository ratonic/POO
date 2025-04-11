package com.example.market.infraestructure.mapper;

import com.example.market.domain.dto.PagoDTO;
import com.example.market.infraestructure.entity.Pago;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PagoMapper {
    @Mappings({
        @Mapping(source = "orden.id", target = "ordenId")
    })
    PagoDTO toDto(Pago pago);

    List<PagoDTO> toDto(List<Pago> pagos);

    @Mappings({
        @Mapping(source = "ordenId", target = "orden.id")
    })
    Pago toEntity(PagoDTO dto);
}