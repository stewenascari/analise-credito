package com.analisecredito.propostacredito;

import com.analisecredito.cliente.ClienteService;
import com.analisecredito.exceptions.RecursoNaoEncontradoException;
import com.analisecredito.propostacredito.enums.StatusProposta;
import com.sun.istack.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PropostaCreditoService {

    private final PropostaCreditoRepository repository;
    private ClienteService clienteService;

    @Autowired
    public void setClienteService(ClienteService clienteService){this.clienteService = clienteService;}

    public PropostaCreditoDTO insert(@NotNull PropostaCreditoCadastroDTO dto){
        var entity = PropostaCreditoConverter.toDto(dto);
        var clienteEntity = clienteService.findById(dto.getIdCliente());

        entity.setCliente(clienteEntity);

        return Optional.ofNullable(entity)
                .map(repository::save)
                .map(PropostaCreditoConverter::toDto)
                .orElseThrow();
    }

    public PropostaCreditoDTO update(@NotNull Long id, @NotNull PropostaCreditoCadastroDTO dto){
        return Optional.ofNullable(dto)
                .map(cadastro -> this.preparePropostaCred(id, cadastro))
                .map(repository::save)
                .map(PropostaCreditoConverter::toDto)
                .orElseThrow();
    }

    public PropostaCredito findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("[Proposta Credito] id: " + id));
    }

    public void delete(Long id) {
        Optional.of(this.findById(id))
                .ifPresent(this.repository::delete);
    }

    List<PropostaCreditoDTO> filter(@Nullable String statusProposta, @Nullable LocalDate dataInicioProposta, @Nullable Pageable pageable) {
        var specification = this.prepareSpecification(statusProposta, dataInicioProposta);
        return repository.findAll(specification, this.preparePageable(pageable)).stream()
                .map(PropostaCreditoConverter::toDto)
                .collect(Collectors.toList());
    }

    private PropostaCredito preparePropostaCred(Long id, PropostaCreditoCadastroDTO cadastroDTO){
        var entity = this.findById(id);
        var cliente = clienteService.findById(cadastroDTO.getIdCliente());
        entity.setStatus(Optional.ofNullable(cadastroDTO.getStatus())
                .map(StatusProposta::fromDescricao)
                .orElse(null));
        entity.setCliente(cliente);

        return entity;

    }

    @NotNull
    private Specification<PropostaCredito> prepareSpecification(@Nullable String statusProposta, @Nullable LocalDate dataInicioProposta) {
        var specification = new PropostaCreditoSpecification();
        var status = (Optional.ofNullable(statusProposta)
                .map(tc -> StatusProposta.fromDescricao(tc))
                .orElse(null));

        return specification.and(specification.findByStatus(status)
                .and(specification.findByDataInicioProposta(dataInicioProposta)));
    }

    private Pageable preparePageable(@Nullable Pageable pageable) {
        return Optional.ofNullable(pageable)
                .orElse(Pageable.unpaged());
    }
}
