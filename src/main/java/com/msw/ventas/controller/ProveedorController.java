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
public class ProveedorController {
	
	@Autowired
	private ProveedorServiceApi proveedorServiceApi;
	
	@RequestMapping("/")
	public String getIndex(Model model) {
		model.addAttribute("proveedores", proveedorServiceApi.getAll());
		return "list";
	}
	
	@GetMapping("/form/{id}")
	public String selectProveedor(Long id, Model model) {
		if(id != null)
			model.addAttribute("proveedor", proveedorServiceApi.get(id));
		return "form";
	}
	
	@PostMapping("/save")
	public String save(Proveedor proveedor, Model model) {
		if(proveedor != null)
			proveedorServiceApi.save(proveedor);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		if(id != null)
			proveedorServiceApi.delete(id);
		return "redirect:/";
	}
}
