package com.scotthensen.fooclinic.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pet extends BaseEntity
{
	private PetType getType;
	private Owner   owner;
	private LocalDate birthDate;
}