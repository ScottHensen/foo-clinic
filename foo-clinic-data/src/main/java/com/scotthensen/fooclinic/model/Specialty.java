package com.scotthensen.fooclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table( name = "specialties" ) 
public class Specialty extends BaseEntity 
{
	@Column( name = "description" )
	private String description;
}
