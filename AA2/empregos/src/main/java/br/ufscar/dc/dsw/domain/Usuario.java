package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import br.ufscar.dc.dsw.validation.UniqueEmail;
 
@Entity
@Table(name = "Usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends AbstractEntity<Long> {
	
	@NotBlank
	@UniqueEmail(message = "Email já cadastrado")
    @Column(nullable = false, length = 45, unique = true)
    private String email;
    
	@NotBlank
    @Column(nullable = false, length = 64)
    private String nome;

	@NotBlank
    @Column(nullable = false, length = 64)
    private String password;
    
	@NotBlank
    @Column(nullable = false, length = 45)
    private String role;
	
	public String getEmail() {
		return email;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}