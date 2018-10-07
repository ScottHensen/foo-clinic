package com.scotthensen.fooclinic.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scotthensen.fooclinic.model.Pet;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long>
{
	Set<Pet> findAll();
}
