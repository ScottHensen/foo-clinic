package com.scotthensen.fooclinic.service.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.scotthensen.fooclinic.model.PetType;
import com.scotthensen.fooclinic.service.PetTypeSvc;

@Service
@Profile( {"default", "map"} )
public class PetTypeMapSvc extends AbstractMapSvc<PetType, Long> implements PetTypeSvc
{
	@Override
	public Set<PetType> findAll() 				{ return super.findAll(); }

	@Override
	public PetType 		findById(Long id) 		{ return super.findById(id); }

	@Override
	public void 		deleteById(Long id) 	{ super.deleteById(id);	}

	@Override
	public void 		delete(PetType object) 	{ super.delete(object);	}

	@Override
	public PetType 		save(PetType object)	{ return super.save(object); }
}
