package com.zurich.policy_client.entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Client implements Serializable{
    @Id
    @Column(name = "numero_cuenta", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroCuenta;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String nombres;

    private String correo;

    private Integer telefono;

    private String calle;

    private String numeroExterior;

    private String colonia;

    private String municipio;

    private String estado;

    @OneToMany(mappedBy = "client")
    Set<PolicyClient> policiesClient;

}
