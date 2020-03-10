package com.longmuir.petclinic.controllers;

import com.longmuir.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listOwners(Model model){
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping("/find")
    public String findOwners(){
        return "notimplemented";
    }


//    //TODO remove this. Was testing the links
//    @RequestMapping({"/find"})
//    public String hackOwner(Model model){
//        model.addAttribute("owner", ownerService.findById(1L));
//        return "owners/find";
//    }
}
