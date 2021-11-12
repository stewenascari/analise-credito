package com.analisecredito.anexodocs;

import com.analisecredito.cliente.Cliente;

import java.util.Optional;

public class AnexoDocsConverter {

    private AnexoDocsConverter() {
        super();
    }

    public static AnexoDocsDTO toDto(AnexoDocs entity) {
        return AnexoDocsDTO.builder()
                .id(entity.getId())
                .pathFilesystem(entity.getPathArquivo())
                .nomeDoc(entity.getNomeDoc())
                .idCliente(Optional.ofNullable(entity.getCliente())
                        .map(Cliente::getId)
                        .orElse(null))
                .build();
    }

    public static AnexoDocs fromDto(AnexoDocsCadastroDTO dto) {
        var entity = new AnexoDocs();
        entity.setNomeDoc(dto.getNomeDoc());
        return entity;
    }
}
