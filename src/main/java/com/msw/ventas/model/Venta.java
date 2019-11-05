package com.msw.ventas.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal importe;

    @OneToMany(cascade= CascadeType.ALL, orphanRemoval = true, mappedBy = "venta")
    private Set<ItemVenta> itemsVenta = new HashSet<ItemVenta>();

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fecha;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;

    /*Getters y Setters*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public Set<ItemVenta> getItemsVenta() {
		return itemsVenta;
	}

	public void setItemsVenta(Set<ItemVenta> itemsVenta) {
		this.itemsVenta = itemsVenta;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
