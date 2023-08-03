package br.ufscar.dc.dsw;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IEmpresaDAO;
import br.ufscar.dc.dsw.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Usuario;

@SpringBootApplication
public class EmpregosApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpregosApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, IEmpresaDAO empresaDAO, IProfissionalDAO profissionalDAO) {
		return (args) -> {

			// Criando um usuário
			Usuario usuario = new Usuario();
			usuario.setNome("Lucas");
			usuario.setEmail("admin@admin.com");
			usuario.setSenha("admin");
			usuario.setPapel("ADMIN");
			usuarioDAO.save(usuario);
	
			// Criando uma empresa
			Empresa empresa = new Empresa();
			empresa.setId(1L);
			empresa.setCnpj("12345678901234");
			empresa.setCidade("São Paulo");
			empresa.setUsuario(usuario);
			empresaDAO.save(empresa);
	
			// Criando um profissional
			Profissional profissional = new Profissional();
			profissional.setId(2L);
			profissional.setCpf("123.456.789-01");
			profissional.setTelefone("(11) 98765-4321");
			profissional.setSexo("Masculino");
			profissional.setData_nascimento(new Date(1990, 5, 15));
			profissional.setUsuario(usuario);
			profissionalDAO.save(profissional);

		};
	}
}