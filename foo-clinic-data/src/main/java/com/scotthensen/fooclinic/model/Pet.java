package com.scotthensen.fooclinic.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.scotthensen.fooclinic.model.PetType.PetTypeBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( name = "pets" )
public class Pet extends BaseEntity
{
	@Builder
	public Pet(Long id, String name, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits) 
	{
		super(id);
		this.name      = name;
		this.petType   = petType;
		this.owner     = owner;
		this.birthDate = birthDate;
		
		if ( visits != null && !visits.isEmpty() ) {
			this.visits = visits;
		}
	}

	@Column( name = "name")
	private String  name;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "type_id" )
	private PetType petType;
	
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "owner_id" )
	private Owner   owner;
	
	@Column( name = "birth_date")
	private LocalDate birthDate;
	
	@OneToMany( cascade = CascadeType.ALL, mappedBy = "pet", fetch = FetchType.LAZY )
	//@Builder.Default
	private Set<Visit> visits = new HashSet<>();

	@Override
	public String toString() {
		return    "Pet ["
				+ "       name="      + name      + "\n"
//				+ "     , petType="   + petType   + "\n"	// this will negate lazy loading
				+ "     , birthDate=" + birthDate + "\n"
//				+ "     , visits="    + visits    + "\n"	// this will negate lazy loading
				+ "]";
	}
}
