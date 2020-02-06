package com.longmuir.petclinic.services.map;

import com.longmuir.petclinic.model.Pet;
import com.longmuir.petclinic.services.CRUDService;

import java.util.Set;

public class PetServiceMapImpl extends AbstractMapServices<Pet, Long> implements CRUDService<Pet, Long> {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);

    }

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteByID(id);

    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }
}
