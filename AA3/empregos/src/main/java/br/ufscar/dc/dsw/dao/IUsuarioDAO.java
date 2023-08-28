package br.ufscar.dc.dsw.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {
    @Query("SELECT usuario FROM Usuario usuario WHERE usuario.email = :email")
	Usuario buscarPorEmail(@Param ("email") String email);
}