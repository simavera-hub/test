package com.zurich.policy_client.entity;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class PolicyClient implements Serializable{
    @EmbeddedId
    PolicyClientKey id;

    @ManyToOne
    @MapsId("numeroCuenta")
    @JoinColumn(name = "numero_cuenta")
    Client client;

    @ManyToOne
    @MapsId("numeroPoliza")
    @JoinColumn(name = "numero_poliza")
    Policy policy;

    Instant created;
    Instant updated;
  
}
