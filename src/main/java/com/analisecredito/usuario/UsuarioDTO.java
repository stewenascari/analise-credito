package com.analisecredito.usuario;

import lombok.*;

@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String email;
    private String senha;
    private String perfil;
}
