package com.kylanches.paineldecontrole.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kylanches.paineldecontrole.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    
    List<Usuario> findByPerfil(String perfil);

    Optional<Usuario> findByEmail(String email);

    Page<Usuario> findAll(Pageable pageable);

}
