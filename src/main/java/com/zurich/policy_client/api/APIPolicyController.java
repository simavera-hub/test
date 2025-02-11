package com.zurich.policy_client.api;

import jakarta.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zurich.policy_client.entity.Client;
import com.zurich.policy_client.entity.Policy;
import com.zurich.policy_client.entity.PolicyClient;
import com.zurich.policy_client.entity.PolicyClientKey;
import com.zurich.policy_client.service.ClientService;
import com.zurich.policy_client.service.PolicyClientService;
import com.zurich.policy_client.service.PolicyService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/policy") 
public class APIPolicyController {


	@Autowired
	private ClientService clientService;
	@Autowired
	private PolicyService policyService;
	@Autowired
	private PolicyClientService policyClientService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(value = "/allByClient")
	/**
	 * Obtiene las pólizas del cliente
	 * @param numeroCuenta
	 * @return
	 * @throws ValidationException
	 */
	public ResponseEntity getAllByClient(@RequestHeader("numeroCuenta") Long numeroCuenta) throws ValidationException {
 		
		try {
			return new ResponseEntity(policyClientService.findByNumeroCuenta(numeroCuenta), HttpStatus.OK);
		} catch (ValidationException ex) {
			log.error("Ocurrió un error", ex.getMessage());
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/allByFilter")
	public ResponseEntity allByFilter(@RequestHeader("policy") Policy policy) throws ValidationException {
 		
		try {
			return new ResponseEntity(policyService.findByDinamic(policy), HttpStatus.OK);
		} catch (ValidationException ex) {
			log.error("Ocurrió un error", ex.getMessage());
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping(value = "/cancel")
	/**
	 * Cancela una póliza activa
	 * @param client
	 * @return
	 * @throws ValidationException
	 */
	public ResponseEntity cancel(@RequestHeader("numeroPoliza") String numeroPoliza) throws ValidationException {
 		
		try {
			Policy originalPolicy = policyService.findByNumeroPoliza(numeroPoliza);
			
			originalPolicy.setActiva(0);
			policyService.save(originalPolicy);
			return new ResponseEntity( "La póliza ha sido cancelada exitosamente", HttpStatus.OK);
		} catch (ValidationException ex) {
			log.error("Ocurrió un error", ex.getMessage());
			return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping(value = "/associateToClient")
	/**
	 * Si el cliente trae numero de cuenta, se valida que exista, en caso de que sí, se guarda la póliza y se asocia al cliente
	 * @param client
	 * @return
	 * @throws ValidationException
	 */
	public ResponseEntity associateToClient(@RequestHeader("poliza") Policy poliza, @RequestHeader("numeroCuenta") Long numeroCuenta) throws ValidationException {
 		
		try {
			Client client = clientService.findByNumeroCuenta(numeroCuenta);
			
			if(client== null)
				return new ResponseEntity( "El cliente aún no se encuentra registrado", HttpStatus.NOT_FOUND);
		
			String error = policyService.validate(poliza);

			if(error!=null){
				return new ResponseEntity( error, HttpStatus.NOT_ACCEPTABLE);
			}

			policyService.save(poliza);

			PolicyClientKey key = new PolicyClientKey(numeroCuenta, poliza.getNumeroPoliza());	
			PolicyClient policyClient = new PolicyClient();
			policyClient.setId(key);
			policyClientService.save(policyClient);

			return new ResponseEntity( "La póliza se ha registrado y asociado exitosamente", HttpStatus.OK);
		} catch (ValidationException ex) {
			log.error("Ocurrió un error", ex.getMessage());
			return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
