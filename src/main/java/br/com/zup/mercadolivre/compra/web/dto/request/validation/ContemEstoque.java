package br.com.zup.mercadolivre.compra.web.dto.request.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ContemEstoqueConstraint.class)
public @interface ContemEstoque {

    String message() default "Não contém essa quantidade em estoque para esse produto";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String nameFieldID() default "id";
    String nameFieldQuantidade() default "quantidade";
}
