package com.analisecredito.security.usersecurity;

import com.analisecredito.usuario.Usuario;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.stream.Collectors;

public class UserSecurityConverter {

    private UserSecurityConverter() {super();}
    public static UserDetails toUserDetails(Usuario entity){
        var userDetails = new UserSecurity();

        userDetails.setId(entity.getId());
        userDetails.setEmail(entity.getEmail());
        userDetails.setSenha(entity.getSenha());
        userDetails.setAuthorities(Optional.ofNullable(entity.getPerfil())
                .map(perfil -> new SimpleGrantedAuthority(perfil.getDescricao()))
                .stream().collect(Collectors.toList()));

        return userDetails;
    }
}
