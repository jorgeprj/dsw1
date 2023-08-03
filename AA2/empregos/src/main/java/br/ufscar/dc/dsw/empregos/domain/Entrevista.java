package br.ufscar.dc.dsw.empregos.domain;

import java.time.LocalDateTime;
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
@Table(name = "Entrevista")
public class Entrevista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf_profissional", nullable = false, length = 11)
    private String cpfProfissional;

    @Column(name = "cnpj_empresa", nullable = false, length = 15)
    private String cnpjEmpresa;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    // getters e setters

    public string getCpfProfissional(String cpfProfissional){
        return cpfProfissional;
    }

    public void setCpfProfissional(String cpfProfissional){
        this.cpfProfissional = cpfProfissional;
    }

    public string getCnpjEmpresa(String cnpjEmpresa){
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa){
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public string getDataHora(LocalDateTime dataHora){
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora){
        this.dataHora = dataHora;
    }
}

