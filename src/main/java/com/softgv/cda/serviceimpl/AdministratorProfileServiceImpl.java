package com.softgv.cda.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.softgv.cda.dao.AdministratorProfileDao;
import com.softgv.cda.entity.AdministratorProfile;
import com.softgv.cda.responsestructure.ResponseStructure;
import com.softgv.cda.service.AdministratorProfileService;
import com.softgv.cda.util.Role;
import com.softgv.cda.util.UserStatus;
@Service
public class AdministratorProfileServiceImpl implements AdministratorProfileService{

	@Autowired
	private AdministratorProfileDao administratorProfileDao;
	
	
	@Override
	public ResponseEntity<?> saveAdministratorProfile(AdministratorProfile administratorProfile) 
	{
		administratorProfile=administratorProfileDao.saveAdministratorProfile(administratorProfile);
		administratorProfile.getUser().setRole(Role.ADMINISTRATOR);
		administratorProfile.getUser().setStatus(UserStatus.ACTIVE);
		administratorProfile=administratorProfileDao.saveAdministratorProfile(administratorProfile);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Administrator Saved Successfully...").body(administratorProfile).build());
	}

	@Override
	public ResponseEntity<?> findAdministratorProfileById(int id) {
		Optional<AdministratorProfile> optinal = administratorProfileDao.findAdministratorProfileById(id);
//		if(optinal.isEmpty())
//			throw Exception();
		AdministratorProfile administratorProfile = optinal.get();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Administrator Found Successfully...").body(administratorProfile).build());
	}

	@Override
	public ResponseEntity<?> findAllAdministrators() 
	{
		List<AdministratorProfile> list=administratorProfileDao.findAllAdministratorProfile();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Administrator Found Successfully...").body(list).build());
	}

	@Override
	public ResponseEntity<?> findAllAdministratorById(int id)
	{
		Optional<AdministratorProfile> optional=administratorProfileDao.findAdministratorProfileById(id);
		if(optional.isEmpty())
			throw new RuntimeException();
		AdministratorProfile profile=optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Administrator Found Successfully...").body(profile).build());
	}

}
