package com.scotthensen.fooclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scotthensen.fooclinic.service.VetSvc;

@Controller
public class VetController 
{
	private final VetSvc vetSvc;
	
	public VetController(VetSvc vetSvc) {
		this.vetSvc = vetSvc;
	}
	
	@RequestMapping({"/vets", "/vets.html", "/vets/index", "/vets/index.html"})
	public String listVets(Model model)
	{
		model.addAttribute( "vets", vetSvc.findAll() );
		
		return "vets/index";
	}
}
