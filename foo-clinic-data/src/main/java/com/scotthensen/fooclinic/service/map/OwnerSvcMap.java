package com.scotthensen.fooclinic.service.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.scotthensen.fooclinic.model.Owner;
import com.scotthensen.fooclinic.service.OwnerSvc;

@Service
public class OwnerSvcMap extends AbstractMapSvc<Owner, Long> implements OwnerSvc
{

	@Override
	public Set<Owner> findAll() 	{ return super.findAll(); }

	@Override
	public Owner findById(Long id) 	{ return super.findById(id); }

	@Override
	public Owner save(Owner object) { return super.save(object); }

	@Override
	public void deleteById(Long id) { super.deleteById(id); }

	@Override
	public void delete(Owner object) { super.delete(object); }

	@Override
	public Owner findByLastName(String lastName) { return null; } //TODO

}
