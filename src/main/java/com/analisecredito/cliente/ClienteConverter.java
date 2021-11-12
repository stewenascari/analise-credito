package com.analisecredito.cliente;

import com.analisecredito.cliente.enums.EstadoCivil;

import java.util.Optional;

public class ClienteConverter {

    private ClienteConverter() {super();}

    public static ClienteDTO toDto(Cliente entity){
        return ClienteDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .mae(entity.getMae())
                .email(entity.getEmail())
                .dataNascimento(entity.getDataNascimento())
                .estadoCivil(Optional.ofNullable(entity.getEstadoCivil())
                        .map(EstadoCivil::getDescricao)
                        .orElse(null))
                .rg(entity.getRg())
                .cpf(entity.getCpf())
                .salario(entity.getSalario())
                .build();
    }

    public static Cliente toDto(ClienteCadastroDTO cadastroDTO){
        var entity = new Cliente();

        entity.setNome(cadastroDTO.getNome());
        entity.setMae(cadastroDTO.getMae());
        entity.setEmail(cadastroDTO.getEmail());
        entity.setDataNascimento(cadastroDTO.getDataNascimento());
        entity.setRg(cadastroDTO.getRg());
        entity.setCpf(cadastroDTO.getCpf());
        entity.setSalario(Double.valueOf(cadastroDTO.getSalario()));

        return entity;

    }
}