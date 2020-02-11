package com.longmuir.petclinic.bootstrap;

import com.longmuir.petclinic.model.Owner;
import com.longmuir.petclinic.model.Vet;
import com.longmuir.petclinic.services.OwnerService;
import com.longmuir.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;


    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner steve = new Owner();
        steve.setId(1L);
        steve.setFirstName("Steve");
        steve.setLastName("Jones");
        ownerService.save(steve);

        Owner michael = new Owner();
        michael.setId(2L);
        michael.setFirstName("Michael");
        michael.setLastName("Weston");
        ownerService.save(michael);

        System.out.println("Loaded owners");

        Vet sam = new Vet();
        sam.setId(1L);
        sam.setFirstName("Sam");
        sam.setLastName("Axe");
        vetService.save(sam);

        Vet james = new Vet();
        james.setId(2L);
        james.setFirstName("James");
        james.setLastName("Stone");
        vetService.save(james);

        System.out.println("Loaded Vets");

    }
}
