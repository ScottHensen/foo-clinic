package com.scotthensen.fooclinic.service;

import java.util.List;

import com.scotthensen.fooclinic.model.Owner;
import com.scotthensen.fooclinic.model.OwnerPetsDTO;

public interface OwnerSvc extends CrudSvc<Owner, Long>
{
	Owner findByLastName(String lastName);
	List<OwnerPetsDTO> findOwnerPetsByLastName(String lastName);
}
