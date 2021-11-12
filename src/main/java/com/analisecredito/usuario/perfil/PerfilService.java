package com.analisecredito.usuario.perfil;

import com.analisecredito.exceptions.RecursoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PerfilService {

    private final PerfilRepository repository;

    public Perfil findPerfilPorDescricao(String descricao) {
        return Optional.ofNullable(repository.findByDescricaoIgnoreCase(descricao))
                .orElseThrow(() -> new RecursoNaoEncontradoException("Perfil PME: " + descricao));
    }

    public Perfil findPerfilPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Perfil PME: " + id));
    }

}
