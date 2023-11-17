package com.kylanches.paineldecontrole.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kylanches.paineldecontrole.services.UsuarioService;
import com.kylanches.paineldecontrole.services.VendasService;
import com.kylanches.paineldecontrole.validators.UsuarioValidator;
import com.kylanches.paineldecontrole.validators.VendasValidator;

@Configuration
public class ValidatorConfig {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private VendasService vendasService;

    @Bean
    public UsuarioValidator usuarioValidator() {
        return new UsuarioValidator(usuarioService);
    }

    @Bean
    public VendasValidator vendasValidator() {
        return new VendasValidator(vendasService);
    }

}
