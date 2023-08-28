package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;


import br.ufscar.dc.dsw.domain.Entrevista;

@SuppressWarnings("unchecked")
public interface IEntrevistaDAO extends CrudRepository<Entrevista, Long>{

	Entrevista findById(long id);

	@Query("SELECT entrevista FROM Entrevista entrevista WHERE entrevista.profissional.id = ?1")
    List<Entrevista> findAllByProfissional(Long profissionalId);

	@Query("SELECT entrevista FROM Entrevista entrevista WHERE entrevista.empresa.id = ?1")
    List<Entrevista> findAllByEmpresa(Long empresaId);
	
	Entrevista save(Entrevista entrevista);
	
	void deleteById(Long id);

	@Query("SELECT entrevista FROM Entrevista entrevista WHERE (entrevista.empresa.id = ?1 OR entrevista.profissional.id = ?2) AND (entrevista.data = ?3) ")
	List<Entrevista> buscarEntrevistasPorProfissionalEEmpresaEData(Long empresa, Long profissional, String data);
}