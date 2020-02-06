package com.longmuir.petclinic.services.map;

import com.longmuir.petclinic.model.Vet;
import com.longmuir.petclinic.services.CRUDService;

import java.util.Set;

public class VetServiceMapImpl extends AbstractMapServices<Vet, Long> implements CRUDService<Vet, Long> {
    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteByID(id);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
