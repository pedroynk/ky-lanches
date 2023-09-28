package com.kylanches.paineldecontrole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.kylanches.paineldecontrole.model.Usuario;
import com.kylanches.paineldecontrole.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
@EnableJpaAuditing
public class PaineldecontroleApplication implements CommandLineRunner {

	@Value("${usuario.nome}")
	private String usuarioNome;

	@Value("${usuario.email}")
	private String usuarioEmail;

	@Value("${usuario.senha}")
	private String usuarioSenha;

	@Value("${usuario.perfil}")
	private String usuarioPerfil;

	@Autowired
	private UsuarioService usuarioService;


	public static void main(String[] args) {
		SpringApplication.run(PaineldecontroleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (usuarioService.buscarTodos().isEmpty()) {

			Usuario usuario = Usuario.builder()
					.nome(usuarioNome)
					.email(usuarioEmail)
					.senha(usuarioSenha)
					.perfil(usuarioPerfil)
					.ativo(true)
					.build();

			usuarioService.cadastrar(usuario);
		}

	}

}
