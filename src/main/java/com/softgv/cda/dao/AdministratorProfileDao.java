package com.softgv.cda.dao;

import java.util.List;
import java.util.Optional;

import com.softgv.cda.entity.AdministratorProfile;
import com.softgv.cda.entity.StudentProfile;

public interface AdministratorProfileDao {
	
	
	AdministratorProfile saveAdministratorProfile(AdministratorProfile administratorProfile);
	
	Optional<AdministratorProfile> findAdministratorProfileById(int id);
	
	List<AdministratorProfile> findAllAdministratorProfile();
	
}
