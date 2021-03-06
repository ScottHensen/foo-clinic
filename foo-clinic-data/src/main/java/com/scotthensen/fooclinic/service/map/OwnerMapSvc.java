package com.scotthensen.fooclinic.service.map;

import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.scotthensen.fooclinic.model.Owner;
import com.scotthensen.fooclinic.model.OwnerPetsDTO;
import com.scotthensen.fooclinic.model.Pet;
import com.scotthensen.fooclinic.service.OwnerSvc;
import com.scotthensen.fooclinic.service.PetSvc;
import com.scotthensen.fooclinic.service.PetTypeSvc;

@Service
@Profile( {"default", "map"} )
public class OwnerMapSvc extends AbstractMapSvc<Owner, Long> implements OwnerSvc
{
	private final PetTypeSvc petTypeSvc;
	private final PetSvc petSvc;
	
	public OwnerMapSvc(PetTypeSvc petTypeSvc, PetSvc petSvc) {
		super();
		this.petTypeSvc = petTypeSvc;
		this.petSvc = petSvc;
	}

	
	@Override
	public Set<Owner> findAll() 				{ return super.findAll(); }

	@Override
	public Owner findById(Long id) 				{ return super.findById(id); }

	@Override //TODO
	public List<Owner> findAllByLastNameLike(String lastName) {return null; }

	@Override
	public void deleteById(Long id) 			{ super.deleteById(id); }

	@Override
	public void delete(Owner object) 			{ super.delete(object); }

	@Override
	public Owner findByLastName(String lastName) 
	{ 
		return this.findAll()
				.stream()
				.filter(o -> o.getLastName().equalsIgnoreCase(lastName))
				.findFirst()
				.orElse(null); 
	} 

	@Override
	public List<OwnerPetsDTO> findOwnerPetsByLastName(String lastName) { return null; } //TODO

	@Override
	public Owner save(Owner object) 
	{ 
		if ( object != null ) 
		{
			if ( object.getPets() != null ) 
			{
				object.getPets().forEach( pet -> {
					
					if ( pet.getPetType() !=null ) 
					{
						if ( pet.getPetType().getId() == null ) 
						{
							pet.setPetType( petTypeSvc.save( pet.getPetType() ) );
						}
					}
					
					if ( pet.getId() == null )
					{
						Pet savedPet = petSvc.save(pet);
						pet.setId(savedPet.getId());
					}
				});
			}
			return super.save( object );
		}
		else 
		{ 
			return null;
		}
	}
}
