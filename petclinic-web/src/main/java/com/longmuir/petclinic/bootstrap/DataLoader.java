package com.longmuir.petclinic.bootstrap;

import com.longmuir.petclinic.model.Owner;
import com.longmuir.petclinic.model.Vet;
import com.longmuir.petclinic.services.OwnerService;
import com.longmuir.petclinic.services.VetService;
import com.longmuir.petclinic.services.map.OwnerServiceMapImpl;
import com.longmuir.petclinic.services.map.VetServiceMapImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;



    public DataLoader() {
        ownerService = new OwnerServiceMapImpl();
        vetService = new VetServiceMapImpl();
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

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet1.setId(2L);
        vet1.setFirstName("James");
        vet1.setLastName("Stone");
        vetService.save(vet2);

        System.out.println("Loaded Vets");

    }
}
