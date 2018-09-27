package com.scotthensen.fooclinic.service.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.scotthensen.fooclinic.model.Specialty;
import com.scotthensen.fooclinic.service.SpecialtySvc;

@Service
public class SpecialtyMapSvc extends AbstractMapSvc<Specialty, Long> implements SpecialtySvc
{
	@Override
	public Set<Specialty> 	findAll() 					{ return super.findAll(); }

	@Override
	public Specialty 		findById(Long id) 			{ return super.findById(id); }

	@Override
	public void 			deleteById(Long id) 		{ super.deleteById(id); }

	@Override
	public void 			delete(Specialty object) 	{ super.delete(object); }

	@Override
	public Specialty 		save(Specialty object) 		{ return super.save(object); }

}
