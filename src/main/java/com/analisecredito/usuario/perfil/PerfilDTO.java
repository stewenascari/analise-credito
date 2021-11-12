package com.analisecredito.usuario.perfil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDTO {

    private Long id;

    @NotNull
    @Size(min = 3, max = 255)
    private String descricao;

}
