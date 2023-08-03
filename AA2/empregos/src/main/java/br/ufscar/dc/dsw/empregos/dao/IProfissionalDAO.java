package br.ufscar.dc.dsw.empregos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.empregos.domain.Profissional;

@SuppressWarnings("unchecked")
public interface IProfissionalDAO extends CrudRepository<Profissional, Long>{
	Profissional findById(long id);

	List<Profissional> findAll();
	
	Profissional save(Profissional profissional);

	void deleteById(Long id);

	//@Query("SELECT profissional FROM Profissional profissional WHERE profissional.cpf = :cpf")
	Profissional findBycpf(String cpf);
}