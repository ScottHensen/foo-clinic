package com.scotthensen.fooclinic.controller;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.scotthensen.fooclinic.model.Owner;
import com.scotthensen.fooclinic.model.Pet;
import com.scotthensen.fooclinic.model.PetType;
import com.scotthensen.fooclinic.service.OwnerSvc;
import com.scotthensen.fooclinic.service.PetSvc;
import com.scotthensen.fooclinic.service.PetTypeSvc;

@ExtendWith(MockitoExtension.class)
public class PetControllerTest 
{
	@Mock
	PetSvc petSvc;
	
	@Mock
	OwnerSvc ownerSvc;
	
	@Mock
	PetTypeSvc petTypeSvc;
	
	@InjectMocks
	PetController petController;
	
	MockMvc mockMvc;
	
	Owner owner;
	Pet pet;
	Set<PetType> petTypes;
	
	@BeforeEach
	void setUp() 
	{
		owner = Owner.builder().id(1L).build();
		pet   = Pet.builder().id(1L).build();
		
		petTypes = new HashSet<>();
		petTypes.add(PetType.builder().id(1L).name("Dog").build());
		petTypes.add(PetType.builder().id(2L).name("Cat").build());
		
		mockMvc = MockMvcBuilders
					.standaloneSetup(petController)
					.build();
	}
	
	@Test
	void initCreationForm() throws Exception
	{
		when(ownerSvc.findById(anyLong())).thenReturn(owner);
		when(petTypeSvc.findAll()).thenReturn(petTypes);
		
		mockMvc.perform(get("/owners/1/pets/new"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("owner"))
				.andExpect(model().attributeExists("pet"))
				.andExpect(view().name("pets/createOrUpdatePetForm"));
	}
	
	@Test
	void processCreationForm() throws Exception
	{
		when(ownerSvc.findById(anyLong())).thenReturn(owner);
		when(petTypeSvc.findAll()).thenReturn(petTypes);
	
		mockMvc.perform(post("/owners/1/pets/new"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1"));
		
		verify(petSvc).save(any());
	}
	@Test
	void initUpdateForm() throws Exception
	{
		when(ownerSvc.findById(anyLong())).thenReturn(owner);
		when(petTypeSvc.findAll()).thenReturn(petTypes);
		when(petSvc.findById(anyLong())).thenReturn(pet);
		
		mockMvc.perform(get("/owners/1/pets/1/edit"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("owner"))
				.andExpect(model().attributeExists("pet"))
				.andExpect(view().name("pets/createOrUpdatePetForm"));
	}
	
	@Test
	void processUpdateForm() throws Exception
	{
		when(ownerSvc.findById(anyLong())).thenReturn(owner);
		when(petTypeSvc.findAll()).thenReturn(petTypes);
	
		mockMvc.perform(post("/owners/1/pets/1/edit"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1"));
		
		verify(petSvc).save(any());
	}
}
