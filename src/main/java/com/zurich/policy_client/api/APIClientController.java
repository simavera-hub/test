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
import com.zurich.policy_client.service.ClientService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/client") 
public class APIClientController {


	@Autowired
	private ClientService clientService;
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(value = "/all")
	public ResponseEntity getAll() throws ValidationException {
 		
		try {
			return new ResponseEntity(clientService.findAll(), HttpStatus.OK);
		} catch (ValidationException ex) {
			log.error("Ocurrió un error", ex.getMessage());
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/allByFilter")
	public ResponseEntity allByFilter(@RequestHeader("client") Client client) throws ValidationException {
 		
		try {
			return new ResponseEntity(clientService.findByDinamic(client), HttpStatus.OK);
		} catch (ValidationException ex) {
			log.error("Ocurrió un error", ex.getMessage());
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = "/save")
	/**
	 * Si el cliente no tiene numero de cuenta se guarda como nuevo
	 * @param client
	 * @return
	 * @throws ValidationException
	 */
	public ResponseEntity save(@RequestHeader("client") Client client) throws ValidationException {
 		
		try {
			clientService.save(client);
			return new ResponseEntity( "El cliente ha sido guardado exitosamente", HttpStatus.OK);
		} catch (ValidationException ex) {
			log.error("Ocurrió un error", ex.getMessage());
			return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping(value = "/update")
	/**
	 * Si el cliente trae numero de cuenta, se valida que exista, en caso de que sí, se modifican los datos actualizados
	 * @param client
	 * @return
	 * @throws ValidationException
	 */
	public ResponseEntity update(@RequestHeader("client") Client client) throws ValidationException {
 		
		try {
			Client originalClient = clientService.findByNumeroCuenta(client.getNumeroCuenta());
			String error = clientService.validate(client);

			if(error!=null){
				return new ResponseEntity( error, HttpStatus.NOT_ACCEPTABLE);
			}
			originalClient = clientService.fill(originalClient,client);
		
			clientService.save(originalClient);
			return new ResponseEntity( "El cliente ha sido modificado exitosamente", HttpStatus.OK);
		} catch (ValidationException ex) {
			log.error("Ocurrió un error", ex.getMessage());
			return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping(value = "/delete")
	/**
	 * Elimina el cliente por numero de cuenta
	 * @param client
	 * @return
	 * @throws ValidationException
	 */
	public ResponseEntity delete(@RequestHeader("numeroCuenta") Long numeroCuenta) throws ValidationException {
 		
		try {
			clientService.delete(numeroCuenta);
			return new ResponseEntity( "El cliente ha sido eliminado exitosamente", HttpStatus.OK);
		} catch (ValidationException ex) {
			log.error("Ocurrió un error", ex.getMessage());
			return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
