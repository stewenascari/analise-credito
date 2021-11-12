package com.analisecredito.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RequestMapping("usuarios")
@RestController
public class UsuarioRestController {

    private final UsuarioService service;

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(UsuarioConverter.toDto(this.service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> insert(@RequestBody @Valid UsuarioCadastroDTO cadastroDTO) {
        return ResponseEntity.ok().body(this.service.insert(cadastroDTO));
    }

    @PutMapping("{id:\\d+}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody @Valid UsuarioCadastroDTO dto) {
        return ResponseEntity.ok().body(this.service.update(id, dto));
    }
}
