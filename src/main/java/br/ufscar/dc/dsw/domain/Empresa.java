package br.ufscar.dc.dsw.domain;

public class Empresa extends Usuario {

    private String cnpj;
    private String cidade;

    public Empresa(Long id) {
        super(id);
    }

    public Empresa(Long id, String nome, String email, String senha, String papel, String cnpj, String cidade) {
        super(id, nome, email, senha, papel);
        this.cnpj = cnpj;
        this.cidade = cidade;
    }

    public Empresa(String nome, String email, String senha, String papel, String cnpj, String cidade) {
        super(nome, email, senha, papel);
        this.cnpj = cnpj;
        this.cidade = cidade;
    }

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
