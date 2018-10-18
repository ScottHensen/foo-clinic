package com.scotthensen.fooclinic.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.scotthensen.fooclinic.service.OwnerSvc;

@ExtendWith( MockitoExtension.class )
class OwnerControllerTest 
{
	@Mock
	OwnerSvc ownerSvc;
	
	@InjectMocks
	OwnerController ownerController;
	
	Set<Owner> owners;
	MockMvc    mockMvc;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		owners = new HashSet<>();
		owners.add(Owner.builder().id(1L).build());
		owners.add(Owner.builder().id(2L).build());
		
		mockMvc = MockMvcBuilders
					.standaloneSetup(ownerController)
					.build();
	}

	@Test
	void testListOwners() throws Exception 
	{
		when(ownerSvc.findAll()).thenReturn(owners);
		
		mockMvc.perform(get("/owners"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/index"))
				.andExpect(model().attribute("owners", hasSize(2)));
	}

	@Test
	void testListOwnersByIndex() throws Exception 
	{
		when(ownerSvc.findAll()).thenReturn(owners);
		
		mockMvc.perform(get("/owners/index"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/index"))
				.andExpect(model().attribute("owners", hasSize(2)));
	}

	@Test
	void testFindOwners() throws Exception 
	{
		mockMvc.perform(get("/owners/find"))
				.andExpect(status().isOk())
				.andExpect(view().name("notimplementedyet"));
		
		verifyZeroInteractions(ownerSvc);
	}

}
