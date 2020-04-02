package com.longmuir.petclinic.services.map;

import com.longmuir.petclinic.model.Pet;
import com.longmuir.petclinic.services.PetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PetMapServiceImpl extends AbstractMapServices<Pet, Long> implements PetService {
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
        return super.save(object);
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
