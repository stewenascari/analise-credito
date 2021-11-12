package com.analisecredito.usuario.enums;

import com.sun.istack.NotNull;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Perfil {

    SOLICITANTE("ROLE_PORTADOR_CARTAO", 1),
    ANALISTA("ROLE_ANALISTA_CREDITO", 2);

    private final String descricao;
    private final int codigo;

    Perfil(String descricao, int codigo) {
        this.descricao = descricao;
        this.codigo = codigo;
    }

    public static Perfil fromDescricao(@NotNull String descricao) {
        return Arrays.stream(Perfil.values())
                .filter(p -> p.getDescricao().equals(descricao))
                .findFirst()
                .orElse(null);
    }

    public static Perfil fromCodigo(@NotNull Integer codigo) {
        return Arrays.stream(Perfil.values())
                .filter(p -> p.getCodigo() == (codigo))
                .findFirst()
                .orElse(null);
    }
}
