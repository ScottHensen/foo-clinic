package com.scotthensen.fooclinicdata.service;

import java.util.Set;

import com.scotthensen.fooclinicdata.model.Pet;

public interface PetSvc 
{
	Pet findById(Long id);

	Pet save(Pet pet);
	
	Set<Pet> findAll();
}
