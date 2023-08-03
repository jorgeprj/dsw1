package br.ufscar.dc.dsw.empregos.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.empregos.domain.Entrevista;

@SuppressWarnings("unchecked")
public interface IEntrevistaDAO extends CrudRepository<Entrevista, Long>{
	
	Entrevista findById(long id);
	
	List<Entrevista> findAll();
	
	Entrevista save(Entrevista c);
	
	void deleteById(Long id);
}