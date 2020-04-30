package com.longmuir.petclinic.services.map;

import com.longmuir.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OwnerServiceMapImplTest {

    public static final Long OWNER_ONE_ID = 1L;
    public static final Long OWNER_TWO_ID = 2L;
    OwnerMapServiceImpl ownerServiceMap;
    Owner ownerOne;
    Owner ownerTwo;
    String lastNameJones = "Jones";
    String lastNameSmith = "Smith";


    @BeforeEach
    void setUp() {
        // Not need Mokito as simple Hash map impl so can use actual classes.
        ownerServiceMap = new OwnerMapServiceImpl(new PetMapServiceImpl(), new PetTypeMapServiceImpl());
        givenOwnerOneInMap();
    }


    @Test
    void save() {
        givenOwnerTwo();
        whenSaveOwnerTwoInMap();

        assertEquals(ownerServiceMap.findAll().size(), 2);
        assertEquals(ownerServiceMap.findById(OWNER_TWO_ID), ownerTwo);

    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(OWNER_ONE_ID);
        assertEquals(OWNER_ONE_ID, owner.getId());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(OWNER_ONE_ID);
        assertEquals(ownerServiceMap.findAll().size(), 0);

    }

    @Test
    void delete() {
        Owner owner2 = new Owner();
        Long owner2Id = 2L;
        owner2.setId(owner2Id);
        ownerServiceMap.save(owner2);
        ownerServiceMap.delete(owner2);
        assertEquals(ownerServiceMap.findAll().size(), 1);
    }

    @Test
    void findByLastName() {
        Owner owner2 = new Owner();
        Long owner2Id = 2L;
        owner2.setId(owner2Id);
        owner2.setLastName("smith");
        ownerServiceMap.save(owner2);

        Owner byLastName = ownerServiceMap.findByLastName(lastNameJones);
        assertEquals(lastNameJones, byLastName.getLastName());
        assertEquals(1L, byLastName.getId());
    }




    private void givenOwnerOneInMap() {
        ownerOne = Owner.builder().lastName(lastNameJones).build();
        ownerServiceMap.save(ownerOne);

    }

    private void givenOwnerTwo() {
        ownerTwo = Owner.builder().lastName(lastNameSmith).build();
//        ownerTwo.setId(OWNER_TWO_ID);
    }

    private void whenSaveOwnerTwoInMap() {
        Owner savedOwner = ownerServiceMap.save(ownerTwo);
        System.out.println("Owner Two: " + savedOwner.getId());
    }
}