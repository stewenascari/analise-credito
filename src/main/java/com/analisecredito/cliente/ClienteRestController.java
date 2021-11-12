package com.analisecredito.cliente;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RequestMapping("clientes")
@RestController
@PreAuthorize("hasAnyRole('SOLICITANTE')")
public class ClienteRestController {

    private final ClienteService service;

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(ClienteConverter.toDto(this.service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> insert(@RequestBody @Valid ClienteCadastroDTO cadastroDTO) {
        return ResponseEntity.ok().body(this.service.insert(cadastroDTO));
    }

    @PutMapping("{id:\\d+}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody @Valid ClienteCadastroDTO dto) {
        return ResponseEntity.ok().body(this.service.update(id, dto));
    }

    @DeleteMapping("{id:\\d+}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.ok().body(null);
    }
}