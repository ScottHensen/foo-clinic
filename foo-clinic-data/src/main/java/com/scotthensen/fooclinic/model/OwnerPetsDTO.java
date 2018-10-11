package com.scotthensen.fooclinic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwnerPetsDTO 
{
	private String firstName;
	private String lastName;
	private String address;
	private String petName;
	
	@Override
	public String toString() {
		return "OwnerPetsDTO [ \n"
			+  "    firstName=" + firstName + "\n"
			+  "  , lastName="  + lastName  + "\n"
			+  "  , address="   + address   + "\n"
			+  "  , petName="   + petName   + "\n"
			+  "]";
	} 
}
