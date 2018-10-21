package com.scotthensen.fooclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.scotthensen.fooclinic.model.Owner;
import com.scotthensen.fooclinic.model.Pet;
import com.scotthensen.fooclinic.model.PetType;
import com.scotthensen.fooclinic.model.Specialty;
import com.scotthensen.fooclinic.model.Vet;
import com.scotthensen.fooclinic.model.Visit;
import com.scotthensen.fooclinic.service.OwnerSvc;
import com.scotthensen.fooclinic.service.PetTypeSvc;
import com.scotthensen.fooclinic.service.SpecialtySvc;
import com.scotthensen.fooclinic.service.VetSvc;
import com.scotthensen.fooclinic.service.VisitSvc;

@Component
public class DataLoader implements CommandLineRunner
{
	private final OwnerSvc 		ownerSvc;
	private final VetSvc   		vetSvc;
	private final PetTypeSvc 	petTypeSvc;
	private final SpecialtySvc 	specialtySvc;
	private final VisitSvc      visitSvc;
	
	public DataLoader(OwnerSvc ownerSvc, VetSvc vetSvc, PetTypeSvc petTypeSvc, SpecialtySvc specialtySvc, VisitSvc visitSvc) {
		this.ownerSvc 		= ownerSvc;
		this.vetSvc 		= vetSvc;
		this.petTypeSvc	 	= petTypeSvc;
		this.specialtySvc 	= specialtySvc;
		this.visitSvc       = visitSvc;
	}

	@Override
	public void run(String... args) throws Exception 
	{
		int count = petTypeSvc.findAll().size();
		
		if ( count == 0 ) {
			loadData();
		}
	}
	
	
	private void loadData() 
	{
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeSvc.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType = petTypeSvc.save(cat);
		
		Specialty radiology = new Specialty();
		radiology.setDescription("Radiology");
		Specialty savedRadiology = specialtySvc.save(radiology);
		
		Specialty surgery = new Specialty();
		surgery.setDescription("Surgery");
		Specialty savedSurgery = specialtySvc.save(surgery);
		
		Specialty dentistry = new Specialty();
		dentistry.setDescription("Dentistry");
		Specialty savedDentistry = specialtySvc.save(dentistry);
		
		
		Owner owner1 = new Owner();
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");
		owner1.setAddress("123 Brickerel");
		owner1.setCity("Miami");
		owner1.setTelephone("1231234567");
		
		Pet mikesPet = new Pet();
		mikesPet.setPetType(savedDogPetType);
		mikesPet.setOwner(owner1);
		mikesPet.setBirthDate(LocalDate.now());
		mikesPet.setName("Rosco");
		
		owner1.getPets().add(mikesPet);
		
		ownerSvc.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setFirstName("Fionna");
		owner2.setLastName("Glenanne");
		owner2.setAddress("123 Brickerel");
		owner2.setCity("Miami");
		owner2.setTelephone("1231234567");

		Pet fionasPet = new Pet();
		fionasPet.setPetType(savedCatPetType);
		fionasPet.setOwner(owner2);
		fionasPet.setBirthDate(LocalDate.now());
		fionasPet.setName("Just Cat");
	
		owner2.getPets().add(fionasPet);	
	
		ownerSvc.save(owner2);
		
		
		Owner owner3 = new Owner();
		owner3.setFirstName("Dr.");
		owner3.setLastName("Doolittle");
		owner3.setAddress("1313 Mockingbird Ln");
		owner3.setCity("Tempe");
		owner3.setTelephone("4808675309");
		
		new Pet();
		Pet pet1 = Pet.builder().name("dog1").owner(owner3).petType(savedDogPetType).build();
		new Pet();
		Pet pet2 = Pet.builder().name("dog2").owner(owner3).petType(savedDogPetType).build();
		new Pet();
		Pet pet3 = Pet.builder().name("dog3").owner(owner3).petType(savedDogPetType).build();
		new Pet();
		Pet pet4 = Pet.builder().name("dog4").owner(owner3).petType(savedDogPetType).build();
		new Pet();
		Pet pet5 = Pet.builder().name("dog5").owner(owner3).petType(savedDogPetType).build();
		owner3.getPets().add(pet1);
		owner3.getPets().add(pet2);
		owner3.getPets().add(pet3);
		owner3.getPets().add(pet4);
		owner3.getPets().add(pet5);
		
		ownerSvc.save(owner3);
		System.out.println("Doolittle: " + owner3.toString());
		
		
		Visit catVisit = new Visit();
		catVisit.setPet(fionasPet);
		catVisit.setDate(LocalDate.now());
		catVisit.setDescription("Sneezy Kitty");
		
		visitSvc.save(catVisit);
		System.out.println("DataLoader:  Loaded owners.");
		
		
		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");
//		vet1.getSpecialties().add(savedRadiology);
//		vet1.addSpecialty(savedRadiology); //.getSpecialties().add(savedRadiology);
		vetSvc.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("Jessie");
		vet2.setLastName("Porter");
//		vet2.addSpecialty(savedDentistry);
//		vet2.addSpecialty(savedSurgery);
//		vet2.getSpecialties().add(savedSurgery);
//		vet2.getSpecialties().add(savedDentistry);
		vetSvc.save(vet2);
		
		System.out.println("DataLoader:  Loaded vets.");		
	}

}
