package com.zurich.policy_client.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zurich.policy_client.entity.Client;
import com.zurich.policy_client.repository.IClientRepository;
import com.zurich.policy_client.service.ClientService;
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
public class ClientServiceImpl implements ClientService{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IClientRepository clientRepository;
    
    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findByNumeroCuenta(Long numeroCuenta) {

        Optional<Client> client = clientRepository.findByNumeroCuenta(numeroCuenta); 

        if(client.isEmpty())
            return new Client();
        return client.get();
    }

    @Override
    public List<Client> findByDinamic(Client client) { 
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> query = cb.createQuery(Client.class);
        Root<Client> root = query.from(Client.class);

        List<Predicate> predicates = new ArrayList<>();

        if (client.getApellidoPaterno() != null) {
            predicates.add(cb.like(root.get("apellidoPaterno"), client.getApellidoPaterno()));
        }

        if (client.getApellidoMaterno() != null) {
            predicates.add(cb.like(root.get("apellidoMaterno"), client.getApellidoMaterno()));
        }

        if (client.getNombres() != null) {
            predicates.add(cb.like(root.get("nombres"), client.getNombres()));
        }

        if (client.getCorreo() != null) {
            predicates.add(cb.like(root.get("correo"), client.getCorreo()));
        }

        if (client.getNumeroCuenta() != null) {
            predicates.add(cb.equal(root.get("numeroCuenta"), client.getNumeroCuenta()));
        }

        query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void delete(Long numeroCuenta) {
        clientRepository.deleteByNumeroCuenta(numeroCuenta);
    }

    @Override
    public Client fill(Client originalClient, Client client){
        originalClient.setApellidoPaterno(client.getApellidoPaterno());
        originalClient.setApellidoMaterno(client.getApellidoMaterno());
        originalClient.setNombres(client.getNombres());
        originalClient.setCorreo(client.getCorreo());
        originalClient.setTelefono(client.getTelefono());
        return originalClient;
    }

    @Override
    public String validate(Client client){

        String error = "";

        if(client == null)
            return "cliente no válido";

        if(Utilities.isNull(client.getNumeroCuenta())){
            error += "El número de Cuenta no debe ser vacío.\n"; 
        }
        if(!Utilities.isValidSize(client.getNumeroCuenta(),10, false)){
            error += "El número de Cuenta debe ser máximo de 10 dígitos.\n";
        }
        if(Utilities.isNull(client.getApellidoPaterno())){
            error += "Debe registrar el apellido paterno.\n"; 
        }
        if(Utilities.isNull(client.getApellidoMaterno())){
            error += "Debe registrar el apellido materno.\n"; 
        }
        if(Utilities.isNull(client.getNombres())){
            error += "Debe registrar al menos un nombre.\n"; 
        }
        if(!Utilities.isValidAlphabetic(client.getApellidoPaterno()) || !Utilities.isValidAlphabetic(client.getApellidoMaterno()) || !Utilities.isValidAlphabetic(client.getNombres())){
            error += "El nombre sólo puede contener letras y espacios.\n";
        }
        if(Utilities.isNull(client.getCorreo())){
            error += "Debe registrar un correo electrónico.\n"; 
        }
        if(!Utilities.isValidEmail(client.getCorreo())){
            error += "El correo no cuenta con el formato correcto.\n";
        }
        if(Utilities.isNull(client.getTelefono())){
            error += "Debe registrar un teléfono.\n"; 
        }
        if(!Utilities.isValidSize(new Long(client.getTelefono()),10, true)){
            error += "El teléfono debe ser de 10 dígitos.\n";
        }
        return error;
    }

}
