package com.msw.ventas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.msw.ventas.model.Producto;
import com.msw.ventas.model.Proveedor;
import com.msw.ventas.service.api.ProductoServiceApi;
import com.msw.ventas.service.api.ProveedorServiceApi;

@Controller
public class ProductoController {
	
	@Autowired
	private ProductoServiceApi productoServiceApi;
	@Autowired
	private ProveedorServiceApi proveedorServiceApi;
	private Proveedor proveedor;
	private boolean modificacion;
	
	@RequestMapping("/producto/")
	public String getIndex(Model model) {
		model.addAttribute("productos", productoServiceApi.getAll());
		return "producto/list";
	}
	
	@GetMapping("/producto/form/{idProveedor}/{id}")
	public String selectProducto(@PathVariable Long id, @PathVariable Long idProveedor, Model model) {
		setModificacion(id != 0);
		model.addAttribute("producto", getProducto(id, idProveedor));
		return "producto/form";
	}
	
	@PostMapping("/producto/save")
	public String save(Producto producto, Model model) {
		if(producto != null) {
			productoServiceApi.save(producto);
			model.addAttribute("producto", getProducto(null, null));
		}
		return isModificacion()?"redirect:/producto/":"redirect:/producto/form/"+getProveedor().getId()+"/0";
	}
	
	private Producto getProducto(Long id, Long idProveedor) {
		Producto producto = null;
		if(id != null && id != 0) {
			producto = productoServiceApi.get(id);
			setProveedor(producto.getProveedor());
		} else {
			producto = new Producto();
			if(getProveedor() != null)
				producto.setProveedor(getProveedor());
			else if (idProveedor != null) {
				setProveedor(proveedorServiceApi.get(idProveedor));
				if(getProveedor() != null)
					producto.setProveedor(getProveedor());
			}
		}
		return producto;
	}
	
	@GetMapping("/producto/delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		if(id != null)
			productoServiceApi.delete(id);
		return "redirect:/producto/";
	}

	@GetMapping("/producto/volver")
	public String volver(Model model) {
		return isModificacion()?"redirect:/producto/":"redirect:/proveedor/";
	}
	
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public boolean isModificacion() {
		return modificacion;
	}

	public void setModificacion(boolean modificacion) {
		this.modificacion = modificacion;
	}
}