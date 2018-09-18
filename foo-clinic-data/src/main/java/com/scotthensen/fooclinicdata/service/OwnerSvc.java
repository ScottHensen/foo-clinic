package com.scotthensen.fooclinicdata.service;

import java.util.Set;

import com.scotthensen.fooclinicdata.model.Owner;

public interface OwnerSvc 
{
	Owner findByLastName(String lastName);

	Owner findById(Long id);
	
	Owner save(Owner owner);
	
	Set<Owner> findAll();
}
