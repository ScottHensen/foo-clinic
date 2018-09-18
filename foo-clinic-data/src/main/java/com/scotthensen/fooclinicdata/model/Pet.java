package com.scotthensen.fooclinicdata.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pet 
{
	private PetType getType;
	private Owner   owner;
	private LocalDate birthDate;
}
