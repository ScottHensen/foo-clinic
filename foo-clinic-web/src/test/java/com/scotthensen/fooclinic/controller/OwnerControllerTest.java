package com.scotthensen.fooclinic.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
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
	List<Owner> oneOwnerList;
	List<Owner> twoOwnersList;
	MockMvc    mockMvc;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		owners = new HashSet<>();
		owners.add(Owner.builder().id(1L).build());
		owners.add(Owner.builder().id(2L).build());
		
		oneOwnerList = new ArrayList<>();
		oneOwnerList.add(Owner.builder().id(1L).build());
		
		twoOwnersList = new ArrayList<>();
		twoOwnersList.add(Owner.builder().id(1L).build());
		twoOwnersList.add(Owner.builder().id(2L).build());
		mockMvc = MockMvcBuilders
					.standaloneSetup(ownerController)
					.build();
	}

//	@Test
//	void testListOwners() throws Exception 
//	{
//		when(ownerSvc.findAll()).thenReturn(owners);
//		
//		mockMvc.perform(get("/owners"))
//				.andExpect(status().isOk())
//				.andExpect(view().name("owners/index"))
//				.andExpect(model().attribute("owners", hasSize(2)));
//	}
//
//	@Test
//	void testListOwnersByIndex() throws Exception 
//	{
//		when(ownerSvc.findAll()).thenReturn(owners);
//		
//		mockMvc.perform(get("/owners/index"))
//				.andExpect(status().isOk())
//				.andExpect(view().name("owners/index"))
//				.andExpect(model().attribute("owners", hasSize(2)));
//	}

	@Test
	void testFindOwners() throws Exception 
	{
		mockMvc.perform(get("/owners/find"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/findOwner"))
				.andExpect(model().attributeExists("owner"));
		
		verifyZeroInteractions(ownerSvc);
	}

	@Test
	void testFindOwnersFormReturnOne() throws Exception 
	{
		when(ownerSvc.findAllByLastNameLike(anyString())).thenReturn(oneOwnerList);
		
		mockMvc.perform(get("/owners"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1"));
		
	}

	@Test
	void testFindOwnersFormReturnMany() throws Exception 
	{
		when(ownerSvc.findAllByLastNameLike(anyString())).thenReturn(twoOwnersList);
		
		mockMvc.perform(get("/owners"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/ownersList"))
				.andExpect(model().attribute("selections", hasSize(2)));
		
	}

	@Test
	void testDisplayOwners() throws Exception 
	{
		when(ownerSvc.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

		mockMvc.perform(get("/owners/123"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/ownerDetails"))
				.andExpect(model().attribute("owner", hasProperty("id", is(1L))));

	}

	@Test
	void testInitializeCreationForm() throws Exception
	{
		mockMvc.perform(get("/owners/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/createOrUpdateOwnerForm"))
				.andExpect(model().attributeExists("owner"));
	
		verifyZeroInteractions(ownerSvc);
	}

	@Test
	void testProcessCreationForm() throws Exception
	{
		when(ownerSvc.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).build());
		
		mockMvc.perform(post("/owners/new"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1"))
				.andExpect(model().attributeExists("owner"));
		
		verify(ownerSvc).save(ArgumentMatchers.any());
	}

	@Test
	void testInitializeUpdateForm() throws Exception
	{
		when(ownerSvc.findById(any())).thenReturn(Owner.builder().id(1L).build());
		
		mockMvc.perform(get("/owners/1/edit"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/createOrUpdateOwnerForm"))
				.andExpect(model().attributeExists("owner"));
	
		verifyZeroInteractions(ownerSvc);
	}

	@Test
	void testProcessUpdateForm() throws Exception
	{
		when(ownerSvc.save(any())).thenReturn(Owner.builder().id(1L).build());
		
		mockMvc.perform(post("/owners/1/edit"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/owners/1"))
				.andExpect(model().attributeExists("owner"));
		
		verify(ownerSvc).save(any());
	}

}
