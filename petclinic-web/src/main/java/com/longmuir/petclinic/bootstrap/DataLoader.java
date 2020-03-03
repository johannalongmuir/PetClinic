package com.longmuir.petclinic.bootstrap;

import com.longmuir.petclinic.model.*;
import com.longmuir.petclinic.services.OwnerService;
import com.longmuir.petclinic.services.PetTypeService;
import com.longmuir.petclinic.services.SpecialtyService;
import com.longmuir.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;



    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) {
        // not want to add data multiple times. If petTypes already exist then won't re-load
        if (petTypeService.findAll().size() == 0) {
            loadData();
        }


    }

    private void loadData() {
        //Pet Types set
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatType = petTypeService.save(cat);

        PetType mouse = new PetType();
        mouse.setName("mouse");
        PetType savedMouseType = petTypeService.save(mouse);

        // Specialties Set
        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);


        // Owner Steve with Dog + Cat
        Owner steve = new Owner();
        steve.setFirstName("Steve");
        steve.setLastName("Jones");
        steve.setAddress("123 Main Road");
        steve.setCity("Manchester");
        steve.setTelephone("213456789");

        Pet stevesDog = new Pet();
        stevesDog.setName("Norman");
        stevesDog.setOwner(steve);
        stevesDog.setPetType(savedDogPetType);
        stevesDog.setBirthDate(LocalDate.of(2019, 10, 10));

        Pet stevesCat = new Pet();
        stevesCat.setName("Sandy");
        stevesCat.setOwner(steve);
        stevesCat.setPetType(savedCatType);
        stevesCat.setBirthDate(LocalDate.of(2018, 06, 05));

        steve.getPets().add(stevesDog);
        steve.getPets().add(stevesCat);
        ownerService.save(steve);


        // Owner Michael with car
        Owner michael = new Owner();
        michael.setFirstName("Michael");
        michael.setLastName("Weston");
        michael.setAddress("456 Main Road");
        michael.setCity("Manchester");
        michael.setTelephone("454124");

        Pet michaelsCat = new Pet();
        michaelsCat.setName("Cara");
        michaelsCat.setOwner(michael);
        michaelsCat.setPetType(savedCatType);
        michaelsCat.setBirthDate(LocalDate.of(2011, 1, 1));

        michael.getPets().add(michaelsCat);
        ownerService.save(michael);


        // Owner keith
        Owner keith = new Owner();
        keith.setFirstName("Keith");
        keith.setLastName("Weston");
        keith.setAddress("789 Main Road");
        keith.setCity("Manchester");
        keith.setTelephone("7862145");
        ownerService.save(keith);

        //
        System.out.println("Loaded owners");


        // Vet setup

        Vet sam = new Vet();
        sam.setFirstName("Sam");
        sam.setLastName("Axe");
        sam.getSpecialties().add(savedDentistry);
        sam.getSpecialties().add(savedRadiology);
        vetService.save(sam);

        Vet james = new Vet();
        james.setFirstName("James");
        james.setLastName("Stone");
        james.getSpecialties().add(savedSurgery);
        vetService.save(james);

        Vet sarah = new Vet();
        sarah.setFirstName("Sarah");
        sarah.setLastName("Stone");
        sarah.getSpecialties().add(savedDentistry);
        sarah.getSpecialties().add(savedRadiology);
        sarah.getSpecialties().add(savedSurgery);
        vetService.save(sarah);
        System.out.println("Loaded Vets");
    }
}
