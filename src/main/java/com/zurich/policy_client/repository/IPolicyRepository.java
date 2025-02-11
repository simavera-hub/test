package com.zurich.policy_client.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.zurich.policy_client.entity.Policy;


public interface IPolicyRepository extends JpaRepository<Policy, String>{

   
    Optional<Policy> findByNumeroPoliza(@Param("numeroPoliza") String numeroPoliza); 

 //   void deleteByNumeroCuenta(@Param("numeroCuenta") Long numeroCuenta);

}
