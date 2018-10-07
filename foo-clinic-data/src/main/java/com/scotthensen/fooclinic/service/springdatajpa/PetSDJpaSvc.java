package com.scotthensen.fooclinic.service.springdatajpa;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.scotthensen.fooclinic.model.Pet;
import com.scotthensen.fooclinic.repositories.PetRepository;
import com.scotthensen.fooclinic.service.PetSvc;

@Service
@Profile("springdatajpa")
public class PetSDJpaSvc implements PetSvc
{
	private final PetRepository petRepo;

	public PetSDJpaSvc(PetRepository petRepo) {
		this.petRepo = petRepo;
	}

	
	@Override
	public Set<Pet> findAll() 
	{
		return petRepo.findAll();
	}

	@Override
	public Pet findById(Long id) 
	{
		//TODO:  this is stupid.  following the tut, but *why* use optional and then 
		//       put the NPE risk back in?  Better to RETURN the optional to the caller.
		return petRepo.findById(id).orElse(null);
	}

	@Override
	public Pet save(Pet object) 
	{
		return petRepo.save(object);
	}

	@Override
	public void delete(Pet object) 
	{
		petRepo.delete(object);
	}

	@Override
	public void deleteById(Long id) 
	{
		petRepo.deleteById(id);
	}
	
	
}
