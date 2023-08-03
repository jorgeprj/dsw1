package br.ufscar.dc.dsw.empregos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.empregos.dao.IEntrevistaDAO;
import br.ufscar.dc.dsw.empregos.domain.Entrevista;
import br.ufscar.dc.dsw.empregos.domain.Usuario;
import br.ufscar.dc.dsw.empregos.service.spec.IEntrevistaService;

@Service
@Transactional(readOnly = false)
public class EntrevistaService implements IEntrevistaService {

	@Autowired
	IEntrevistaDAO dao;
	
	public void salvar(Entrevista entrevista) {
		dao.save(entrevista);
	}

	@Transactional(readOnly = true)
	public Entrevista buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Entrevista> buscarTodosPorUsuario(Usuario u) {
		return dao.findAllByUsuario(u);
	}
}