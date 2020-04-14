package com.longmuir.petclinic.bootstrap;

import com.longmuir.petclinic.model.*;
import com.longmuir.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

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




        Visit visitOne = new Visit();
        visitOne.setDate(LocalDate.of(2020, 1, 1));
        visitOne.setPet(stevesDog);
        visitOne.setDescription("Visit for: " + visitOne.getPet().getName());
        visitService.save(visitOne);

        Visit visitTwo = new Visit();
        visitTwo.setDate(LocalDate.of(2020, 1, 2));
        visitTwo.setPet(michaelsCat);
        visitTwo.setDescription("Visit for: " + visitTwo.getPet().getName());
        visitService.save(visitTwo);
        System.out.println("Loaded Visits");


        Pet dog1 = Pet.builder()
                .name("dog")
                .birthDate(LocalDate.of(1992, 6, 12))
                .petType(savedDogPetType)
                .build();

        Set<Pet> dogOwnerPets = new HashSet<>();
        dogOwnerPets.add(dog1);

        Owner dogOwner = Owner.builder()
                .firstName("Dog")
                .lastName("Owner")
                .address("Address")
                .city("City")
                .telephone("123456789")
                .pets(dogOwnerPets)
                .build();



        dog1.setOwner(dogOwner);
        ownerService.save(dogOwner);

    }
}
