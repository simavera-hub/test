package com.zurich.policy_client.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.zurich.policy_client.entity.Client;



public interface IClientRepository extends JpaRepository<Client, Long>{
   
    Optional<Client> findByNumeroCuenta(@Param("numeroCuenta") Long numeroCuenta); 

    void deleteByNumeroCuenta(@Param("numeroCuenta") Long numeroCuenta);

}
