package com.longmuir.petclinic.services.map;

import com.longmuir.petclinic.model.Vet;
import com.longmuir.petclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMapImpl extends AbstractMapServices<Vet, Long> implements VetService {
    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Vet vet) {
        super.delete(vet);
    }

    @Override
    public Vet save(Vet vet) {
        return super.save(vet);
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
