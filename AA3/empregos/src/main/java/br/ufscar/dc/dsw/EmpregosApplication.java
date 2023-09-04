package br.ufscar.dc.dsw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.dao.IEmpresaDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.dao.IEntrevistaDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Entrevista;



@SpringBootApplication
public class EmpregosApplication {

	public static void main(String[] args) throws Throwable {
		SpringApplication.run(EmpregosApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO UsuarioDao, IProfissionalDAO ProfissionalDao, IEmpresaDAO EmpresaDao, IEntrevistaDAO EntrevistaDao, BCryptPasswordEncoder encoder) {
		return (args) -> {
						
			Usuario u1 = new Usuario();
			u1.setEmail("admin@email.com");
			u1.setNome("Admin");
			u1.setPassword(encoder.encode("admin"));
			u1.setRole("ROLE_ADMIN");
			UsuarioDao.save(u1);	

			Profissional p1 = new Profissional();
			p1.setEmail("jorge@email.com");
			p1.setNome("Jorge Pires");
			p1.setPassword(encoder.encode("jorge"));
			p1.setRole("ROLE_PROFISSIONAL");
			p1.setCpf("468.325.873-40");
			p1.setTelefone("(16)99423-5549");
			p1.setGenero("M");
			p1.setDataNasc("11/02/2002");
			ProfissionalDao.save(p1);

			Profissional p2 = new Profissional();
			p2.setEmail("lucas@email.com");
			p2.setNome("lucas abbiati");
			p2.setPassword(encoder.encode("lucas"));
			p2.setRole("ROLE_PROFISSIONAL");
			p2.setCpf("479.366.878-07");
			p2.setTelefone("(16)999714810");
			p2.setGenero("M");
			p2.setDataNasc("17/10/2003");
			ProfissionalDao.save(p2);
			
			Empresa e1 = new Empresa();
			e1.setEmail("ufscar@email.com");
			e1.setNome("Ufscar");
			e1.setPassword(encoder.encode("ufscar"));
			e1.setRole("ROLE_EMPRESA");
			e1.setCnpj("12.345.678/0001-90");
			e1.setCidade("São Carlos");
			EmpresaDao.save(e1);

			Empresa e2 = new Empresa();
			e2.setEmail("amazon@email.com");
			e2.setNome("amazon");
			e2.setPassword(encoder.encode("amazon"));
			e2.setRole("ROLE_EMPRESA");
			e2.setCnpj("12.345.789/0001-90");
			e2.setCidade("Campinas");
			EmpresaDao.save(e2);

			Entrevista en1 = new Entrevista();
			en1.setData("30/07/2023 15:00");
			en1.setProfissional(p1);
			en1.setEmpresa(e1);
			EntrevistaDao.save(en1);

		};
	}
}