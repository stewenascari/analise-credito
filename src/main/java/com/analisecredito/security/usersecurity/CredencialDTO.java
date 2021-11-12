package com.analisecredito.security.usersecurity;

import lombok.*;

@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CredencialDTO {

    private String email;
    private String senha;
}
