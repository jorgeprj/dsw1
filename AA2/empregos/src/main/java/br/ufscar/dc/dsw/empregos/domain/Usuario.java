package br.ufscar.dc.dsw.empregos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo obrigatório.")
    @Column(nullable = false, length = 256)
    private String nome;

    @NotBlank(message = "Campo obrigatório.")
    @Column(nullable = false, length = 128)
    private String email;

    @NotBlank(message = "Campo obrigatório.")
    @Column(nullable = false, length = 64)
    private String senha;

    @Column(length = 20)
    private String papel;

    // Getters e Setters
		
	public String getId() {
		return id;
	}
	
	public void seId(String id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPapel() {
		return papel;
	}
	
	public void setPapel(String papel) {
		this.papel = papel;
	}
	
}