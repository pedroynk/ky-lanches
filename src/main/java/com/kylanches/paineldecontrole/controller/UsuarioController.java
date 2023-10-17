package com.kylanches.paineldecontrole.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kylanches.paineldecontrole.dto.AlertDTO;
import com.kylanches.paineldecontrole.model.Usuario;
import com.kylanches.paineldecontrole.services.UsuarioService;
import com.kylanches.paineldecontrole.validators.UsuarioValidator;

@Controller
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioValidator usuarioValidator;

    @GetMapping("/listar")
    public ModelAndView list(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("usuario/lista");

        Page<Usuario> usuariosPage = this.usuarioService.listar(pageable);

        usuariosPage.forEach(usuario -> {
            BindingResult result = new BeanPropertyBindingResult(usuario, "usuario");
            usuarioValidator.validate(usuario, result);
        });

        modelAndView.addObject("usuarios", usuariosPage);

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {

        ModelAndView modelAndView = new ModelAndView("usuario/formulario");
        modelAndView.addObject("tiposDeUsuarios", this.usuarioService.listarTiposDeUsuarios());
        modelAndView.addObject("usuario", new Usuario());

        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid Usuario usuario, BindingResult bindingResult, ModelMap model,
            RedirectAttributes attrs, @RequestParam("fotoPerfil") MultipartFile fotoPerfil) throws IOException {

        UsuarioValidator usuarioValidator = new UsuarioValidator();
        usuarioValidator.validate(usuario, bindingResult);
        if (bindingResult.hasErrors()) {
           
            model.addAttribute("tiposDeUsuarios", this.usuarioService.listarTiposDeUsuarios());
            model.addAttribute("usuario", usuario);
            return "usuario/formulario";
        }

        if (fotoPerfil != null && !fotoPerfil.isEmpty()) {
            String filePath = this.usuarioService.salvarFotoDePerfil(fotoPerfil);
            usuario.setFotoPerfilPath(filePath);
        }

        if (usuario.getId() == null) {
            usuarioService.cadastrar(usuario);
        } else {
            usuarioService.cadastrar(usuario);

        }

        attrs.addFlashAttribute("alert", new AlertDTO("Funcionario cadastrado com sucesso!", "alert-success"));

        return "redirect:/usuarios/listar";
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("usuario/formulario");

        modelAndView.addObject("tiposDeUsuarios", this.usuarioService.listarTiposDeUsuarios());
        modelAndView.addObject("usuario", usuarioService.buscarPorId(id));

        return modelAndView;
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        usuarioService.excluirPorId(id);

        return "redirect:/usuarios/listar";
    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("usuario/detalhes");

        modelAndView.addObject("usuario", usuarioService.buscarPorId(id));

        return modelAndView;
    }

    @GetMapping("/ativar/{id}")
    public String ativar(@PathVariable Long id) {
        usuarioService.ativarOuDesativar(id);
        return "redirect:/usuarios/listar";
    }
}