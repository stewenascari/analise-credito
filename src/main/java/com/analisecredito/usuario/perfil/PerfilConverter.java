package com.analisecredito.usuario.perfil;

public class PerfilConverter {
    private PerfilConverter() {
        super();
    }

    public static Perfil fromDto(PerfilDTO dto) {
        var entity = new Perfil();
        entity.setId(dto.getId());
        entity.setDescricao(dto.getDescricao());
        return entity;
    }

    public static PerfilDTO toDto(Perfil entity) {
        return new PerfilDTO(entity.getId(), entity.getDescricao());
    }
}
