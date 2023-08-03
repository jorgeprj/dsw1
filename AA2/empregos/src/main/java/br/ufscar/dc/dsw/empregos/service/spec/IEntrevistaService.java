package br.ufscar.dc.dsw.empregos.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.empregos.domain.Entrevista;
import br.ufscar.dc.dsw.empregos.domain.Usuario;

public interface IEntrevistaService {

	Entrevista buscarPorId(Long id);

	List<Entrevista> buscarTodosPorUsuario(Usuario u);
	
	void salvar(Entrevista entrevista);
}
