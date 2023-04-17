package com.parcial.demo.model.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.parcial.demo.model.entities.Cliente;

@Service
public interface ClienteService {

    List<Cliente> listarClientes();

    Cliente guardarCliente(Cliente cliente);

    Cliente actualizarCliente(Cliente cliente);

    void eliminarCliente(long id);

    Cliente buscarClientePorId(long id);

    List<Cliente> listarClientesPorCategoria(int categoria);

    List<Cliente> listarClientesPorCategoriaNula();
 
    List<Cliente> listarClientesPorNombreOApellidosOCiudad(String nombre, String apellido1, String apellido2, String ciudad);
   
    List<Cliente> buscarPorNombreApellidoYCategoria(String nombre, String apellido, Integer categoria);

    List<Object[]> listarClientesConPedidos();

    List<Object[]> buscarClientesConPedidos(String entrada);
    
    List<Cliente> findClienteWithPedidosById(Long id);
    

    
}
