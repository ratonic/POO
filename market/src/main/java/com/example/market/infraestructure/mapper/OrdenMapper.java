package com.example.market.infraestructure.mapper;

import com.example.market.domain.dto.OrdenDTO;
import com.example.market.infraestructure.entity.Orden;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdenMapper {

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "fecha", target = "date"),
        @Mapping(source = "total", target = "total"),
        @Mapping(source = "estado", target = "status"),
        @Mapping(source = "cliente.id", target = "customerId")
    })
    OrdenDTO toOrderDTO(Orden orden);

    List<OrdenDTO> toOrdersDTO(List<Orden> ordenes);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)
    Orden toOrden(OrdenDTO dto);
}
