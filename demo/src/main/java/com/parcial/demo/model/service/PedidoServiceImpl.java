package com.parcial.demo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcial.demo.model.entities.Pedido;
import com.parcial.demo.model.repository.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido actualizarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public void eliminarPedido(Long codigo) {
        pedidoRepository.deleteById(codigo);
    }

    @Override
    public Pedido buscarPedidoPorId(Long codigo) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(codigo);
        if (optionalPedido.isPresent()) {
            return optionalPedido.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Pedido> findByFechaBetween(String fechaInicio, String fechaFin) {       
        return pedidoRepository.findByFechaBetween(fechaInicio, fechaFin);
    }

    @Override
    public List<Pedido> findByFechaGreaterThanEqual(String fechaInicio) {
        return pedidoRepository.findByFechaGreaterThanEqual(fechaInicio);
    }

    @Override
    public List<Pedido> findByFechaLessThanEqual(String fechaFin) {
        return pedidoRepository.findByFechaLessThanEqual(fechaFin);
    }
}
