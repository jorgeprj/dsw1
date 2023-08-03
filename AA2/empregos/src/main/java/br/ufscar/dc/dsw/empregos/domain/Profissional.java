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
@Table(name = "Profissional")
public class Profissional {

    @Id
    private Long id;

    @Column(nullable = false, length = 20)
    private String cpf;

    @Column(length = 20)
    private String telefone;

    @Column(length = 10)
    private String sexo;

    @Column
    private Date data_nascimento;

    @OneToOne
    @JoinColumn(name = "id")
    private Usuario usuario;

    // getters e setters
	
	public String getCPF() {
		return cpf;
	}
	
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

    public void getData(Data data){
        return data;
    }

    public void setData(Data data){
        this.data = data;
    }
}