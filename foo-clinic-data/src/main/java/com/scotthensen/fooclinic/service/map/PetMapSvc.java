package com.scotthensen.fooclinic.service.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.scotthensen.fooclinic.model.Pet;
import com.scotthensen.fooclinic.service.PetSvc;

@Service
public class PetMapSvc extends AbstractMapSvc<Pet, Long> implements PetSvc
{
	@Override
	public Set<Pet> findAll() 			{ return super.findAll(); }

	@Override
	public Pet 		findById(Long id) 	{ return super.findById(id); }

	@Override
	public void 	deleteById(Long id) { super.deleteById(id);	}

	@Override
	public void 	delete(Pet object) 	{ super.delete(object);	}

	@Override
	public Pet 		save(Pet object) 	{ return super.save(object); }
}
