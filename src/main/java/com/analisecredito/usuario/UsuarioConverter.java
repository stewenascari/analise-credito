package com.analisecredito.usuario;

import com.analisecredito.usuario.perfil.Perfil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.Optional;

public class UsuarioConverter {

    private UsuarioConverter() {super();}

    public static UsuarioDTO toDto(Usuario entity){
        return UsuarioDTO.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .perfil(Optional.ofNullable(entity.getPerfil())
                        .map(Perfil::getDescricao)
                        .orElse(null))
                .build();
    }

    public static Usuario fromDto(UsuarioCadastroDTO cadastroDTO){
        var entity = new Usuario();
        var pe = new BCryptPasswordEncoder();

        entity.setEmail(cadastroDTO.getEmail());
        entity.setEmail(cadastroDTO.getEmail());
        entity.setSenha(pe.encode(cadastroDTO.getSenha()));

        return entity;

    }
}