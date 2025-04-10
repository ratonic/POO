package com.example.market.infraestructure.mapper;

import com.example.market.domain.dto.OrdenItemDTO;
import com.example.market.infraestructure.entity.OrdenItem;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdenItemMapper {

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "cantidad", target = "quantity"),
        @Mapping(source = "precioUnitario", target = "unitPrice"),
        @Mapping(source = "producto.id", target = "productId"),
        @Mapping(source = "orden.id", target = "orderId")
    })
    OrdenItemDTO toDTO(OrdenItem item);

    List<OrdenItemDTO> toDTOs(List<OrdenItem> items);

    @InheritInverseConfiguration
    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "orden", ignore = true)
    OrdenItem toEntity(OrdenItemDTO dto);
}
