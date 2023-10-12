package com.kylanches.paineldecontrole.validators;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kylanches.paineldecontrole.model.Vendas;
import com.kylanches.paineldecontrole.services.VendasService;

public class VendasValidator implements Validator {
    public VendasValidator(VendasService vendasService) {
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Vendas.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numero", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataVenda", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lanche", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "formaPagamento", "NotEmpty");
    }

}
