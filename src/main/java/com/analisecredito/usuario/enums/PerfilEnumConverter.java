package com.analisecredito.usuario.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class PerfilEnumConverter implements AttributeConverter<Perfil, String> {

    @Override
    public String convertToDatabaseColumn(Perfil perfil) {
        return Optional.ofNullable(perfil)
                .map(Perfil::getDescricao)
                .orElse(null);
    }

    @Override
    public Perfil convertToEntityAttribute(String descricao) {
        return Optional.ofNullable(descricao)
                .map(d -> Perfil.fromDescricao(d))
                .orElse(null);
    }

    public Perfil convertToEntityAttributeTwo(Integer codigo) {
        return Optional.ofNullable(codigo)
                .map(d -> Perfil.fromCodigo(d))
                .orElse(null);
    }
}
