package br.ufscar.dc.dsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.dc.dsw.domain.Entrevista;
import br.ufscar.dc.dsw.service.spec.IEntrevistaService;

@RestController
public class EntrevistaRestController {

	@Autowired
	private IEntrevistaService service;

    @GetMapping(path = "/entrevistas")
	public ResponseEntity<List<Entrevista>> lista() {
		List<Entrevista> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
    @GetMapping(path = "/entrevistas/{id}")
	public ResponseEntity<Entrevista> listaPorId(@PathVariable("id") long id) {
    	Entrevista entrevista = service.buscarPorId(id);
		if (entrevista == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(entrevista);
	}

	@GetMapping(path = "/entrevistas/profissionais/{id}")
	public ResponseEntity<List<Entrevista>> listaPorProfissional(@PathVariable("id") long id) {
		List<Entrevista> listaProfissionais = service.buscarTodosPorProfissional(id);
		if (listaProfissionais.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(listaProfissionais);
	}

    @GetMapping(path = "/entrevistas/empresas/{id}")
	public ResponseEntity<List<Entrevista>> listaPorEmpresa(@PathVariable("id") long id) {
		List<Entrevista> listaEmpresas = service.buscarTodosPorEmpresa(id);
		if (listaEmpresas.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(listaEmpresas);
	}
}