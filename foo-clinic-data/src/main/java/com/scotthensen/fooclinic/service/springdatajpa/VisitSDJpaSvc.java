package com.scotthensen.fooclinic.service.springdatajpa;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.scotthensen.fooclinic.model.Visit;
import com.scotthensen.fooclinic.repositories.VisitRepository;
import com.scotthensen.fooclinic.service.VisitSvc;

@Service
@Profile("springdatajpa")
public class VisitSDJpaSvc implements VisitSvc
{
	private final VisitRepository visitRepo;

	public VisitSDJpaSvc(VisitRepository visitRepo) {
		this.visitRepo = visitRepo;
	}

	@Override
	public Set<Visit> findAll() 
	{
		return visitRepo.findAll();
	}

	@Override
	public Visit findById(Long id) 
	{
		return visitRepo.findById(id).orElse(null);
	}

	@Override
	public Visit save(Visit object) 
	{
		return visitRepo.save(object);
	}

	@Override
	public void delete(Visit object) 
	{
		visitRepo.delete(object);
	}

	@Override
	public void deleteById(Long id) 
	{
		visitRepo.deleteById(id);
	}
	
	
	
}
