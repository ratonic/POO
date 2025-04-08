package com.example.market.infraestructure.mapper;

import com.example.market.domain.dto.ClienteDTO;
import com.example.market.infraestructure.entity.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "nombre", target = "name"),
        @Mapping(source = "email", target = "email"),
        @Mapping(source = "direccion", target = "address"),
        @Mapping(source = "telefono", target = "phone")
    })
    ClienteDTO toClienteDTO(Cliente cliente);
    List<ClienteDTO> toClientesDTO(List<Cliente> clientes);

    @InheritInverseConfiguration
    Cliente toCliente(ClienteDTO clienteDTO);
    List<Cliente> toClientes(List<ClienteDTO> clienteDTOs);
}
