package com.project.unidacproject.controller;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.unidacproject.model.UserUnidac;
import com.project.unidacproject.service.UserUnidacService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/userunidac", produces = MediaType.APPLICATION_JSON_VALUE )
public class UserUnidacController {
	
	private UserUnidacService userUnidacService;
	
	
	public UserUnidacController(UserUnidacService userUnidacService) {
		super();
		this.userUnidacService = userUnidacService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<UserUnidac>> findAll(@RequestParam(name = "name", required = false) String name) {
		List<UserUnidac> userUnidacs = userUnidacService.findAllUserUnidac(name);
		return new ResponseEntity<List<UserUnidac>>(userUnidacs, HttpStatus.OK);
	}

	@ApiOperation(value = "Get UserUnidac by id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UserUnidac> findById(@PathVariable(value = "id") Long id) {
		UserUnidac userUnidac = userUnidacService.findUserUnidacById(id);
		if (userUnidac == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(userUnidac, HttpStatus.OK);

		}
	}

	@ApiOperation(value = "Create User")
	@ApiResponses({ @ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad Request") })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UserUnidac> createUserUnidac(@RequestBody UserUnidac userUnidac) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		userUnidac = userUnidacService.saveUserUnidac(userUnidac);

		if (userUnidac == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(userUnidac, HttpStatus.CREATED);
		}
	}

	@ApiOperation(value = "Update User")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UserUnidac> updateUserUnidac(@PathVariable(value = "id") Long id, @RequestBody UserUnidac userUnidac) {
		userUnidac.setId(id);
		userUnidac = userUnidacService.updateUserUnidac(userUnidac);

		if (userUnidac == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}

	@ApiOperation(value = "Delete User")
	@ApiResponses({ @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request") })
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteByIdUserUnidac(@PathVariable(value = "id") Long id) {
		userUnidacService.deleteUserUnidacById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Delete all User")
	@ApiResponses({ @ApiResponse(code = 204, message = "No Content") })
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAllUserUnidac() {
		userUnidacService.deleteAllUserUnidac();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
