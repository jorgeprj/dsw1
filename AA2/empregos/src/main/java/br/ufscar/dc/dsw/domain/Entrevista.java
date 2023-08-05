package br.ufscar.dc.dsw.domain;

import br.ufscar.dc.dsw.validation.UniqueEntrevista;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@UniqueEntrevista
@Table(name = "Entrevista")
public class Entrevista extends AbstractEntity<Long> {

	@NotNull
	@Column(nullable = false, length = 19)
	private String data;

	@NotNull
    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;

    @NotNull
	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

    public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}   
}
