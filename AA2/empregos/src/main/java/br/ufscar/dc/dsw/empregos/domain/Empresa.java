package br.ufscar.dc.dsw.empregos.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.ufscar.dc.dsw.empregos.validation.UniqueCNPJ;

@SuppressWarnings("serial")
@Entity
@Table(name = "Empresa")
public class Empresa {

    @Id
    private Long id;

    @Column(nullable = false, length = 32)
    private String cnpj;

    @Column(nullable = false, length = 50)
    private String cidade;

    @OneToOne
    @JoinColumn(name = "id")
    private Usuario usuario;
    
    // Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<Entrevista> getEntrevistas() {
		return entrevistas;
	}

	public void setEntrevistas(List<Entrevista> entrevistas) {
		this.entrevistas = entrevistas;
	}
}