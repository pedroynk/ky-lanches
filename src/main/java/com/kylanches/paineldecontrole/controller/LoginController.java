package com.kylanches.paineldecontrole.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kylanches.paineldecontrole.model.Usuario;
import com.kylanches.paineldecontrole.repository.UsuarioRepository;
import com.kylanches.paineldecontrole.services.UsuarioService;
import com.kylanches.paineldecontrole.utils.AlterarSenha;
import com.kylanches.paineldecontrole.utils.SenhaUtils;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }

    @GetMapping("/perfil")
    public ModelAndView perfil(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("usuario/perfil");

        modelAndView.addObject("logado", this.usuarioService.encontrarPorEmail(principal.getName()).get());
        modelAndView.addObject("idUsuario", this.usuarioService.encontrarPorEmail(principal.getName()).get().getId());
        modelAndView.addObject("alterarSenhaForm", new AlterarSenha());

        return modelAndView;
    }

    @PostMapping("/alterar-senha")
    public String alterarSenha(AlterarSenha form, Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName()).get();

        if (SenhaUtils.matches(form.getSenhaAtual(), usuario.getSenha())) {
            usuario.setSenha(SenhaUtils.encode(form.getNovaSenha()));

            usuarioRepository.save(usuario);
        }

        return "redirect:/perfil";

    }

}
