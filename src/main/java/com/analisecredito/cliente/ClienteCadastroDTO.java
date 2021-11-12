package com.analisecredito.cliente;
import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import java.time.LocalDate;

@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCadastroDTO {

    @NotNull
    @Size(min = 3, max = 255, message = "O campo [nome: '${validatedValue}'] deve ter entre {min} e {max} caracteres de tamanho")
    private String nome;
    @NotNull
    @Size(min = 3, max = 255, message = "O campo [nome: '${validatedValue}'] deve ter entre {min} e {max} caracteres de tamanho")
    private String mae;
    @NotNull
    @Email
    private String email;
    @NotNull
    private LocalDate dataNascimento;
    @NotNull
    private String estadoCivil;
    @NotNull
    private String rg;
    @NotNull
    private String cpf;
    @NotNull
    private String salario;
    @NotNull
    private Long idUsuario;
}