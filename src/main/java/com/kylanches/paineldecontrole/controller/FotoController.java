package com.kylanches.paineldecontrole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kylanches.paineldecontrole.services.UsuarioService;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FotoController {

    @Autowired
    private UsuarioService usuarioService;

    @Value("${sem.imagem}")
    private String imagemPadrao;

    @GetMapping("/files/{idUsuario}")
    public ResponseEntity<Resource> serveFile(@PathVariable Long idUsuario) {
        String path = this.usuarioService.getPathFotoPerfil(idUsuario);
        Path file = null;

        if (path == null) {
            path = this.imagemPadrao;
            file = Paths.get(path);
        } else {
            file = Paths.get(this.usuarioService.getPathFotoPerfil(idUsuario));
        }

        Resource resource;
        try {
            resource = new UrlResource(file.toUri());

        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(resource);
    }
}
