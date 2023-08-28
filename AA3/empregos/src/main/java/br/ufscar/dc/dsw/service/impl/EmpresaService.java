package br.ufscar.dc.dsw.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.dao.IEmpresaDAO;
import br.ufscar.dc.dsw.dao.IEntrevistaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.domain.Entrevista;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;
import java.util.Optional;


@Service
@Transactional(readOnly = false)
public class EmpresaService implements IEmpresaService {

	@Autowired
	IEmpresaDAO dao;
	@Autowired
	IEntrevistaDAO entrevistaDao;
	
	public void salvar(Empresa empresa) {
		dao.save(empresa);
	}
	
	public void excluir(Long id) {
        Optional<Empresa> optionalEmpresa = dao.findById(id);
        if (optionalEmpresa.isPresent()) {
            Empresa empresa = optionalEmpresa.get();
            List<Entrevista> entrevistas = empresa.getEntrevistas();
            for (Entrevista entrevista : entrevistas) {
                entrevista.setEmpresa(null); 
                entrevistaDao.save(entrevista); 
            }
            dao.delete(empresa);
		}
	}

	@Transactional(readOnly = true)
	public Empresa buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}
	
	@Transactional(readOnly = true)
	public List<Empresa> buscarTodos() {
		return dao.findAll();

	}    

	@Transactional(readOnly = true)
	public Empresa buscarPorEmail(String email){
		return dao.findByEmail(email);
	}

	@Override
	public List<Empresa> buscarPorCidade(String cidade) {
    	return dao.findByCidade(cidade);
	}

	@Override
    public List<String> buscarCidadesDisponiveis() {
        return dao.findDistinctCidades();
    }

}