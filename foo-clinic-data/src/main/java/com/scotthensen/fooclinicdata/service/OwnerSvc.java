package com.scotthensen.fooclinicdata.service;

import com.scotthensen.fooclinicdata.model.Owner;

public interface OwnerSvc extends CrudSvc<Owner, Long>
{
	Owner findByLastName(String lastName);
}
