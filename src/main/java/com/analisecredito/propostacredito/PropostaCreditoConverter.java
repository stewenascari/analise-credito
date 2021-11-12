package com.analisecredito.propostacredito;


import com.analisecredito.cliente.Cliente;
import com.analisecredito.propostacredito.enums.StatusProposta;

import java.util.Optional;

public class PropostaCreditoConverter {

    private PropostaCreditoConverter() {super();}

    public static PropostaCreditoDTO toDto(PropostaCredito entity){
        return PropostaCreditoDTO.builder()
                .id(entity.getId())
                .status(Optional.ofNullable(entity.getStatus())
                        .map(StatusProposta::getDescricao)
                        .orElse(null))
                .dataInicioProposta(entity.getDataInicioProposta())
                .idCliente(Optional.ofNullable(entity.getCliente())
                        .map(Cliente::getId)
                        .orElse(null))
                .build();
    }

    public static PropostaCredito toDto(PropostaCreditoCadastroDTO cadastroDTO){
        var entity = new PropostaCredito();
        entity.setDataInicioProposta(cadastroDTO.getDataInicioProposta());
        entity.setStatus(Optional.ofNullable(cadastroDTO.getStatus())
                .map(StatusProposta::fromDescricao)
                .orElse(null));

        return entity;

    }
}
