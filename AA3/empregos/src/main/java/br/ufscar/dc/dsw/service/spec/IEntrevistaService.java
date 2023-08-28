package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Entrevista;

public interface IEntrevistaService {

	Entrevista buscarPorId(Long id);

	List<Entrevista> buscarTodosPorProfissional(Long id);

	List<Entrevista> buscarTodosPorEmpresa(Long id);
	
    List<Entrevista> buscarEntrevistasPorProfissionalEEmpresaEData(Long profissionalId, Long empresaId, String data);
	
	void salvar(Entrevista entrevista);
	
    void excluir(Long id);
}
