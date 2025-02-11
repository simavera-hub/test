package com.zurich.policy_client.service;

import java.util.List;

import com.zurich.policy_client.entity.Policy;

public interface PolicyService {

void save(Policy policy);

Policy findByNumeroPoliza(String numeroPoliza);

List<Policy> findByDinamic(Policy policy);

String validate(Policy policy);
}

