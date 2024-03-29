package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufscar.dc.dsw.service.spec.IEmpresaService;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	private IEmpresaService service;

	@GetMapping("")
	public String listar(@RequestParam(value = "cidade", required = false) String cidade, ModelMap model) {
		if (cidade == null) {
			model.addAttribute("empresas", service.buscarTodos());
		} else {
			model.addAttribute("empresas", service.buscarPorCidade(cidade));
		}
		return "/index";
	}
}