package com.parcial.demo.model.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.parcial.demo.model.entities.Pedido;

@Service
public interface PedidoService {

    List<Pedido> listarPedidos();

    Pedido guardarPedido(Pedido pedido);

    Pedido actualizarPedido(Pedido pedido);

    void eliminarPedido(Long codigo);
    
    Pedido buscarPedidoPorId(Long codigo);

    List<Pedido> findByFechaBetween(String fechaInicio, String fechaFin);

    List<Pedido> findByFechaGreaterThanEqual(String fechaInicio);
    
    List<Pedido> findByFechaLessThanEqual(String fechaFin);
}
