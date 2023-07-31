package br.ufscar.dc.dsw.domain;

public class Entrevista {

    private Long id;
    private String cpfProfissional;
    private String cnpjEmpresa;
    private String dataHora;

    public Entrevista(Long id) {
        this.id = id;
    }

    public Entrevista(Long id, String cpfProfissional, String cnpjEmpresa, String dataHora) {
        this.id = id;
        this.cpfProfissional = cpfProfissional;
        this.cnpjEmpresa = cnpjEmpresa;
        this.dataHora = dataHora;
    }

    public Entrevista(String cpfProfissional, String cnpjEmpresa, String dataHora) {
        this.cpfProfissional = cpfProfissional;
        this.cnpjEmpresa = cnpjEmpresa;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfProfissional() {
        return cpfProfissional;
    }

    public void setCpfProfissional(String cpfProfissional) {
        this.cpfProfissional = cpfProfissional;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}
