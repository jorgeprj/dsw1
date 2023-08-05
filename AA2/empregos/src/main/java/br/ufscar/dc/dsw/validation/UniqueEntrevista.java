package br.ufscar.dc.dsw.validation;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEntrevistaValidator.class)
public @interface UniqueEntrevista {
	
    String message() default "Já existe uma entrevista para esse profissional ou empresa neste dia/horário";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
}