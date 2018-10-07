package com.scotthensen.fooclinic.service;

import com.scotthensen.fooclinic.model.Owner;

public interface OwnerSvc extends CrudSvc<Owner, Long>
{
	Owner findByLastName(String lastName);
	
}
