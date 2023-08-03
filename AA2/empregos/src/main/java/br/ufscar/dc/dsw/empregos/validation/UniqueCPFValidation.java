package br.ufscar.dc.dsw.empregos.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;

@Component
public class UniqueCPFValidator implements ConstraintValidator<UniqueCPF, String> {

	@Autowired
	private IProfissionalDAO dao;

	@Override
	public boolean isValid(String CPF, ConstraintValidatorContext context) {
		if (dao != null) {
			Profissional profissional = dao.findByCPF(CPF);
			return profissional == null;
		} else {
			return true;
		}

	}
}