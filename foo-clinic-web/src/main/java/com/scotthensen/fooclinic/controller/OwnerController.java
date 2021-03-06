package com.scotthensen.fooclinic.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder)
	{
		dataBinder.setDisallowedFields("id");
	}
	
//	@RequestMapping( { "", "/", "/index", "/index.html" } )
//	public String listOwners(Model model)
//	{
//		log.info("\n >>>>> ownerSvc.findAll()");
//		Set<Owner> allOwners = ownerSvc.findAll();
//		log.info(allOwners.toString());
//		model.addAttribute( "owners", allOwners );
//		
////--  this is not part of the app.
////--  just playing around with jpa to see underlying sql calls
////		log.info("\n >>>>> ownerSvc.findByLastName()");
////		Owner owner = ownerSvc.findByLastName("Doolittle");
////		log.info(owner.toString());
////		model.addAttribute( "ownerEntity",  owner);
////		
////		log.info("\n >>>>> ownerSvc.findOwnerPetsByLastName()");
////		List<OwnerPetsDTO> ownerPets = ownerSvc.findOwnerPetsByLastName("Doolittle");
////		log.info(ownerPets.toString());
////		model.addAttribute( "ownerPetsDTO", ownerPets);
//		
//		return "owners/index";
//	}

	@GetMapping( { "", "/" } )
	public String processFindForm( Owner owner, BindingResult result, Model model)
	{
		if ( owner.getLastName() == null ) {
			owner.setLastName("");
		}
		
		List<Owner> owners = ownerSvc.findAllByLastNameLike("%" + owner.getLastName() + "%");
		
		if ( owners.isEmpty() ) {
			result.rejectValue("lastName", "notFound", "not found");
			return "owners/findOwner";
		}
		else if ( owners.size() == 1 ) {
			owner = owners.get(0);
			return "redirect:/owners/" + owner.getId();
		}
		else {
			model.addAttribute("selections", owners);
			return "owners/ownersList";
		}
	}

	@GetMapping( "/{ownerId}" )
	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId)
	{
		ModelAndView mav = new ModelAndView( "owners/ownerDetails" );
		mav.addObject(ownerSvc.findById(ownerId));
		return mav;
	}
	
	@GetMapping( "/find" )
	public String initFindForm(Model model) 
	{
		model.addAttribute("owner", Owner.builder().build());
		return "owners/findOwner";
	}
	
	@GetMapping( "/new" )
	public String initCreationForm( Model model )
	{
		model.addAttribute("owner", Owner.builder().build());
		return "owners/createOrUpdateOwnerForm";
	}
	
	@PostMapping( "/new" )
	public String processCreationForm( @Valid Owner owner, BindingResult result )
	{
		if ( result.hasErrors() ) {
			return "owners/createOrUpdateOwnerForm";
		}
		else {
			Owner savedOwner = ownerSvc.save(owner);
			return "redirect:/owners/" + savedOwner.getId();
		}			
	}
	
	@GetMapping( "/{ownerId}/edit")
	public String initUpdateOwnerForm( @PathVariable Long ownerId, Model model )
	{
		model.addAttribute(ownerSvc.findById(ownerId));
		return "owners/createOrUpdateOwnerForm";
	}
	
	@PostMapping( "/{ownerId}/edit" )
	public String processUpdateOwnerForm( @Valid Owner owner, BindingResult result, @PathVariable Long ownerId )
	{
		if ( result.hasErrors() ) {
			return "owners/createOrUpdateOwnerForm";
		}
		else {
			owner.setId(ownerId);
			Owner savedOwner = ownerSvc.save(owner);
			return "redirect:/owners/" + savedOwner.getId();
		}
	}
}
