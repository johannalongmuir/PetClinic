package com.longmuir.petclinic.controllers;

import com.longmuir.petclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/vets")
@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listVets(Model model){
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }


    @RequestMapping("/findbyid")
    public String getAVet(@RequestParam(value = "id", defaultValue = "-1") String id, Model model){
        model.addAttribute("vets", vetService.findById(Long.parseLong(id)));
        return "vets/index";
    }
}
