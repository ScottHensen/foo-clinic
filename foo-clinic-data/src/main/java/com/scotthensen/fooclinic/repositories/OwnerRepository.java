package com.scotthensen.fooclinic.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scotthensen.fooclinic.model.Owner;
import com.scotthensen.fooclinic.model.OwnerPetsDTO;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long>
{
	Owner findByLastName(String lastName);
	
	Set<Owner> findAll();
	
	@Query( value = "SELECT new com.scotthensen.fooclinic.model.OwnerPetsDTO " 
				  + "     ( o.firstName,   "
				  + "       o.lastName,    "
				  + "       o.address,     "
				  + "       p.name       ) "
				  + "FROM   Owner o,       "
				  + "       Pet p          "
				  + "WHERE  o.lastName = :lastName  "
				  + "  AND  o.id       = p.owner    "
			) 
	List<OwnerPetsDTO> findOwnerPetsByLastName(@Param("lastName") String lastName);
}
