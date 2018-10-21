package com.scotthensen.fooclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table( name = "owners" )
public class Owner extends Person
{
	@Builder
	public Owner(Long id, String firstName, String lastName, 
				 String address, String city, String telephone, Set<Pet> pets) 
	{
		super(id, firstName, lastName);
		this.address   = address;
		this.city      = city;
		this.telephone = telephone;
		
		if ( pets != null ) {	// don't build with null set; let it get init'd to empty set with below instantiation
			this.pets = pets;
		}
	}
	
	@Column( name = "address" )
	private String address;

	@Column( name = "city" )
	private String city;
	
	@Column( name = "telephone" )
	private String telephone;
	
	@OneToMany( cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.LAZY )
	private Set<Pet> pets = new HashSet<>();

	
	public Pet getPet(String name) {
		return getPet(name, false);
	}
	
	public Pet getPet(String name, boolean ignoreNew)
	{
		name = name.toLowerCase();
		for ( Pet pet : pets ) {
			if ( ! ignoreNew || ! pet.isNew() ) {
				String compName = pet.getName();
				compName = compName.toLowerCase();
				if ( compName.equals(name) ) {
					return pet;
				}
			}
		}
		return null;
	}
	@Override
	public String toString() {
		return "Owner [ \n"
				+ "      address="   + address   + "\n"
				+ "    , city="      + city      + "\n"
				+ "    , telephone=" + telephone + "\n"
//				+ "    , pets="      + pets      + "\n"	 // this will negate lazy loading
				+ "]";
	}
}
