package com.scotthensen.fooclinicdata.service;

import java.util.Set;

import com.scotthensen.fooclinicdata.model.Vet;

public interface VetSvc 
{
	Vet findById(Long id);
	
	Vet save(Vet vet);
	
	Set<Vet> findAll();
}
