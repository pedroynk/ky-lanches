package com.kylanches.paineldecontrole.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kylanches.paineldecontrole.model.Vendas;
import com.kylanches.paineldecontrole.services.VendasService;

public class VendasValidator implements Validator {
    public VendasValidator(VendasService vendasService) {
    }

    public VendasValidator() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Vendas.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numero", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataVenda", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "formaPagamento", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantidade", "NotEmpty");
        ValidationUtils.rejectIfEmpty(errors, "dataVenda", "NotEmpty.vendas.dataVenda");

    }

}
