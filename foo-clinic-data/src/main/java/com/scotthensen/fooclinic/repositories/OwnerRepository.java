package com.scotthensen.fooclinic.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scotthensen.fooclinic.model.Owner;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long>
{
	Owner findByLastName(String lastName);
	Set<Owner> findAll();
//	void delete(Owner owner);
//	void deleteById(Long id);
}
