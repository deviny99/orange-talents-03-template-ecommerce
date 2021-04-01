package br.com.zup.mercadolivre.global.web.validations;

import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ExistsIDImplementation implements ConstraintValidator<ExistsID, Object> {

    @PersistenceContext
    EntityManager entityManager;
    private String domainName;
    private String nameFieldID;
    private boolean requiredField = false;

    @Override
    public void initialize(ExistsID constraintAnnotation) {
        this.domainName = constraintAnnotation.targetEntity().getSimpleName();
        this.nameFieldID = constraintAnnotation.nameFieldID();
        this.requiredField = constraintAnnotation.requiredField();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {


        if (this.requiredField == false && object == null)
        {
            return true;
        }

        return !isExists(object);
    }

    private boolean isExists(Object valueID){
        StringBuilder queryBuilder = new StringBuilder();
        Query query = this.entityManager.createQuery(
                queryBuilder.append("SELECT 1 FROM ")
                .append(this.domainName)
                .append(" WHERE ")
                .append(nameFieldID)
                .append(" = :id")
                .toString()
        );
        query.setParameter("id", valueID);
        return query.getResultList().isEmpty();
    }

}