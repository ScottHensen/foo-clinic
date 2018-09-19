package com.scotthensen.fooclinicdata.service.map;

import java.util.Set;

import com.scotthensen.fooclinicdata.model.Owner;
import com.scotthensen.fooclinicdata.service.CrudSvc;

public class OwnerSvcMap extends AbstractMapSvc<Owner, Long> implements CrudSvc<Owner, Long>
{

	@Override
	public Set<Owner> findAll() 
	{
		return super.findAll();
	}

	@Override
	public Owner findById(Long id) 
	{
		return super.findById(id);
	}

	@Override
	public Owner save(Owner object) 
	{
		return super.save(object.getId(), object);
	}

	@Override
	public void deleteById(Long id) 
	{
		super.deleteById(id);
	}

	@Override
	public void delete(Owner object) 
	{
		super.delete(object);
	}

}
