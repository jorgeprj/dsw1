package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Entrevista;
import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;


@RestController
public class EmpresaRestController {

    @Autowired
	private IEmpresaService service;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}
	
	private void parse(Empresa empresa, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				empresa.setId(((Integer) id).longValue());
			} else {
				empresa.setId(((Long) id));
			}
		}
		
		empresa.setEmail((String) json.get("email"));
		empresa.setNome((String) json.get("nome"));
		empresa.setPassword((String) json.get("password"));
        empresa.setRole((String) json.get("role"));
        empresa.setCnpj((String) json.get("cnpj"));
        empresa.setCidade((String) json.get("cidade"));
        empresa.setEntrevistas((List<Entrevista>) json.get("entrevistas"));		
	}


	@GetMapping(path = "/empresas")
	public ResponseEntity<List<Empresa>> lista() {
		List<Empresa> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

    @GetMapping(path = "/empresas/cidades/{nome}")
	public ResponseEntity<List<Empresa>> listaEmpresaCidades(@PathVariable("nome") String nome) {
		List<Empresa> lista = service.buscarPorCidade(nome);
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(path = "/empresas/{id}")
	public ResponseEntity<Empresa> lista(@PathVariable("id") long id) {
	    Empresa empresa = service.buscarPorId(id);
		if (empresa == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(empresa);
	}
    
	@PostMapping(path = "/empresas")
	public ResponseEntity<Empresa> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Empresa empresa = new Empresa(); 
				parse(empresa, json);
				service.salvar(empresa);
				return ResponseEntity.ok(empresa);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); 
		}
	}
    

	@PutMapping(path = "/empresas/{id}")
	public ResponseEntity<Empresa> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
			    Empresa empresa = service.buscarPorId(id);
				if (empresa == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(empresa, json);
					service.salvar(empresa);
					return ResponseEntity.ok(empresa);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); 
		}
	}
    

	@DeleteMapping(path = "/empresas/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Empresa empresa = service.buscarPorId(id);
		if (empresa == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
	    }
	}
}