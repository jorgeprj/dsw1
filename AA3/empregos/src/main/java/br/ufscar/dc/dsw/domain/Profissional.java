package br.ufscar.dc.dsw.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.ufscar.dc.dsw.validation.UniqueCpf;

import javax.persistence.OneToMany;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "entrevistas" })
@Entity
@Table(name = "Profissional")
public class Profissional extends Usuario {
    
    @NotBlank
	@UniqueCpf(message = "CPF já cadastrado")
	@Size(min = 11, max = 15, message = "Número de caracteres inválido")
    @Column(nullable = false, length = 45 )
    private String cpf;
    
    @Column(nullable = false, length = 64)
    private String telefone;
    
    @Column(nullable = false, length = 45)
    private String genero;
    
    @Column(nullable = false, length = 15)
    private String dataNasc;

	@OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL)
    private List<Entrevista> entrevistas;
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String getDataNasc() {
		return dataNasc;
	}
	
	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public List<Entrevista> getEntrevistas() {
        return entrevistas;
    }

    public void setEntrevistas(List<Entrevista> entrevistas) {
        this.entrevistas = entrevistas;
    }
}