package com.scotthensen.fooclinicdata.service.map;

import java.util.Set;

import com.scotthensen.fooclinicdata.model.Pet;
import com.scotthensen.fooclinicdata.service.PetSvc;

public class PetSvcMap extends AbstractMapSvc<Pet, Long> implements PetSvc
{
	@Override
	public Set<Pet> findAll() {
		return super.findAll();
	}

	@Override
	public Pet findById(Long id) {
		return super.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Pet object) {
		super.delete(object);
	}

	@Override
	public Pet save(Pet object) {
		return super.save(object.getId(), object);
	}

}
