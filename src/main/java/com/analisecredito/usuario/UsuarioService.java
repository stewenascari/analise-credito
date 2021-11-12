package com.analisecredito.usuario;

import com.analisecredito.usuario.perfil.PerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder pe;
    private PerfilService perfilService;
    @Autowired
    public void setPerfilService(PerfilService perfilService){this.perfilService = perfilService;}


    public Usuario findByEmail(String email){
        return repository.findByEmail(email);
    }

    public Usuario findById(Long id){
        return repository.findById(id)
                .orElseThrow();
    }

    public UsuarioDTO insert(UsuarioCadastroDTO cadastroDTO){
        var entity = UsuarioConverter.fromDto(cadastroDTO);
        var perfil = perfilService.findPerfilPorId(cadastroDTO.getPerfilId());
        entity.setPerfil(perfil);

        return Optional.ofNullable(repository.save(entity))
                .map(UsuarioConverter::toDto)
                .orElseThrow();
    }

    public UsuarioDTO update(@NotNull Long id, UsuarioCadastroDTO cadastroDTO){
        var entity = this.findById(id);
        this.prepareUsuario(entity, cadastroDTO);
        return Optional.ofNullable(entity)
                .map(repository::save)
                .map(UsuarioConverter::toDto)
                .orElseThrow();
    }

    private void prepareUsuario(Usuario entity, UsuarioCadastroDTO cadastroDTO){
        entity.setEmail(cadastroDTO.getEmail());
        entity.setSenha(pe.encode(cadastroDTO.getSenha()));
    }
}
