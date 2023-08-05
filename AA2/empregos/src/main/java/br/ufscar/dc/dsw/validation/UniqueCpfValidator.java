package br.ufscar.dc.dsw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;

@Component
public class UniqueCpfValidator implements ConstraintValidator<UniqueCpf, String> {

	@Autowired
	private IProfissionalDAO dao;

	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		if (dao != null) {
			Profissional profissional = dao.findByCpf(cpf);
			return profissional == null;
		} else {
			return true;
		}
	}
}