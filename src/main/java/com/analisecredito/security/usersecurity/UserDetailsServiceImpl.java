package com.analisecredito.security.usersecurity;

import com.analisecredito.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UsuarioService usuarioService;

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService){this.usuarioService = usuarioService;}


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return Optional.ofNullable(usuarioService.findByEmail(email))
                .map(UserSecurityConverter::toUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
