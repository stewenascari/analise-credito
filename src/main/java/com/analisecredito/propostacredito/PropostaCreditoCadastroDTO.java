package com.analisecredito.propostacredito;

import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PropostaCreditoCadastroDTO {
    private String status;
    private LocalDate dataInicioProposta;
    private Long idCliente;
}
