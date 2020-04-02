package com.longmuir.petclinic.services.map;

import com.longmuir.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OwnerServiceMapImplTest {

    OwnerMapServiceImpl ownerServiceMap;
    long ownerId = 1L;
    String jones = "Jones";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerMapServiceImpl(new PetMapServiceImpl(), new PetTypeMapServiceImpl());
        // when builder set up change this to builder. Refactor this.
        Owner owner = new Owner();
        owner.setId(ownerId);
        owner.setLastName(jones);
        ownerServiceMap.save(owner);

    }

    @Test
    void save() {
        Owner owner2 = new Owner();
        Long owner2Id = 2L;
        owner2.setId(owner2Id);
        ownerServiceMap.save(owner2);

        assertEquals(ownerServiceMap.findAll().size(), 2);
        assertEquals(ownerServiceMap.findById(2L), owner2);

    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteByID(ownerId);
        assertEquals(ownerServiceMap.findAll().size(), 0);

    }

    @Test
    void delete() {


//        ownerServiceMap.delete();
    }

    @Test
    void findByLastName() {
        Owner owner2 = new Owner();
        Long owner2Id = 2L;
        owner2.setId(owner2Id);
        owner2.setLastName("smith");
        ownerServiceMap.save(owner2);

        Owner byLastName = ownerServiceMap.findByLastName(jones);
        assertEquals(jones, byLastName.getLastName());
        assertEquals(1L, byLastName.getId());
    }
}