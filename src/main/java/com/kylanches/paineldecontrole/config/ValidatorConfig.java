package com.kylanches.paineldecontrole.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kylanches.paineldecontrole.validators.UsuarioValidator;
import com.kylanches.paineldecontrole.validators.VendasValidator;

@Configuration
public class ValidatorConfig {

    @Bean
    public UsuarioValidator usuarioValidator() {
        return new UsuarioValidator();
    }

    @Bean
    public VendasValidator vendasValidator() {
        return new VendasValidator(null);
    }

}
