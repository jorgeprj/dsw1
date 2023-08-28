package br.ufscar.dc.dsw.validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    public boolean isValid(Entrevista entrevista, ConstraintValidatorContext context) {
        return true;
    }
}