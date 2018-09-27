package com.scotthensen.fooclinic.service.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.scotthensen.fooclinic.model.Specialty;
import com.scotthensen.fooclinic.model.Vet;
import com.scotthensen.fooclinic.service.SpecialtySvc;
import com.scotthensen.fooclinic.service.VetSvc;

@Service
public class VetMapSvc extends AbstractMapSvc<Vet, Long> implements VetSvc
{
	private final SpecialtySvc specialtySvc;
	
	public VetMapSvc(SpecialtySvc specialtySvc) {
		this.specialtySvc = specialtySvc;
	}
	
	
	@Override
	public Set<Vet> findAll() 		{ return super.findAll(); }

	@Override
	public Vet findById(Long id) 	{ return super.findById(id); }

	@Override
	public void deleteById(Long id) { super.deleteById(id); }

	@Override
	public void delete(Vet object) 	{ super.delete(object); }

	@Override
	public Vet save(Vet object) 	
	{ 
		if ( object.getSpecialties().size() > 0 )
		{
			object.getSpecialties().forEach(specialty -> {
				if (specialty.getId() == null ) {
					Specialty savedSpecialty = specialtySvc.save(specialty);
					specialty.setId(savedSpecialty.getId());
				}
			});
		}
		return super.save(object); 
	}
}
