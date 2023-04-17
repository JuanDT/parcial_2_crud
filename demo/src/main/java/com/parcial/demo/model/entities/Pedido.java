package com.parcial.demo.model.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank
    @Size(max = 100)
    private String cantidad;
 
    @NotNull
    private String fecha;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_comercial")
    private Comercial comercial;
    
    public Pedido() {
        
    }
    
    public Pedido(String cantidad, String fecha, Cliente cliente, Comercial comercial) {
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.cliente = cliente;
        this.comercial = comercial;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Comercial getComercial() {
        return comercial;
    }
    
    public void setComercial(Comercial comercial) {
        this.comercial = comercial;
    }
}

