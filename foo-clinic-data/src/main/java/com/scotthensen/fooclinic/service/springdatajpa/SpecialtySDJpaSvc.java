package com.scotthensen.fooclinic.service.springdatajpa;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.scotthensen.fooclinic.model.Specialty;
import com.scotthensen.fooclinic.repositories.SpecialtyRepository;
import com.scotthensen.fooclinic.service.SpecialtySvc;

@Service
@Profile("springdatajpa")
public class SpecialtySDJpaSvc implements SpecialtySvc 
{
	private final SpecialtyRepository specialtyRepo;

	public SpecialtySDJpaSvc(SpecialtyRepository specialtyRepo) {
		this.specialtyRepo = specialtyRepo;
	}

	@Override
	public Set<Specialty> findAll() 
	{
		return specialtyRepo.findAll();
	}

	@Override
	public Specialty findById(Long id) 
	{
		return specialtyRepo.findById(id).orElse(null);
	}

	@Override
	public Specialty save(Specialty object) 
	{
		return specialtyRepo.save(object);
	}

	@Override
	public void delete(Specialty object) 
	{
		specialtyRepo.delete(object);
	}

	@Override
	public void deleteById(Long id) 
	{
		specialtyRepo.deleteById(id);
	}
	
}
