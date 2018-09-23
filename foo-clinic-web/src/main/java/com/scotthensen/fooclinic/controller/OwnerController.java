package com.scotthensen.fooclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scotthensen.fooclinic.service.OwnerSvc;

@Controller
@RequestMapping("/owners")
public class OwnerController 
{
	private final OwnerSvc ownerSvc;
	
	public OwnerController(OwnerSvc ownerSvc) {
		this.ownerSvc = ownerSvc;
	}
	
	@RequestMapping({"", "/", "/index", "/index.html"})
	public String listOwners(Model model)
	{
		model.addAttribute( "owners", ownerSvc.findAll() );
		
		return "owners/index";
	}
}
