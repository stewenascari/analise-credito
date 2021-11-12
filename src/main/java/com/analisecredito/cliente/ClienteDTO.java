package com.analisecredito.cliente;
import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private Long id;
    private String nome;
    private String mae;
    private String email;
    private LocalDate dataNascimento;
    private String estadoCivil;
    private String rg;
    private String cpf;
    private Double salario;
}