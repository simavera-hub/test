package com.zurich.policy_client.service;

import org.springframework.data.jpa.repository.Query;

import com.zurich.policy_client.api.DTO.PoliciesClientDTO;
import com.zurich.policy_client.entity.PolicyClient;

public interface PolicyClientService {

void save(PolicyClient policyClient);


@Query(" SELECT new com.zurich.policy_client.api.dto.PoliciesClientDTO(  " +
                        "            p.numeroPoliza as numeroPoliza, p.activa AS activa, c.numeroCuenta as numeroCuenta ) " +
                        "       FROM Client c" +
                        " INNER JOIN PolicyClient pc ON c.numeroCuenta = pc.numeroCuenta " +
                        " INNER JOIN Policy p ON pc.numeroPoliza = p.numeroPoliza" +
                        " WHERE  c.numeroCuenta = :numeroCuenta  ")
PoliciesClientDTO findByNumeroCuenta(Long numeroCuenta);
}

