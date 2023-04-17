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

import com.parcial.demo.model.entities.Cliente;
import com.parcial.demo.model.entities.Comercial;
import com.parcial.demo.model.entities.Pedido;
import com.parcial.demo.model.service.ClienteService;
import com.parcial.demo.model.service.ComercialService;
import com.parcial.demo.model.service.PedidoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ComercialService comercialService;

    @Autowired
    private ClienteService clienteService;


    @GetMapping("/listar")
    public String listarPedidos(
        @RequestParam(name = "fechaInicio", required = false) String fechaInicio,
        @RequestParam(name = "fechaFin", required = false) String fechaFin,
        @RequestParam(name = "numero", required = false) Integer numero,
        Model model) {
        List<Pedido> listaPedidos = new ArrayList<>();
            
                Long numero2 = null;
            if (numero != null) {
                numero2 = numero.longValue();
            }


                if (fechaInicio != null && !fechaInicio.isEmpty() && fechaFin != null && !fechaFin.isEmpty()) {
                    if (numero != null) {
                        Pedido pedido = pedidoService.buscarPedidoPorId(numero2);
                        if (pedido != null && pedido.getFecha().compareTo(fechaInicio) >= 0 && pedido.getFecha().compareTo(fechaFin) <= 0) {
                            listaPedidos.add(pedido);
                        }
                    } else {
                        listaPedidos = pedidoService.findByFechaBetween(fechaInicio, fechaFin);
                    }
                } else if (fechaInicio != null && !fechaInicio.isEmpty() && (fechaFin == null || fechaFin.isEmpty())) {
                    if (numero != null) {
                        Pedido pedido = pedidoService.buscarPedidoPorId(numero2);
                        if (pedido != null && pedido.getFecha().compareTo(fechaInicio) >= 0) {
                            listaPedidos.add(pedido);
                        }
                    } else {
                        listaPedidos = pedidoService.findByFechaGreaterThanEqual(fechaInicio);
                    }
                } else if (fechaFin != null && !fechaFin.isEmpty() && (fechaInicio == null || fechaInicio.isEmpty())) {
                    if (numero != null) {
                        Pedido pedido = pedidoService.buscarPedidoPorId(numero2);
                        if (pedido != null && pedido.getFecha().compareTo(fechaFin) <= 0) {
                            listaPedidos.add(pedido);
                        }
                    } else {
                        listaPedidos = pedidoService.findByFechaLessThanEqual(fechaFin);
                    }
                } else {
                    if (numero != null) {
                        Pedido pedido = pedidoService.buscarPedidoPorId(numero2);
                        if (pedido != null) {
                            listaPedidos.add(pedido);
                        }
                    } else {
                        listaPedidos = pedidoService.listarPedidos();
                    }
                }

                model.addAttribute("listaPedidos", listaPedidos);
                model.addAttribute("numero", numero);
                model.addAttribute("fechaInicio", fechaInicio);
                model.addAttribute("fechaFin", fechaFin);

                return "pedidos/listar";
    }

     
          

    @GetMapping("/nuevo")
    public String mostrarFormularioAgregarPedido(Model model) {
        List<Cliente> listaClientes = clienteService.listarClientes();
        List<Comercial> listaComerciales = comercialService.listarComerciales();

        model.addAttribute("pedido", new Pedido());
        model.addAttribute("clientes", listaClientes);
        model.addAttribute("comerciales", listaComerciales);
        return "pedidos/nuevo";
    }

    @PostMapping("/guardar")
    public String guardarPedido(@Valid @ModelAttribute("pedido") Pedido pedido, BindingResult result) {
        if (result.hasErrors()) {
            return "pedidos/agregar";
        } else {
            pedidoService.guardarPedido(pedido);
            return "redirect:/pedidos/listar";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarPedido(@PathVariable("id") Long id, Model model) {
        Pedido pedido = pedidoService.buscarPedidoPorId(id);
        List<Cliente> listaClientes = clienteService.listarClientes();
        List<Comercial> listaComerciales = comercialService.listarComerciales();
        
        

        model.addAttribute("pedido", pedido);
        model.addAttribute("listaClientes", listaClientes);
        model.addAttribute("listaComerciales", listaComerciales);
        return "pedidos/editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarPedido(@PathVariable("id") Long id,@Valid @ModelAttribute("pedido") Pedido pedido, BindingResult result) {
        if (result.hasErrors()) {
            return "pedidos/editar";
        } else {
            pedidoService.actualizarPedido(pedido);
            return "redirect:/pedidos/listar";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPedido(@PathVariable("id") Long id) {
        pedidoService.eliminarPedido(id);
        return "redirect:/pedidos/listar";
    }
}

