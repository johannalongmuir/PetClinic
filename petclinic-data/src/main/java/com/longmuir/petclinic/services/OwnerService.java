package com.longmuir.petclinic.services;

import com.longmuir.petclinic.model.Owner;

public interface OwnerService extends CRUDService<Owner, Long> {

    Owner findByLastName(String lastName);

}
