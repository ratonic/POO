package com.example.market.domain.service;

import com.example.market.domain.dto.OrdenItemDTO;
import com.example.market.domain.repository.IOrdenItem;
import com.example.market.infraestructure.crud.OrdenItemRepository;
import com.example.market.infraestructure.crud.OrdenRepository;
import com.example.market.infraestructure.crud.ProductoRepository;
import com.example.market.infraestructure.entity.Orden;
import com.example.market.infraestructure.entity.OrdenItem;
import com.example.market.infraestructure.entity.Producto;
import com.example.market.infraestructure.mapper.OrdenItemMapper;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenItemService {

    private final IOrdenItem ordenItemRepo;
    private final ProductoRepository productoRepo;
    private final OrdenRepository ordenRepo;
    private final OrdenItemRepository itemRepo;
    private final OrdenItemMapper mapper;

    public OrdenItemService(IOrdenItem ordenItemRepo,
                            ProductoRepository productoRepo,
                            OrdenRepository ordenRepo,
                            OrdenItemRepository itemRepo,
                            OrdenItemMapper mapper) {
        this.ordenItemRepo = ordenItemRepo;
        this.productoRepo = productoRepo;
        this.ordenRepo = ordenRepo;
        this.itemRepo = itemRepo;
        this.mapper = mapper;
    }

    public List<OrdenItemDTO> getByOrden(Long ordenId) {
        return ordenItemRepo.getItemsByOrder(ordenId);
    }

    public Optional<OrdenItemDTO> getItem(Long id) {
        return ordenItemRepo.getItem(id);
    }

    public OrdenItemDTO save(OrdenItemDTO dto) {
        Producto producto = productoRepo.findById(dto.getProductId()).orElseThrow();
        Orden orden = ordenRepo.findById(dto.getOrderId()).orElseThrow();

        dto.setUnitPrice(producto.getPrecio());

        OrdenItem item = mapper.toEntity(dto);
        item.setProducto(producto);
        item.setOrden(orden);

        OrdenItem saved = itemRepo.save(item);
        recalcularTotalOrden(orden.getId());

        return mapper.toDTO(saved);
    }

    public void delete(Long id) {
        OrdenItem item = itemRepo.findById(id).orElseThrow();
        Long ordenId = item.getOrden().getId();

        itemRepo.deleteById(id);
        recalcularTotalOrden(ordenId);
    }

    private void recalcularTotalOrden(Long ordenId) {
        Orden orden = ordenRepo.findById(ordenId).orElseThrow();

        List<OrdenItem> items = itemRepo.findByOrdenId(ordenId);

        double total = items.stream()
            .mapToDouble(i -> i.getPrecioUnitario() * i.getCantidad())
            .sum();

        orden.setTotal(total);
        ordenRepo.save(orden);
    }
}
