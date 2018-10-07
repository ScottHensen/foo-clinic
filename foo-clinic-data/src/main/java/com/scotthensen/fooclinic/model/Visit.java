package com.scotthensen.fooclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table( name = "visits" )
public class Visit extends BaseEntity
{
	@Column( name = "date" )
	private LocalDate date;
	
	@Column( name = "description" )
	private String description;
	
	@ManyToOne
	@JoinColumn( name = "pet_id" )
	private Pet pet;
}
