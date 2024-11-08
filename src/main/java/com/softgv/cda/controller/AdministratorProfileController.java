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

import com.softgv.cda.entity.AdministratorProfile;
import com.softgv.cda.service.AdministratorProfileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(value = "/administratorprofiles")
public class AdministratorProfileController {

	@Autowired
	private AdministratorProfileService administratorProfileService;

	
	@Operation(summary = "To Create The AdministratorProfile ", description = "This API Will Accept The Request body of AdministratorProfile Entity and Persists Into The Database table")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "AdministratorProfile Saved Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Save AdministratorProfile...") })

	@PostMapping
	public ResponseEntity<?> saveAdministratorProfile(@RequestBody AdministratorProfile administratorProfile) {
		return administratorProfileService.saveAdministratorProfile(administratorProfile);
	}
	
	@Operation(summary = "To Get The All AdministratorProfiles ", description = "This API Will Get The All AdministratorProfiles Entities From The Database table")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "AdministratorProfiles Found Sucessfully..."),
			@ApiResponse(responseCode = "400", description = "Unable To Found AdministratorProfiles...") })

	@GetMapping
	public ResponseEntity<?> findAllAdministrators() {
		return administratorProfileService.findAllAdministrators();
	}
	
	@Operation(summary = "To Get AdministratorProfile By Id", description = "This API Will Get The  AdministratorProfile By Id From The Database.")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "AdministratorProfile Found..."),
			@ApiResponse(responseCode = "400", description = "Unable To Get AdministratorProfile By Id...") })
	@GetMapping("/{id}")
	public ResponseEntity<?> findAllAdministratorById(@PathVariable int id) {
		return administratorProfileService.findAllAdministratorById(id);
	}
	
	

}
