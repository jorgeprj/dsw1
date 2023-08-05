package br.ufscar.dc.dsw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.IEmpresaDAO;
import br.ufscar.dc.dsw.domain.Empresa;

@Component
public class UniqueCnpjValidator implements ConstraintValidator<UniqueCnpj, String> {

	@Autowired
	private IEmpresaDAO dao;

	@Override
	public boolean isValid(String CNPJ, ConstraintValidatorContext context) {
		if (dao != null) {
			Empresa empresa = dao.findByCNPJ(CNPJ);
			return empresa == null;
		} else {
			return true;
		}
	}
}