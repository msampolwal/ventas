package com.msw.ventas.controller;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public abstract class AbstractController {

	public void info(String mensaje, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", mensaje);
	    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
	}
	
	public void error(String mensaje, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", mensaje);
	    redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
	}
}