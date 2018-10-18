package com.scotthensen.fooclinic.service.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.scotthensen.fooclinic.model.Owner;
import com.scotthensen.fooclinic.repositories.OwnerRepository;

@ExtendWith( value = { MockitoExtension.class } )
class OwnerSDJpaSvcTest {

	final private String LAST_NAME = "Smith";
	final private Long ID = 1L;
	
	private Owner returnOwner;
	
	@Mock
	OwnerRepository ownerRepo;
	
	@InjectMocks
	OwnerSDJpaSvc ownerSvc;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		returnOwner = Owner.builder().id(ID).lastName(LAST_NAME).build();
	}

	@Test
	void testFindAll() 
	{
		Set<Owner> returnOwners = new HashSet<>();
		returnOwners.add(Owner.builder().id(1L).lastName(LAST_NAME).build());
		returnOwners.add(Owner.builder().id(2L).build());
		when(ownerRepo.findAll()).thenReturn(returnOwners);
		
		Set<Owner> owners = ownerSvc.findAll();
		
		assertNotNull(owners);
		assertEquals(2, owners.size());
	}

	@Test
	void testFindById() 
	{
		when(ownerRepo.findById(anyLong())).thenReturn(Optional.of(returnOwner));
		
		Owner owner = ownerSvc.findById(ID);
		
		assertNotNull(owner);
		assertEquals(ID, owner.getId());
	}

	@Test
	void testFindByIdNotFound() 
	{
		when(ownerRepo.findById(anyLong())).thenReturn(Optional.empty());
		
		Owner owner = ownerSvc.findById(ID);
		
		assertNull(owner);
	}

	@Test
	void testFindByLastName() 
	{
		// build mocked repo's response
		when(ownerRepo.findByLastName(any())).thenReturn(returnOwner);
		
		Owner owner = ownerSvc.findByLastName(LAST_NAME);
		
		assertEquals(LAST_NAME, owner.getLastName());
		verify(ownerRepo).findByLastName(any());		// verify the mock save occurred
	}
	
	@Test
	void testSave() 
	{
		when(ownerRepo.save(any())).thenReturn(returnOwner);

		Owner savedOwner = ownerSvc.save(returnOwner);

		assertEquals(ID, savedOwner.getId());
		verify(ownerRepo).save(any());					
	}

	@Test
	void testDelete() 
	{
		ownerSvc.delete(returnOwner);
		
		verify(ownerRepo, times(1)).delete(any());		// verify the mock delete occurred exactly 1 time
	}

	@Test
	void testDeleteById() 
	{
		ownerSvc.deleteById(1L);
		
		verify(ownerRepo, times(1)).deleteById(anyLong());
	}


}
