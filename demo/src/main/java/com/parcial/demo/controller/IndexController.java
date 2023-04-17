package com.parcial.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.parcial.demo.model.service.ClienteService;

@Controller
public class IndexController {

    @Autowired
    ClienteService clienteService;

    @GetMapping({ "/", "" })
    public String index(@RequestParam(value = "entrada", required = false) String entrada, Model model) {
        List<Object[]> listaClientesPedidos = clienteService.listarClientesConPedidos();
        model.addAttribute("entrada", entrada);
        model.addAttribute("listaClientesPedidos", listaClientesPedidos);
        return "index";
    }

    
    
    
    @GetMapping("/pedidos")
    public String gestionarPedidos() {
        return "pedidos/listar";
    }
    
    @GetMapping("/comerciales")
    public String gestionarComerciales() {
        return "comerciales/listar";
    }
}
