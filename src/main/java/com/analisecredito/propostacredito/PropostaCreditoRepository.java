package com.analisecredito.propostacredito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaCreditoRepository extends JpaRepository<PropostaCredito, Long>, JpaSpecificationExecutor<PropostaCredito> {
}
