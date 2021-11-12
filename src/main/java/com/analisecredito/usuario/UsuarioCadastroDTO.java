package com.analisecredito.usuario;

import lombok.*;

@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCadastroDTO {

    private String email;
    private String senha;
    private Long perfilId;
}
