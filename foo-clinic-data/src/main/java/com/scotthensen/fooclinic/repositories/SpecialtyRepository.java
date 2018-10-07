package com.scotthensen.fooclinic.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scotthensen.fooclinic.model.Specialty;

@Repository
public interface SpecialtyRepository extends CrudRepository<Specialty, Long> 
{
	Set<Specialty> findAll();
}
