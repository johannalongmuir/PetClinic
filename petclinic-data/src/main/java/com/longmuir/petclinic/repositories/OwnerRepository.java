package com.longmuir.petclinic.repositories;

import com.longmuir.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    // TODO refactor to return a list as maybe more than one with last name....
    Owner findByLastName(String lastname);

}
