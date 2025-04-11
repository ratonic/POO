package com.example.market.infraestructure.repositories;

import com.example.market.domain.dto.OrdenItemDTO;
import com.example.market.domain.repository.IOrdenItem;
import com.example.market.infraestructure.crud.OrdenItemRepository;
import com.example.market.infraestructure.crud.ProductoRepository;
import com.example.market.infraestructure.crud.OrdenRepository;
import com.example.market.infraestructure.entity.OrdenItem;
import com.example.market.infraestructure.entity.Orden;
import com.example.market.infraestructure.entity.Producto;
import com.example.market.infraestructure.mapper.OrdenItemMapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrdenItemImp implements IOrdenItem {

    private final OrdenItemRepository itemRepo;
    private final OrdenRepository ordenRepo;
    private final ProductoRepository productoRepo;
    private final OrdenItemMapper mapper;

    public OrdenItemImp(OrdenItemRepository itemRepo, OrdenRepository ordenRepo,
                        ProductoRepository productoRepo, OrdenItemMapper mapper) {
        this.itemRepo = itemRepo;
        this.ordenRepo = ordenRepo;
        this.productoRepo = productoRepo;
        this.mapper = mapper;
    }

    @Override
    public List<OrdenItemDTO> getItemsByOrder(Long ordenId) {
        return mapper.toDTOs(itemRepo.findByOrdenId(ordenId));
    }

    @Override
    public Optional<OrdenItemDTO> getItem(Long id) {
        return itemRepo.findById(id).map(mapper::toDTO);
    }

    @Override
    public OrdenItemDTO save(OrdenItemDTO dto) {
        OrdenItem item = mapper.toEntity(dto);

        Producto producto = productoRepo.findById(dto.getProductId()).orElseThrow();
        Orden orden = ordenRepo.findById(dto.getOrderId()).orElseThrow();

        item.setProducto(producto);
        item.setOrden(orden);
        item.setPrecioUnitario(producto.getPrecio());

        OrdenItem saved = itemRepo.save(item);
        recalcularTotalOrden(orden);

        return mapper.toDTO(saved);
    }

    @Override
    public void delete(Long id) {
        OrdenItem item = itemRepo.findById(id).orElseThrow();
        Orden orden = item.getOrden();

        itemRepo.deleteById(id);
        recalcularTotalOrden(orden);
    }

    private void recalcularTotalOrden(Orden orden) {
        List<OrdenItem> items = itemRepo.findByOrdenId(orden.getId());

        double total = items.stream()
                .mapToDouble(i -> i.getPrecioUnitario() * i.getCantidad())
                .sum();

        orden.setTotal(total);
        ordenRepo.save(orden);
    }
}