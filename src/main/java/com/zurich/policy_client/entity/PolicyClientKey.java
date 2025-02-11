package com.zurich.policy_client.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Getter
@Setter
@Entity
public class PolicyClientKey implements Serializable{

    @Column(name = "numero_cuenta")
    Long client;

    
    @Column(name = "numero_poliza")
    String policy;
  
}
