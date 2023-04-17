package com.parcial.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.parcial.demo.model.entities.Cliente;
import com.parcial.demo.model.service.ClienteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listar")
    public String listarClientes(@RequestParam(value = "categoria", required = false) Integer categoria,
            @RequestParam(value = "nombre", required = false) String nombre,
            Model model) {
        List<Cliente> listaClientes = new ArrayList<>();
        Cliente clienteId = new Cliente();

        if (nombre != null && nombre.matches("^[0-9]+$")) {

            Long id = Long.parseLong(nombre);
            clienteId = clienteService.buscarClientePorId(id);
            if (clienteId != null) {
                listaClientes.add(clienteId);
            } else {
                model.addAttribute("listaClientes", listaClientes);
                model.addAttribute("cliente", clienteId);
                model.addAttribute("categoria", categoria);
                model.addAttribute("nombre", nombre);
                return "clientes/listar";
            }
        } else {

            if (categoria != null) {
                if (categoria == -1) {
                    if (nombre != null && !nombre.isEmpty()) {
                        listaClientes = clienteService.buscarPorNombreApellidoYCategoria(nombre, nombre, categoria);
                    } else {
                        listaClientes = clienteService.listarClientesPorCategoriaNula();
                    }
                } else {
                    if (nombre != null && !nombre.isEmpty()) {
                        listaClientes = clienteService.buscarPorNombreApellidoYCategoria(nombre, nombre, categoria);
                    } else {
                        listaClientes = clienteService.listarClientesPorCategoria(categoria);
                    }
                }
            } else if (nombre != null && !nombre.isEmpty()) {
                listaClientes = clienteService.listarClientesPorNombreOApellidosOCiudad(nombre, nombre, nombre, nombre);
            } else {
                listaClientes = clienteService.listarClientes();
            }
        }

        model.addAttribute("listaClientes", listaClientes);
        model.addAttribute("cliente", clienteId);
        model.addAttribute("categoria", categoria);
        model.addAttribute("nombre", nombre);
        return "clientes/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioAgregarCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/nuevo";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return "clientes/guardar";
        } else {
            clienteService.guardarCliente(cliente);
            return "redirect:/clientes/listar";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCliente(@PathVariable("id") Long id, Model model) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        model.addAttribute("cliente", cliente);
        return "clientes/editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarCliente(@PathVariable Long id, @Valid @ModelAttribute("cliente") Cliente cliente,
                                 BindingResult result) {
    if (result.hasErrors()) {
        return "clientes/editar";
    } else {
        Cliente clienteExistente = clienteService.buscarClientePorId(id);
        if (clienteExistente == null) {
            return "redirect:/clientes/listar?error=Cliente no encontrado con id=" + id;
        } else {
            
            clienteExistente.setNombre(cliente.getNombre());
            clienteExistente.setApellido1(cliente.getApellido1());
            clienteExistente.setApellido2(cliente.getApellido2());
            clienteExistente.setCiudad(cliente.getCiudad());
            clienteExistente.setCategoria(cliente.getCategoria());
            clienteService.actualizarCliente(clienteExistente);
            return "redirect:/clientes/listar";
        }
    }
}

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable("id") Long id, RedirectAttributes attributes) {

        List<Cliente> cliente = clienteService.findClienteWithPedidosById(id);

        if (cliente.size() == 0) {
            clienteService.eliminarCliente(id);
            attributes.addFlashAttribute("mensaje", "Cliente eliminado correctamente");
        } else {
            attributes.addFlashAttribute("error", "El cliente tiene pedidos asociados y no se puede eliminar");
        }

        return "redirect:/clientes/listar";
    }

}
