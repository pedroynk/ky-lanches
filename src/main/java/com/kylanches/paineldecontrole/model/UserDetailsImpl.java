package com.kylanches.paineldecontrole.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private Usuario usuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Perfil perfil = usuario.getPerfil().equals("Administrador") ? Perfil.ADMINISTRADOR : Perfil.CONSULTA;

        return AuthorityUtils.createAuthorityList(perfil.toString());
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    public String getNome() {
        return usuario.getNome();
    }

    public Long getId() {
        return usuario.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
