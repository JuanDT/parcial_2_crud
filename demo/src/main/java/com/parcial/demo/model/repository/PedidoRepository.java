package com.parcial.demo.model.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parcial.demo.model.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByFechaBetween(String fechaInicio, String fechaFin);
    @Query("SELECT p FROM Pedido p WHERE p.fecha >= :fechaInicio")
    List<Pedido> findByFechaGreaterThanEqual(@Param("fechaInicio") String fechaInicio);
    List<Pedido> findByFechaLessThanEqual(String fechaFin);
}
