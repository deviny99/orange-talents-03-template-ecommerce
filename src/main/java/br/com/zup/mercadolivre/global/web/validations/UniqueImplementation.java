package br.com.zup.mercadolivre.global.web.validations;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueImplementation implements ConstraintValidator<Unique,Object> {

    @PersistenceContext
    EntityManager entityManager;
    private String domainName;
    private String fieldName;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.domainName = constraintAnnotation.targetEntity().getSimpleName();
        this.fieldName = constraintAnnotation.nameField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        return isExistValueField(value);
    }

    private boolean isExistValueField(Object value){
        StringBuilder queryBuilder = new StringBuilder();
        Query query = this.entityManager.createQuery(queryBuilder.append("SELECT 1 FROM ")
        .append(this.domainName)
                .append(" WHERE ")
        .append(this.fieldName)
                .append(" = :valor").toString());
        query.setParameter("valor", value);
        return query.getResultList().isEmpty();
    }
}
