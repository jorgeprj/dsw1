package br.ufscar.dc.dsw.domain;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import br.ufscar.dc.dsw.validation.UniqueCnpj;
import javax.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@SuppressWarnings("serial")
@JsonIgnoreProperties(value = { "entrevistas" })
@Entity
@Table(name = "Empresa")
public class Empresa extends Usuario {

	@NotBlank
    @UniqueCnpj (message = "CNPJ já cadastrado")
	@Size(min = 13, max = 18, message = "Número de caracteres inválido")
	@Column(nullable = false, unique = true, length = 60)
	private String cnpj;

    @Column(nullable = true, length = 50)
    private String cidade;

	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Entrevista> entrevistas;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

    public String getCidade(){
        return cidade;
    }

    public void setCidade(String cidade){
        this.cidade = cidade;
    }

	public List<Entrevista> getEntrevistas() {
        return entrevistas;
    }

    public void setEntrevistas(List<Entrevista> entrevistas) {
        this.entrevistas = entrevistas;
    }
}