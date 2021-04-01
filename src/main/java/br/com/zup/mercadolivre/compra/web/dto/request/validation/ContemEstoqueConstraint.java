package br.com.zup.mercadolivre.compra.web.dto.request.validation;

import br.com.zup.mercadolivre.compra.web.dto.request.ProdutoQuantidadeRequest;
import br.com.zup.mercadolivre.global.web.validations.ExistsID;
import br.com.zup.mercadolivre.produto.data.domain.Produto;
import br.com.zup.mercadolivre.produto.data.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Valid;
import java.lang.reflect.Field;

@Component
public class ContemEstoqueConstraint implements ConstraintValidator<ContemEstoque,Object> {

    @Autowired
    private ProdutoRepository produtoRepository;

    private String nameFieldID;
    private String nameFieldQuantidade;
    private Long idValue = null;
    private Integer qtdValue = null;



    @Override
    public void initialize(ContemEstoque constraintAnnotation) {
        this.nameFieldID = constraintAnnotation.nameFieldID();
        this.nameFieldQuantidade = constraintAnnotation.nameFieldQuantidade();
    }


    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        initID(object);
        initQuantidade(object);

        if (!this.produtoRepository.verificarProdutoEstoque(this.idValue, this.qtdValue)){
            return false;
        }

        return true;
    }

    private void initID(Object objetoAnotado){

        try {
            Field idField = objetoAnotado.getClass().getDeclaredField(this.nameFieldID);
            idField.setAccessible(true);
            this.idValue = Long.parseLong(idField.get(objetoAnotado).toString());
            idField.setAccessible(false);
        }catch (NoSuchFieldException | IllegalAccessException se){ }

    }

    private void initQuantidade(Object objetoAnotado){
        try {
            Field qtdField = objetoAnotado.getClass().getDeclaredField(this.nameFieldQuantidade);
            qtdField.setAccessible(true);
            this.qtdValue = Integer.parseInt(qtdField.get(objetoAnotado).toString());
            qtdField.setAccessible(false);
        }catch (NoSuchFieldException | IllegalAccessException se){ }
    }

}
