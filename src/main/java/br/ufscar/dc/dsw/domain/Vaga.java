package br.ufscar.dc.dsw.domain;

import java.sql.Date;

public class Vaga {
    private Long id;
    private String nome;
    private String descricao;
    private String cnpj;
    private Date dataLimite;

    public Vaga(Long id) {
		this.id = id;
	}

    public Vaga(String nome, String descricao, String cnpj, Date dataLimite) {
        super();
        this.nome = nome;
        this.descricao = descricao;
        this.cnpj = cnpj;
        this.dataLimite = dataLimite;
    }

    public Vaga(Long id, String nome, String descricao, String cnpj, Date dataLimite) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.cnpj = cnpj;
        this.dataLimite = dataLimite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCNPJ() {
        return cnpj;
    }

    public void setCNPJ(String cnpj) {
        this.cnpj = cnpj;
    }

    public Date getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }
}
