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

			Profissional c1 = new Profissional();
			c1.setEmail("jorge@email.com");
			c1.setNome("Jorge Pires");
			c1.setPassword(encoder.encode("jorge"));
			c1.setRole("ROLE_PROFISSIONAL");
			c1.setCpf("468.325.873-40");
			c1.setTelefone("(16)99423-5549");
			c1.setGenero("F");
			c1.setDataNasc("11/02/2002");
			ProfissionalDao.save(c1);
			
			Empresa l1 = new Empresa();
			l1.setEmail("ufscar@email.com");
			l1.setNome("Ufscar");
			l1.setPassword(encoder.encode("ufscar"));
			l1.setRole("ROLE_EMPRESA");
			l1.setCnpj("12.345.678/0001-90");
			l1.setCidade("São Carlos");
			EmpresaDao.save(l1);

			Entrevista lo1 = new Entrevista();
			lo1.setData("30/07/2023 15:00");
			lo1.setProfissional(c1);
			lo1.setEmpresa(l1);
			EntrevistaDao.save(lo1);

		};
	}
}