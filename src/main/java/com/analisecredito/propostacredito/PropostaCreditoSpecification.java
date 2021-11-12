package com.analisecredito.propostacredito;

import com.analisecredito.propostacredito.enums.StatusProposta;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.Optional;

public class PropostaCreditoSpecification implements Specification<PropostaCredito> {
    @Override
    public Predicate toPredicate(Root<PropostaCredito> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.isNotNull(root.get("id"));
    }

    public Specification<PropostaCredito> findEqualsByCodigo(@Nullable String codigo) {
        return Optional.ofNullable(codigo)
                .map(c -> prepareEqualsSpecification("codigo", c))
                .orElse(null);
    }

    public Specification<PropostaCredito> findLikeByNome(@Nullable String nome) {
        return Optional.ofNullable(nome)
                .map(s -> prepareLikeSpecification("nome", s))
                .orElse(null);
    }

    public Specification<PropostaCredito> findByClienteId(@Nullable Long idCliente) {
        return Optional.ofNullable(idCliente).map(id -> prepareLikeSubSpecification("cliente", "id", String.valueOf(id)))
                .orElse(null);
    }

    public Specification<PropostaCredito> findByStatus(@Nullable StatusProposta statusProposta) {
        return Optional.ofNullable(statusProposta)
                .map(tc -> prepareEqualsSpecification("status", tc))
                .orElse(null);
    }

    public Specification<PropostaCredito> findByDataInicioProposta(@Nullable LocalDate dataInicioProposta) {
        return Optional.ofNullable(dataInicioProposta)
                .map(s -> (Specification<PropostaCredito>) (root, query, builder) -> builder
                        .equal(root.get("dataInicioProposta"), s))
                .orElse(null);

    }

    @NotNull
    protected Specification<PropostaCredito> prepareEqualsSpecification(@NotNull String columnName, @NotNull Object value) {
        return (root, query, builder) -> builder.equal(root.get(columnName), value);
    }

    @NotNull
    protected Specification<PropostaCredito> prepareLikeSpecification(@NotNull String columnName, @NotNull String value) {
        return (root, query, builder) -> builder.like(builder.lower(root.get(columnName)),
                "%" + value.toLowerCase() + "%");
    }

    @NotNull
    protected Specification<PropostaCredito> prepareLikeSubSpecification(@NotNull String columnName, @NotNull String campoName,
                                                           @NotNull String value) {
        return (root, query, builder) -> builder.like(builder.lower(root.join(columnName)
                .get(campoName)), "%" + value.toLowerCase() + "%");
    }
}
