package com.analisecredito.propostacredito.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class StatusPropostaEnumConverter implements AttributeConverter<StatusProposta, String> {

    @Override
    public String convertToDatabaseColumn(StatusProposta statusProposta) {
        return Optional.ofNullable(statusProposta)
                .map(StatusProposta::getDescricao)
                .orElse(null);
    }

    @Override
    public StatusProposta convertToEntityAttribute(String descricao) {
        return Optional.ofNullable(descricao)
                .map(d -> StatusProposta.fromDescricao(d))
                .orElse(null);
    }

    public StatusProposta convertToEntityAttributeTwo(Integer codigo) {
        return Optional.ofNullable(codigo)
                .map(d -> StatusProposta.fromCodigo(d))
                .orElse(null);
    }
}
