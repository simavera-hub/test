package com.zurich.policy_client.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zurich.policy_client.entity.Policy;
import com.zurich.policy_client.repository.IPolicyRepository;
import com.zurich.policy_client.service.PolicyService;
import com.zurich.policy_client.util.Utilities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PolicyServiceImpl implements PolicyService{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IPolicyRepository policyRepository;

    @Override
    public Policy findByNumeroPoliza(String numeroPoliza) {

        Optional<Policy> poliza = policyRepository.findByNumeroPoliza(numeroPoliza); 

        if(poliza.isEmpty())
            return new Policy();
        return poliza.get();
    }

    @Override
    public void save(Policy policy) {
        policyRepository.save(policy);
    }

    @Override
    public String validate(Policy policy){

        String error = "";

        if(policy == null)
            return "número de póliza no válido";

        if(Utilities.isNull(policy.getTipoPoliza())){
            error += "El tipo de la póliza no debe ser vacío.\n"; 
        }
        if(!Utilities.isAfterDate(policy.getFechaInicio(),policy.getFechaFin())){
            error += "La fecha de expiración de la vigencia no debe ser anterior a la fecha de inicio de vigencia.\n";
        }
        if(Utilities.isPositiveNumber(policy.getMontoAsegurado())){
            error += "El monto asegurado debe ser un valor positivo.\n"; 
        }
        return error;
    }

    @Override
    public List<Policy> findByDinamic(Policy policy) { 
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Policy> query = cb.createQuery(Policy.class);
        Root<Policy> root = query.from(Policy.class);

        List<Predicate> predicates = new ArrayList<>();

        if (policy.getTipoPoliza() != null) {
            predicates.add(cb.equal(root.get("tipoPoliza"), policy.getTipoPoliza()));
        }

        if (policy.getActiva() != null) {
            predicates.add(cb.equal(root.get("activa"), policy.getActiva()));
        }

        if (policy.getFechaInicio() != null && policy.getFechaFin() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("fechaInicio"), policy.getFechaInicio()));
            predicates.add(cb.lessThanOrEqualTo(root.get("fechaFin"), policy.getFechaFin()));
        }

        query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));
        return entityManager.createQuery(query).getResultList();
    }
}
