package br.ufscar.dc.dsw.empregos.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.empregos.domain.Usuario;

public interface IUsuarioService {

	Usuario buscarPorId(Long id);

	List<Usuario> buscarTodos();

	void salvar(Usuario usuario);

	void excluir(Long id);	
}
