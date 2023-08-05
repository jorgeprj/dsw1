package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IEntrevistaDAO;
import br.ufscar.dc.dsw.domain.Entrevista;
import br.ufscar.dc.dsw.service.spec.IEntrevistaService;

@Service
@Transactional(readOnly = false)
public class EntrevistaService implements IEntrevistaService {

	@Autowired
	IEntrevistaDAO dao;
	
	public void salvar(Entrevista entrevista) {
		dao.save(entrevista);
	}
	
	public void excluir(Long id) {
		dao.deleteById(id);
	}
	
	@Transactional(readOnly = true)
    public List<Entrevista> buscarEntrevistasPorProfissionalEEmpresaEData(Long profissionalId, Long empresaId, String data) {
        return dao.buscarEntrevistasPorProfissionalEEmpresaEData(profissionalId.longValue(), empresaId.longValue(), data);
    }


	@Transactional(readOnly = true)
	public Entrevista buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Entrevista> buscarTodosPorProfissional(Long id) {
		return dao.findAllByProfissional(id);
	}

	@Transactional(readOnly = true)
	public List<Entrevista> buscarTodosPorEmpresa(Long id) {
		return dao.findAllByEmpresa(id);
	}

}