package com.longmuir.petclinic.services.springdatajpa;

import com.longmuir.petclinic.model.PetType;
import com.longmuir.petclinic.repositories.PetTypeRepository;
import com.longmuir.petclinic.services.PetTypeService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@Profile(value = "springdatajpa")
@Service
public class PetTypeSDJPAServiceImpl implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    @Override
    public Set<PetType> findAll() {
        Set<PetType> pets = new HashSet<>();
        petTypeRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public PetType findById(Long aLong) {
        return petTypeRepository.findById(aLong).orElse(null);
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petTypeRepository.deleteById(aLong);
    }
}
