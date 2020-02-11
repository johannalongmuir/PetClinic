package com.longmuir.petclinic.services.map;

import com.longmuir.petclinic.model.Owner;
import com.longmuir.petclinic.services.OwnerService;

import java.util.Set;

public class OwnerServiceMapImpl extends AbstractMapServices<Owner, Long> implements OwnerService {

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner save(Owner object) {
        return super.save(object.getId(), object);
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
        // TODO sort this
        return null;
    }
}
