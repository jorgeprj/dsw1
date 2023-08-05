package br.ufscar.dc.dsw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.IEntrevistaDAO;
import br.ufscar.dc.dsw.domain.Entrevista;

@Component
public class UniqueEntrevistaValidator implements ConstraintValidator<UniqueEntrevista, Entrevista> {

    @Autowired
    private IEntrevistaDAO dao;

    @Override
    public boolean isValid(Entrevista locacao, ConstraintValidatorContext context) {
        if(dao != null && locacao != null) {
        	return dao.buscarEntrevistasPorProfissionalEEmpresaEData(locacao.getProfissional().getId(), locacao.getEmpresa().getId(), locacao.getData()).isEmpty();
        }
        return true;
    }
}