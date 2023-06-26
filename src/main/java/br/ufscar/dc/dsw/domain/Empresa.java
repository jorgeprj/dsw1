package br.ufscar.dc.dsw.domain;

public class Empresa extends Usuario{
    private String CNPJ;
    private String cidade;

    public Empresa(Long id) {
        super(id);
    }

    public Empresa(String nome, String email, String senha, String cnpj, String cidade) {
        super(nome, email, senha, "EMPRESA");
        this.CNPJ = cnpj;
        this.cidade = cidade;
    }

    public Empresa(Long id, String nome, String email, String senha, String cnpj, String cidade){
        super(id, nome, email, senha, "EMPRESA");
        this.CNPJ = cnpj;
        this.cidade = cidade;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String cnpj) {
        CNPJ = cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
