package br.ufscar.dc.dsw.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.dao.IEntrevistaDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Entrevista;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional(readOnly = false)
public class ProfissionalService implements IProfissionalService {

	@Autowired
	IProfissionalDAO dao;

	@Autowired
	IEntrevistaDAO entrevistaDao;

	@PersistenceContext
    private EntityManager entityManager;

	public void salvar(Profissional profissional) {
		dao.save(profissional);
	}

	public void excluir(Long id) {
		Optional<Profissional> optionalProfissional = dao.findById(id);
		if (optionalProfissional.isPresent()) {
			Profissional profissional = optionalProfissional.get();
			List<Entrevista> entrevistas = profissional.getEntrevistas();
			for (Entrevista entrevista : entrevistas) {
				entrevista.setProfissional(null); 
				entrevistaDao.save(entrevista);
			}
			dao.delete(profissional);
		}
	}

	@Transactional(readOnly = true)
	public Profissional buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}
	
	@Transactional(readOnly = true)
	public List<Profissional> buscarTodos() {
		return dao.findAll();
	}    

	@Transactional(readOnly = true)
	public Profissional buscarPorEmail(String email){
		return dao.findByEmail(email);
	}
}