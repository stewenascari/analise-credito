package com.analisecredito.propostacredito.enums;

import com.sun.istack.NotNull;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum StatusProposta {

    SOLICTADO("Proposta Solicitada", 1),
    ACEITE("Proposta Aceita", 2),
    RECUSADA("Proposta Recusada", 3);

    private final String descricao;
    private final int codigo;

    StatusProposta(String descricao, int codigo) {
        this.descricao = descricao;
        this.codigo = codigo;
    }

    public static StatusProposta fromDescricao(@NotNull String descricao) {
        return Arrays.stream(StatusProposta.values())
                .filter(p -> p.getDescricao().equals(descricao))
                .findFirst()
                .orElse(null);
    }

    public static StatusProposta fromCodigo(@NotNull Integer codigo) {
        return Arrays.stream(StatusProposta.values())
                .filter(p -> p.getCodigo() == (codigo))
                .findFirst()
                .orElse(null);
    }
}
