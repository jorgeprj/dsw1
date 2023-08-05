package br.ufscar.dc.dsw.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.Authentication;

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Entrevista;
import br.ufscar.dc.dsw.service.spec.IEntrevistaService;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.security.UsuarioDetails;

@Controller
@RequestMapping("/entrevistas")
public class EntrevistaController {
	
	@Autowired
	private IEntrevistaService service;
	@Autowired
	private IEmpresaService empresaService;
	@Autowired
	private IProfissionalService profissionalService;
	
	private Usuario getUsuario() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioDetails.getUsuario();
	}

	
	@GetMapping("/cadastrar")
	public String cadastrar(Entrevista entrevista, Model model) {
		List<Empresa> empresas = empresaService.buscarTodos();
        model.addAttribute("Empresa", empresas);
        
		return "entrevista/cadastro";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Entrevista entrevista, BindingResult result, RedirectAttributes attr, Authentication auth) {

        String email = auth.getName();
        Profissional profissionalLogado = profissionalService.buscarPorEmail(email);
        entrevista.setProfissional(profissionalLogado);
        
		
		service.salvar(entrevista);
		attr.addFlashAttribute("sucess", "Entrevista inserida com sucesso.");
		return "redirect:/entrevistas/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model, Authentication auth) {
	    model.addAttribute("entrevista", service.buscarPorId(id));
	    
	    List<Empresa> empresas = empresaService.buscarTodos();
	    model.addAttribute("Empresa", empresas);
	    
	    return "entrevista/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Entrevista entrevista, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			
		}				
		service.salvar(entrevista);
		attr.addFlashAttribute("sucess", "Entrevista editada com sucesso.");
		return "redirect:/entrevistas/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id);
		model.addAttribute("sucess", "Entrevista excluída com sucesso.");
		return listar(model);
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		Usuario usuario = this.getUsuario();
		String role = usuario.getRole(); 
		
		if (role.equals("ROLE_PROFISSIONAL"))
			model.addAttribute("entrevistas",service.buscarTodosPorProfissional(usuario.getId()));
		else 
			model.addAttribute("entrevistas",service.buscarTodosPorEmpresa(usuario.getId()));

		return "entrevista/lista";
	}
	
	
}
