package com.scotthensen.fooclinic.controller;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scotthensen.fooclinic.model.Owner;
import com.scotthensen.fooclinic.model.OwnerPetsDTO;
import com.scotthensen.fooclinic.service.OwnerSvc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/owners")
public class OwnerController 
{
	private final OwnerSvc ownerSvc;
	
	public OwnerController(OwnerSvc ownerSvc) {
		this.ownerSvc = ownerSvc;
	}
	
	@RequestMapping( { "", "/", "/index", "/index.html" } )
	public String listOwners(Model model)
	{
		log.info("\n >>>>> ownerSvc.findAll()");
		Set<Owner> allOwners = ownerSvc.findAll();
		log.info(allOwners.toString());
		model.addAttribute( "owners", allOwners );
		
		log.info("\n >>>>> ownerSvc.findByLastName()");
		Owner owner = ownerSvc.findByLastName("Doolittle");
		log.info(owner.toString());
		model.addAttribute( "ownerEntity",  owner);
		
		log.info("\n >>>>> ownerSvc.findOwnerPetsByLastName()");
		List<OwnerPetsDTO> ownerPets = ownerSvc.findOwnerPetsByLastName("Doolittle");
		log.info(ownerPets.toString());
		model.addAttribute( "ownerPetsDTO", ownerPets);
		
		return "owners/index";
	}

}
