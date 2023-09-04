package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import javax.servlet.http.HttpServletRequest;



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

	@RequestMapping("/home")
    public String defaultAfterLogin(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin";
        } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_EMPRESA"))) {
            return "redirect:/user";
        } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_PROFISSIONAL"))) {
            return "redirect:/user";
        } else {
            // Redirecionamento padrão em caso de roles desconhecidas
            return "redirect:/home";
        }
    }
}
