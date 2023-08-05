package br.ufscar.dc.dsw.service.spec;
import java.util.List;
import br.ufscar.dc.dsw.domain.Empresa;

public interface IEmpresaService {
    void salvar(Empresa empresa);
    void excluir(Long id);
	Empresa buscarPorId(Long id);
	List<Empresa> buscarTodos();
    Empresa buscarPorEmail(String email);
    List<Empresa> buscarPorCidade(String cidade);
    List<String> buscarCidadesDisponiveis();
}