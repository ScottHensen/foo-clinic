package com.scotthensen.fooclinicweb.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.scotthensen.fooclinicdata.model.Owner;
import com.scotthensen.fooclinicdata.model.Vet;
import com.scotthensen.fooclinicdata.service.OwnerSvc;
import com.scotthensen.fooclinicdata.service.VetSvc;
import com.scotthensen.fooclinicdata.service.map.OwnerSvcMap;
import com.scotthensen.fooclinicdata.service.map.VetSvcMap;

@Component
public class DataLoader implements CommandLineRunner
{
	private final OwnerSvc ownerSvc;
	private final VetSvc   vetSvc;
	
	public DataLoader() {
		ownerSvc = new OwnerSvcMap();
		vetSvc   = new VetSvcMap();
	}
	
	@Override
	public void run(String... args) throws Exception 
	{
		Owner owner1 = new Owner();
		owner1.setId(1L);
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");
		
		ownerSvc.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setId(2L);
		owner2.setFirstName("Fionna");
		owner2.setLastName("Glenanne");
		
		ownerSvc.save(owner2);
		
		System.out.println("DataLoader:  Loaded owners.");
		
		Vet vet1 = new Vet();
		vet1.setId(1L);
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
		
		vetSvc.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setId(2L);
		vet2.setFirstName("Jessie");
		vet2.setLastName("Porter");
		
		vetSvc.save(vet2);
		
		System.out.println("DataLoader:  Loaded vets.");		
	}

}
