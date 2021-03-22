package br.com.zup.global.web.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = UniqueImplementation.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {

    String message() default "Invalid Argument";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<?> targetEntity();
    String nameField();

}
