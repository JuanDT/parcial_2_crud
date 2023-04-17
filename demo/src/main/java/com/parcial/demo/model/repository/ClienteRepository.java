package com.parcial.demo.model.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parcial.demo.model.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByCategoria(int categoria);
    List<Cliente> findByCategoriaNull();
   

    @Query("SELECT c FROM Cliente c WHERE c.id = :id AND EXISTS (SELECT p FROM Pedido p WHERE p.cliente.id = c.id)")
    List<Cliente> findClienteWithPedidosById(@Param("id") Long id);

    @Query(value ="select * from cliente c left join pedido p on c.id = p.id_cliente order by c.apellido1, c.apellido2, c.nombre asc", nativeQuery = true)
    List<Object[]> listarClientesConPedidos();
    
    @Query("SELECT c FROM Cliente c WHERE c.nombre LIKE %:nombre% OR c.apellido1 LIKE %:apellido1% OR c.apellido2 LIKE %:apellido2%")
      List<Cliente> findByNombreContainsOrApellido1ContainsOrApellido2Contains(@Param("nombre") String nombre, @Param("apellido1") 
      String apellido1, @Param("apellido2") String apellido2);

    @Query("SELECT c FROM Cliente c WHERE (c.nombre LIKE %:nombre% OR c.apellido1 LIKE %:apellido% OR c.apellido2 LIKE %:apellido%) AND c.categoria = :categoria ")
      List<Cliente> findByNombreApellidoAndCategoria(@Param("nombre") String nombre, @Param("apellido") String apellido, @Param("categoria") Integer categoria);
     

    
}

