package com.scotthensen.fooclinic.formatter;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.scotthensen.fooclinic.model.PetType;
import com.scotthensen.fooclinic.service.PetTypeSvc;

/*
 * Spring MVC is going to use this when it's parsing/formatting the PetType
 */
@Component
public class PetTypeFormatter implements Formatter<PetType> 
{

    private final PetTypeSvc petTypeService;

    public PetTypeFormatter(PetTypeSvc petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException 
    {
        Collection<PetType> petTypes = petTypeService.findAll();

        for (PetType type : petTypes) {
            if ( type != null && type.getName() != null && type.getName().equals(text)) {
                return type;
            }
        }

        throw new ParseException("type not found: " + text, 0);
    }
}