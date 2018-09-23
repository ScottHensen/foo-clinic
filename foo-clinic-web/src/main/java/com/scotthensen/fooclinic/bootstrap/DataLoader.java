package com.scotthensen.fooclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.scotthensen.fooclinic.model.Owner;
import com.scotthensen.fooclinic.model.Vet;
import com.scotthensen.fooclinic.service.OwnerSvc;
import com.scotthensen.fooclinic.service.VetSvc;

@Component
public class DataLoader implements CommandLineRunner
{
	private final OwnerSvc ownerSvc;
	private final VetSvc   vetSvc;
	
	public DataLoader(OwnerSvc ownerSvc, VetSvc vetSvc) {
		this.ownerSvc = ownerSvc;
		this.vetSvc = vetSvc;
	}

	@Override
	public void run(String... args) throws Exception 
	{
		Owner owner1 = new Owner();
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");
		
		ownerSvc.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setFirstName("Fionna");
		owner2.setLastName("Glenanne");
		
		ownerSvc.save(owner2);
		
		System.out.println("DataLoader:  Loaded owners.");
		
		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		
		vetSvc.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("Jessie");
		vet2.setLastName("Porter");
		
		vetSvc.save(vet2);
		
		System.out.println("DataLoader:  Loaded vets.");		
	}

}
