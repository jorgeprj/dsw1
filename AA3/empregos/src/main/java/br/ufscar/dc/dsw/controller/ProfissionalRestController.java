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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Entrevista;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;

@RestController
public class ProfissionalRestController {

	@Autowired
	private IProfissionalService service;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}
	
	private void parse(Profissional profissional, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				profissional.setId(((Integer) id).longValue());
			} else {
				profissional.setId(((Long) id));
			}
		}
		
		profissional.setEmail((String) json.get("email"));
		profissional.setNome((String) json.get("nome"));
		profissional.setPassword((String) json.get("password"));
        profissional.setRole((String) json.get("role"));
        profissional.setCpf((String) json.get("cpf"));
		profissional.setTelefone((String) json.get("telefone"));
		profissional.setGenero((String) json.get("genero"));
   		profissional.setDataNasc((String) json.get("dataNasc"));
        profissional.setEntrevistas((List<Entrevista>) json.get("entrevistas"));		
		
	}

	@GetMapping(path = "/profissionais")
	public ResponseEntity<List<Profissional>> lista() {
		List<Profissional> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(path = "/profissionais/{id}")
	public ResponseEntity<Profissional> lista(@PathVariable("id") long id) {
		Profissional profissional = service.buscarPorId(id);
		if (profissional == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(profissional);
	}
    
	@PostMapping(path = "/profissionais")
	public ResponseEntity<Profissional> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Profissional profissional = new Profissional(); 
				parse(profissional, json);
				profissional.setRole("ROLE_PROFISSIONAL");
				service.salvar(profissional);
				return ResponseEntity.ok(profissional);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
    
	@PutMapping(path = "/profissionais/{id}")
	public ResponseEntity<Profissional> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Profissional profissional = service.buscarPorId(id);
				if (profissional == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(profissional, json);
					service.salvar(profissional);
					return ResponseEntity.ok(profissional);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
    
	@DeleteMapping(path = "/profissionais/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Profissional profissional = service.buscarPorId(id);
		if (profissional == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
	}
	}
}