package com.longmuir.petclinic.services.map;

import com.longmuir.petclinic.model.Owner;
import com.longmuir.petclinic.model.Pet;
import com.longmuir.petclinic.services.OwnerService;
import com.longmuir.petclinic.services.PetService;
import com.longmuir.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Profile({"default", "map"})
@Service
public class OwnerMapServiceImpl extends AbstractMapServices<Owner, Long> implements OwnerService {

    private final PetService petService;
    private final PetTypeService petTypeService;

    public OwnerMapServiceImpl(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Owner save(Owner object) {

        // if owner not null
        if (object != null){
            // owner set has pets then want to check The pets are correct
            if (object.getPets() != null){
                // for each pet in Set, get the Pet Type + not equal null or throw exception
                object.getPets().forEach(pet -> {
                    // check has PetTypes
                    if (pet.getPetType() != null) {
                        // if id hasn't been set on PetType then need to set.
                        if(pet.getPetType().getId() == null){
                            // save that pet type to petTypeService using the Type, then set back to Pet
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("Pet type is required");
                    }
                    // Pet needs to be set in petService so similar. Get that id + use it.
                    if (pet.getId() == null){
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });

            }
            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteByID(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner findByLastName(String lastName) {
//        Set<Owner> all = this.findAll();
//        for (Owner owner: all) {
//            if (owner.getLastName().equalsIgnoreCase(lastName)){
//                return owner;
//            }
//        }

        return this.findAll().stream()
                .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }
}
