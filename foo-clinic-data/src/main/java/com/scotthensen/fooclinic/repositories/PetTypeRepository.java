package com.scotthensen.fooclinic.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scotthensen.fooclinic.model.PetType;

@Repository
public interface PetTypeRepository extends CrudRepository<PetType, Long> 
{
	Set<PetType> findAll();
}
