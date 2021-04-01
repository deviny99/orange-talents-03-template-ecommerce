package br.com.zup.mercadolivre.global.web.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsIDImplementation.class)
public @interface ExistsID {

    String message() default "Invalid Argument";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?> targetEntity();
    String nameFieldID();
    boolean requiredField() default false;
}
