package com.parcial.demo.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcial.demo.model.entities.Cliente;
import com.parcial.demo.model.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminarCliente(long id) {
        clienteRepository.deleteById(id);
        
    }

    @Override
    public Cliente buscarClientePorId(long id) {
        return clienteRepository.findById(id).orElse(null);
    }


    public List<Cliente> listarClientesPorCategoria(int categoria) {
        return clienteRepository.findByCategoria(categoria);
    }

    @Override
    public List<Cliente> listarClientesPorNombreOApellidosOCiudad(String nombre, String apellido1, String apellido2, String ciudad) {
       return clienteRepository.findByNombreContainsOrApellido1ContainsOrApellido2ContainsCiudadContains(nombre, apellido1, apellido2, ciudad);
    }

    
    @Override
    public List<Cliente> buscarPorNombreApellidoYCategoria(String nombre, String apellido, Integer categoria) {
        return clienteRepository.findByNombreApellidoAndCategoria(nombre, apellido, categoria);
    }

    @Override
    public List<Cliente> listarClientesPorCategoriaNula() {
        return clienteRepository.findByCategoriaNull();
    }

    @Override
    public List<Object[]> listarClientesConPedidos() {
        return clienteRepository.listarClientesConPedidos();
    }

    @Override
    public List<Cliente> findClienteWithPedidosById(Long id) {
        return clienteRepository.findClienteWithPedidosById(id);
    }

    @Override
    public List<Object[]> buscarClientesConPedidos(String entrada) {
        return clienteRepository.buscarClientesConPedidos(entrada);
    }

    
    
   
    
}
