package com.scotthensen.fooclinic.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scotthensen.fooclinic.model.Owner;
import com.scotthensen.fooclinic.model.Pet;
import com.scotthensen.fooclinic.model.PetType;
import com.scotthensen.fooclinic.service.OwnerSvc;
import com.scotthensen.fooclinic.service.PetSvc;
import com.scotthensen.fooclinic.service.PetTypeSvc;

@Controller
@RequestMapping( "/owners/{ownerId}" )
public class PetController 
{
	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
	
	private final PetSvc     petSvc;
	private final PetTypeSvc petTypeSvc;
	private final OwnerSvc   ownerSvc;
	
	public PetController(PetSvc petSvc, PetTypeSvc petTypeSvc, OwnerSvc ownerSvc ) {
		this.petSvc     = petSvc;
		this.ownerSvc   = ownerSvc;
		this.petTypeSvc = petTypeSvc;
	}
		
	
	@ModelAttribute( "types" )
	public Collection<PetType> populatePetTypes() {
		return petTypeSvc.findAll();
	}
	
	@ModelAttribute( "owner" )
	public Owner findOwner( @PathVariable("ownerId") Long ownerId ) {
		return ownerSvc.findById(ownerId);
	}
	
	@InitBinder( "owner" )
	public void initOwnerBinder( WebDataBinder dataBinder ) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping( "/pets/new" )
	public String initCreationForm(Owner owner, Model model)
	{
		Pet pet = new Pet();
		owner.getPets().add(pet);
		pet.setOwner(owner);
		model.addAttribute("pet", pet);
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping( "/pets/new" )
	public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, Model model)
	{
		if ( StringUtils.hasLength(pet.getName())      && 
			 pet.isNew()                               && 
			 owner.getPet(pet.getName(), true) != null   ) {
			
			result.rejectValue("name",  "duplicate", "already exists");
		}
		owner.getPets().add(pet);
		
		if ( result.hasErrors() ) {
			model.addAttribute("pet", pet);
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}
		else {
			petSvc.save(pet);
			return "redirect:/owners/" + owner.getId();
		}
	}
	
	@GetMapping( "/pets/{petId}/edit" )
	public String initUpdateForm( @PathVariable Long petId, Model model )
	{
		model.addAttribute("pet", petSvc.findById(petId));
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping( "/pets/{petId}/edit" )
	public String processUpdateForm( @Valid Pet pet, BindingResult result, Owner owner, Model model )
	{
		if ( result.hasErrors() ) {
			pet.setOwner(owner);
			model.addAttribute("pet", pet);
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}
		else {
			owner.getPets().add(pet);
			petSvc.save(pet);
			return "redirect:/owners/" + owner.getId();
		}
	}
	
}
