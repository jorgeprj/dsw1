package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Empresa;

@SuppressWarnings("unchecked")
public interface IEmpresaDAO extends CrudRepository<Empresa, Long>{
	Empresa findById(long id);
	List<Empresa> findAll();
	Empresa save(Empresa empresa);
	void deleteById(Long id);

	@Query("SELECT empresa FROM Empresa empresa WHERE empresa.cnpj = :cnpj")
	Empresa findByCNPJ(@Param ("cnpj") String cnpj);

	@Query("SELECT empresa FROM Empresa empresa WHERE empresa.email = :email")
	Empresa findByEmail(@Param ("email") String email);

	@Query("SELECT empresa FROM Empresa empresa WHERE empresa.cidade = :cidade")
    List<Empresa> findByCidade(@Param("cidade") String cidade);

	@Query("SELECT DISTINCT empresa.cidade FROM Empresa empresa")
    List<String> findDistinctCidades();

}