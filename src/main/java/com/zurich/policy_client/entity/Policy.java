package com.zurich.policy_client.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Policy implements Serializable{
    @Id
    @Column(name = "numero_poliza", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String numeroPoliza;

    
    @JoinColumn(name = "id_tipo_poliza") // Que columna en la tabla Policy tiene la FK
    @OneToOne(fetch = FetchType.LAZY)
    private PolicyType tipoPoliza;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private Double montoAsegurado;

    private Integer activa;

}
