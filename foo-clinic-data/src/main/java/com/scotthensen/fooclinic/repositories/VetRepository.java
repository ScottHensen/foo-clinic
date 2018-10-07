package com.scotthensen.fooclinic.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scotthensen.fooclinic.model.Vet;

@Repository
public interface VetRepository extends CrudRepository<Vet, Long> 
{
	Set<Vet> findAll();
}
