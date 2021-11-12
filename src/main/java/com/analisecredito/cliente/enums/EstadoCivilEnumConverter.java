package com.analisecredito.cliente.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class EstadoCivilEnumConverter  implements AttributeConverter<EstadoCivil, String> {

    @Override
    public String convertToDatabaseColumn(EstadoCivil estadoCivil) {
        return Optional.ofNullable(estadoCivil)
                .map(EstadoCivil::getDescricao)
                .orElse(null);
    }

    @Override
    public EstadoCivil convertToEntityAttribute(String descricao) {
        return Optional.ofNullable(descricao)
                .map(d -> EstadoCivil.fromDescricao(d))
                .orElse(null);
    }
}
