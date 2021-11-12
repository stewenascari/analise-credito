package com.analisecredito.cliente.enums;

import com.sun.istack.NotNull;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EstadoCivil {

    SOLTEIRO("Solteiro(a)"),
    CASADO("Casado(a)"),
    SEPARADO("Separado(a)"),
    DIVORCIADO("Divorciado(a)"),
    VIUVO("Viúvo(a)");

    private final String descricao;

    EstadoCivil(String descricao) {
        this.descricao = descricao;
    }

    public static EstadoCivil fromDescricao(@NotNull String descricao) {
        return Arrays.stream(EstadoCivil.values())
                .filter(p -> p.getDescricao().equals(descricao))
                .findFirst()
                .orElse(null);
    }
}
