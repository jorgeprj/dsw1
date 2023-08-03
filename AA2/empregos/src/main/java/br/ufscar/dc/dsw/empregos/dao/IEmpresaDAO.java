package java.br.ufscar.dc.dsw.empregos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.empregos.domain.Empresa;

@SuppressWarnings("unchecked")
public interface IEmpresaDAO extends CrudRepository<Empresa, Long>{
	
	Empresa findById(long id);
	
	List<Empresa> findAll();
	
	Empresa save(Empresa l);
	
	void deleteById(Long id);
	
	Empresa findBycnpj(String cnpj);
	
	Empresa findByemail(String email);
	
	Empresa findBycidade(String cidade);
}