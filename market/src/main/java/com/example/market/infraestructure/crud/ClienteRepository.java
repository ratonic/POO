package com.example.market.infraestructure.crud;

import org.springframework.data.repository.CrudRepository;

import com.example.market.infraestructure.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}