package com.scotthensen.fooclinic.service.springdatajpa;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.scotthensen.fooclinic.model.Vet;
import com.scotthensen.fooclinic.repositories.VetRepository;
import com.scotthensen.fooclinic.service.VetSvc;

@Service
@Profile("springdatajpa")
public class VetSDJpaSvc implements VetSvc
{
	private final VetRepository vetRepo;
	
	public VetSDJpaSvc(VetRepository vetRepo) {
		this.vetRepo = vetRepo;
	}

	@Override
	public Set<Vet> findAll() 
	{
		return vetRepo.findAll();
	}

	@Override
	public Vet findById(Long id) 
	{
		return vetRepo.findById(id).orElse(null);
	}

	@Override
	public Vet save(Vet object) 
	{
		return save(object);
	}

	@Override
	public void delete(Vet object) 
	{
		vetRepo.delete(object);
	}

	@Override
	public void deleteById(Long id) 
	{
		vetRepo.deleteById(id);
	}

}
