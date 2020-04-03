package com.longmuir.petclinic.services.map;

import com.longmuir.petclinic.model.Vet;
import com.longmuir.petclinic.services.SpecialtyService;
import com.longmuir.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Profile({"default", "map"})
@Service
public class VetMapServiceImpl extends AbstractMapServices<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    public VetMapServiceImpl(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

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
        // defensive code here. Make sure as nested objects that its saved.
        if(vet.getSpecialties().size() > 0){
            vet.getSpecialties().forEach(specialty -> {
                if(specialty.getId() == null){
                    specialty.setId((specialtyService.save(specialty)).getId());
                }
            });
        }
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
