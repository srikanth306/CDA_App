package com.softgv.cda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softgv.cda.entity.User;
import com.softgv.cda.service.UserService;
import com.softgv.cda.util.AuthUser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController 
{

	@Autowired
	UserService service;

	@Operation(summary = "To Login The User", description = "This API Is Used To Login The User.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "All Users Found..."),
			@ApiResponse(responseCode = "400", description = "Unable To Get ALl Users...") })
	@PostMapping(value = "/login")
	public ResponseEntity<?> findByUsernameAndPassword(@RequestBody AuthUser authUser) {
		return service.findByUsernameAndPassword(authUser);
	}
	
	@Operation(summary = "To Create The User", description = "This API Will Accept The Request body of User Entity and Persists To The Database table")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "User Saved Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Save User...") })

	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		return service.saveUser(user);
	}
	
	
	@Operation(summary = "To Get User By Id", description = "This API Will Get The  User By Id From The Database.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "User Found..."),
			@ApiResponse(responseCode = "400", description = "Unable To Get Users By Id...") })
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findUserById(@PathVariable int id) {
		return service.findUserById(id);
	}
	
	@Operation(summary = "To Get The All Users", description = "This API Will Get The All Users From The Database.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "All Users Found..."),
			@ApiResponse(responseCode = "400", description = "Unable To Get ALl Users...") })

	@GetMapping
	public ResponseEntity<?> findAllUsers() {
		return service.findAllUsers();
	}

}
