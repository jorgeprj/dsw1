package br.ufscar.dc.dsw.domain;

import java.sql.Date;

public class Profissional extends Usuario {

    private String CPF;
    private String telefone;
    private Date dataNascimento;

    public Profissional(Long id) {
        super(id);
    }

    public Profissional(String nome, String email, String senha, String cpf, String telefone, Date dataNascimento) {
        super(nome, email, senha, "PROFISSIONAL");
        this.CPF = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    public Profissional(Long id, String nome, String email, String senha, String cpf, String telefone, Date dataNascimento) {
        super(id, nome, email, senha, "PROFISSIONAL");
        this.CPF = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return CPF;
    }

    public void setCpf(String cpf) {
        this.CPF = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
