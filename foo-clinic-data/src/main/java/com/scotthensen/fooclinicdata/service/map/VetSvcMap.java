package com.scotthensen.fooclinicdata.service.map;

import java.util.Set;

import com.scotthensen.fooclinicdata.model.Vet;
import com.scotthensen.fooclinicdata.service.VetSvc;

public class VetSvcMap extends AbstractMapSvc<Vet, Long> implements VetSvc
{
	@Override
	public Set<Vet> findAll() {
		return super.findAll();
	}

	@Override
	public Vet findById(Long id) {
		return super.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public void delete(Vet object) {
		super.delete(object);
	}

	@Override
	public Vet save(Vet object) {
		return super.save(object.getId(), object);
	}

}
