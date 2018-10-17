package com.scotthensen.fooclinic.service.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.scotthensen.fooclinic.model.Owner;

class OwnerMapSvcTest {

	OwnerMapSvc ownerMapSvc;
	
	final Long ownerId = 1L;
	final String lastName = "Smith";
	
	@BeforeEach
	void setUp() throws Exception 
	{
		ownerMapSvc = new OwnerMapSvc( new PetTypeMapSvc(), new PetMapSvc() );
		ownerMapSvc.save(Owner.builder().id(ownerId).lastName(lastName).build());
	}

	@Test
	void testFindAll() 
	{
		Set<Owner> owners = ownerMapSvc.findAll();
		assertEquals(1, owners.size());
	}

	@Test
	void testFindByIdLong() 
	{
		Owner owner = ownerMapSvc.findById(ownerId);
		assertEquals(ownerId, owner.getId());
	}

	@Test
	void testDeleteByIdLong() 
	{
		ownerMapSvc.deleteById(ownerId);
		assertEquals(0, ownerMapSvc.findAll().size());
	}

	@Test
	void testDeleteOwner() 
	{
		ownerMapSvc.delete(ownerMapSvc.findById(ownerId));
		assertEquals(0, ownerMapSvc.findAll().size());
	}

	@Test
	void testSaveExistingIdOwner() 
	{
		Long id = 2L;
		Owner owner2 = Owner.builder().id(id).build();
		Owner savedOwner = ownerMapSvc.save(owner2);
		assertEquals(id, savedOwner.getId());
	}

	@Test
	void testSaveNoIdOwner() 
	{
		Owner savedOwner = ownerMapSvc.save(Owner.builder().build());
		assertNotNull(savedOwner);
		assertNotNull(savedOwner.getId());
	}

	@Test
	void testFindByLastName() 
	{
		Owner owner = ownerMapSvc.findByLastName(lastName);
		assertNotNull(owner);
		assertEquals(lastName, owner.getLastName());
		assertEquals(ownerId, owner.getId());
	}

	@Test
	void testFindByLastNameNotFound() 
	{
		Owner owner = ownerMapSvc.findByLastName("foo");
		assertNull(owner);
	}

//	@Test
//	void testFindOwnerPetsByLastName() {
//		fail("Not yet implemented");
//	}

}
