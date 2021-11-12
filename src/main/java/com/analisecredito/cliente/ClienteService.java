package com.analisecredito.cliente;

import com.analisecredito.cliente.enums.EstadoCivil;
import com.analisecredito.exceptions.RecursoNaoEncontradoException;
import com.analisecredito.propostacredito.PropostaCreditoCadastroDTO;
import com.analisecredito.propostacredito.PropostaCreditoService;
import com.analisecredito.propostacredito.enums.StatusProposta;
import com.analisecredito.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository repository;
    private UsuarioService usuarioService;
    private PropostaCreditoService propostaCreditoService;

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService){this.usuarioService = usuarioService;}
    @Autowired
    public void setPropostaCreditoService(PropostaCreditoService propostaCreditoService){this.propostaCreditoService = propostaCreditoService;}

    public ClienteDTO insert(@NotNull ClienteCadastroDTO dto){
        var entity = ClienteConverter.toDto(dto);
        var usuarioEntity = usuarioService.findById(dto.getIdUsuario());
        entity.setEstadoCivil(Optional.ofNullable(dto.getEstadoCivil())
                .map(EstadoCivil::fromDescricao).orElse(null));

        entity.setUsuario(usuarioEntity);

        return Optional.ofNullable(entity)
                .map(repository::save)
                .map(cliente -> {
                    var propostaCred = PropostaCreditoCadastroDTO.builder()
                            .idCliente(cliente.getId())
                            .status(StatusProposta.SOLICTADO.getDescricao())
                            .dataInicioProposta(LocalDate.now())
                            .build();
                    propostaCreditoService.insert(propostaCred);

                    return cliente;
                })
                .map(ClienteConverter::toDto)
                .orElseThrow();


    }

    public ClienteDTO update(@NotNull Long id, @NotNull ClienteCadastroDTO dto){
        return Optional.ofNullable(dto)
                .map(cadastro -> this.prepareDadosPessoais(id, cadastro))
                .map(repository::save)
                .map(ClienteConverter::toDto)
                .orElseThrow();
    }

    public Cliente findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("[Cliente] id: " + id));
    }

    public void delete(Long id) {
        Optional.of(this.findById(id))
                .ifPresent(this.repository::delete);
    }

    private Cliente prepareDadosPessoais(Long id, ClienteCadastroDTO cadastroDTO){
        var entity = this.findById(id);

        entity.setNome(cadastroDTO.getNome());
        entity.setMae(cadastroDTO.getMae());
        entity.setEmail(cadastroDTO.getEmail());
        entity.setDataNascimento(cadastroDTO.getDataNascimento());
        entity.setRg(cadastroDTO.getRg());
        entity.setCpf(cadastroDTO.getCpf());
        entity.setSalario(Double.valueOf(cadastroDTO.getSalario()));
        entity.setEstadoCivil(Optional.ofNullable(cadastroDTO.getEstadoCivil())
                .map(EstadoCivil::fromDescricao)
                .orElse(null));

        return entity;

    }
}