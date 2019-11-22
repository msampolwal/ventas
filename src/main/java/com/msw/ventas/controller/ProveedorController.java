package com.msw.ventas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.msw.ventas.model.Proveedor;
import com.msw.ventas.service.api.ProveedorServiceApi;

@Controller
public class ProveedorController extends AbstractController{
	
	@Autowired
	private ProveedorServiceApi proveedorServiceApi;
	
	@RequestMapping("/proveedor/")
	public String getIndex(Model model) {
		model.addAttribute("proveedores", proveedorServiceApi.getAll());
		return "proveedor/list";
	}
	
	@GetMapping("/proveedor/form/{id}")
	public String selectProveedor(@PathVariable Long id, Model model) {
		if(id != null && id != 0)
			model.addAttribute("proveedor", proveedorServiceApi.get(id));
		else
			model.addAttribute("proveedor", new Proveedor());
		return "proveedor/form";
	}
	
	@PostMapping("/proveedor/save")
	public String save(Proveedor proveedor, Model model) {
		if(proveedor != null)
			proveedorServiceApi.save(proveedor);
		return "redirect:/proveedor/";
	}
	
	@GetMapping("/proveedor/delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		if(id != null)
			proveedorServiceApi.delete(id);
		return "redirect:/proveedor/";
	}
}