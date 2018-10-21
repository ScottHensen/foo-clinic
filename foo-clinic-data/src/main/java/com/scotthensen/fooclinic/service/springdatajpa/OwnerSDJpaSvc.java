package com.scotthensen.fooclinic.service.springdatajpa;

import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.scotthensen.fooclinic.model.Owner;
import com.scotthensen.fooclinic.model.OwnerPetsDTO;
import com.scotthensen.fooclinic.repositories.OwnerRepository;
import com.scotthensen.fooclinic.service.OwnerSvc;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaSvc implements OwnerSvc
{
	public OwnerSDJpaSvc(OwnerRepository ownerRepo) {
		this.ownerRepo = ownerRepo;
	}

	private final OwnerRepository   ownerRepo;
	
	@Override
	public Set<Owner> findAll() 
	{
		return ownerRepo.findAll();
	}

	@Override
	public Owner findById(Long id) 
	{
		return ownerRepo.findById(id).orElse(null);
	}

	@Override
	public Owner findByLastName(String lastName) 
	{
		return ownerRepo.findByLastName(lastName);
	}

	@Override
	public List<Owner> findAllByLastNameLike(String lastName)
	{
		return ownerRepo.findAllByLastNameLike(lastName);
	}

	@Override
	public List<OwnerPetsDTO> findOwnerPetsByLastName(String lastName) 
	{
		return ownerRepo.findOwnerPetsByLastName(lastName);
	}

	@Override
	public Owner save(Owner object) 
	{
		return ownerRepo.save(object);
	}

	@Override
	public void delete(Owner object) 
	{
		ownerRepo.delete(object);
	}

	@Override
	public void deleteById(Long id) 
	{
		ownerRepo.deleteById(id);
		
	}

}
