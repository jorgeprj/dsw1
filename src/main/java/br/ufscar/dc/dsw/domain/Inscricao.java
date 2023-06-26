package br.ufscar.dc.dsw.domain;

import java.sql.Date;

public class Inscricao {
    private Long id;
    private Date data;
    private String cpf;
    private String curriculo;
    private Vaga vaga;

    public Inscricao(Long id) {
        this.id = id;
    }

    public Inscricao(Date data, String cpf, String curriculo, Vaga vaga) {
		super();
        this.data = data;
        this.cpf = cpf;
        this.curriculo = curriculo;
        this.vaga = vaga;
	}

    public Inscricao(Long id, Date data, String cpf, String curriculo, Vaga vaga) {
        this.id = id;
        this.data = data;
        this.cpf = cpf;
        this.curriculo = curriculo;
        this.vaga = vaga;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(String curriculo) {
        this.curriculo = curriculo;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
}
