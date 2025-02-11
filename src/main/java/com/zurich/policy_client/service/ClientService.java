package com.zurich.policy_client.service;

import java.util.List;

import com.zurich.policy_client.entity.Client;

public interface ClientService {

List<Client> findAll();

void save(Client client);

Client findByNumeroCuenta(Long numeroCuenta);

List<Client> findByDinamic(Client client);

void delete(Long numeroCuenta);

Client fill(Client originalClient, Client client);

String validate(Client client);
}

