package com.parcial.demo.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parcial.demo.model.entities.Comercial;

@Repository
public interface ComercialRepository extends JpaRepository<Comercial, Long> {

    @Query("SELECT c FROM Comercial c WHERE c.nombre LIKE %:nombre% OR c.apellido1 LIKE %:apellido1% OR c.apellido2 LIKE %:apellido2% OR c.ciudad LIKE %:ciudad%")
      List<Comercial> findByNombreContainsOrApellido1ContainsOrApellido2ContainsCiudadcontains(@Param("nombre") String nombre, @Param("apellido1") 
      String apellido1, @Param("apellido2") String apellido2, @Param("ciudad") String ciudad);

      @Query("SELECT c FROM Comercial c WHERE c.id = :id AND EXISTS (SELECT p FROM Pedido p WHERE p.comercial.id = c.id)")
      List<Comercial> findComercialWithPedidosById(@Param("id") Long id);

}
