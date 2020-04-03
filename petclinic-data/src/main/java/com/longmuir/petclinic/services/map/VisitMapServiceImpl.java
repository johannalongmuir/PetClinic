package com.longmuir.petclinic.services.map;

import com.longmuir.petclinic.model.Visit;
import com.longmuir.petclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Profile(value = "default")
@Service
public class VisitMapServiceImpl extends AbstractMapServices<Visit, Long> implements VisitService {

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteByID(id);
    }

    @Override
    public void delete(Visit visit) {
        super.delete(visit);
    }

    @Override
    public Visit save(Visit visit) {
        // defensive coding to make sure have properly formed object at this point
        if (visit.getPet() == null || visit.getPet().getOwner() == null || visit.getPet().getId() == null
                || visit.getPet().getOwner().getId() == null){
            throw new RuntimeException("Invalid Visit");
        } else {
            visit.getPet().getVisit().add(visit);
        }
        return super.save(visit);
    }





    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}
