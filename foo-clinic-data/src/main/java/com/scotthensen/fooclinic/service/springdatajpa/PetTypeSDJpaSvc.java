package com.scotthensen.fooclinic.service.springdatajpa;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.scotthensen.fooclinic.model.PetType;
import com.scotthensen.fooclinic.repositories.PetTypeRepository;
import com.scotthensen.fooclinic.service.PetTypeSvc;

@Service
@Profile("springdatajpa")
public class PetTypeSDJpaSvc implements PetTypeSvc
{
	private final PetTypeRepository petTypeRepo;

	public PetTypeSDJpaSvc(PetTypeRepository petTypeRepo) {
		this.petTypeRepo = petTypeRepo;
	}

	@Override
	public Set<PetType> findAll() 
	{
		return petTypeRepo.findAll();
	}

	@Override
	public PetType findById(Long id) 
	{
		return petTypeRepo.findById(id).orElse(null);
	}

	@Override
	public PetType save(PetType object) 
	{
		return petTypeRepo.save(object);
	}

	@Override
	public void delete(PetType object) 
	{
		petTypeRepo.delete(object);
	}

	@Override
	public void deleteById(Long id) 
	{
		petTypeRepo.deleteById(id);
	}
	
	
	
}
