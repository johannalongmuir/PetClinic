package com.longmuir.petclinic.bootstrap;

import com.longmuir.petclinic.model.Owner;
import com.longmuir.petclinic.model.PetType;
import com.longmuir.petclinic.model.Vet;
import com.longmuir.petclinic.services.OwnerService;
import com.longmuir.petclinic.services.PetTypeService;
import com.longmuir.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;



    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatType = petTypeService.save(cat);

        PetType mouse = new PetType();
        mouse.setName("mouse");
        PetType savedMouseType = petTypeService.save(mouse);

        Owner steve = new Owner();
        steve.setFirstName("Steve");
        steve.setLastName("Jones");
        ownerService.save(steve);

        Owner michael = new Owner();
        michael.setFirstName("Michael");
        michael.setLastName("Weston");
        ownerService.save(michael);

        Owner keith = new Owner();
        keith.setFirstName("Keith");
        keith.setLastName("Weston");
        ownerService.save(keith);

        System.out.println("Loaded owners");

        Vet sam = new Vet();
        sam.setFirstName("Sam");
        sam.setLastName("Axe");
        vetService.save(sam);

        Vet james = new Vet();
        james.setFirstName("James");
        james.setLastName("Stone");
        vetService.save(james);

        Vet sarah = new Vet();
        sarah.setFirstName("Sarah");
        sarah.setLastName("Stone");
        vetService.save(sarah);

        System.out.println("Loaded Vets");


    }
}
