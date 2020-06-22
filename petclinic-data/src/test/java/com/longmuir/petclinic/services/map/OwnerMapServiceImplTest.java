package com.longmuir.petclinic.services.map;

import com.longmuir.petclinic.model.Owner;
import com.longmuir.petclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class OwnerMapServiceImplTest {

    //TODO refactor the tests. Also Not covering the PetType and Pet

    private final PetMapServiceImpl petMapServiceMock = mock(PetMapServiceImpl.class);
    private final PetTypeMapServiceImpl petTypeMapServiceMock = mock(PetTypeMapServiceImpl.class);
    public static final Long OWNER_ONE_ID = 1L;
    public static final Long OWNER_TWO_ID = 2L;
    OwnerMapServiceImpl ownerServiceMap;
    Owner ownerOne;
    Owner ownerTwo;
    String lastNameJones = "Jones";
    String lastNameSmith = "Smith";


    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerMapServiceImpl(petMapServiceMock, petTypeMapServiceMock);
        givenOwnerOneInMap();
    }

    @Test
    void saveWithKnownId() {
        givenOwnerTwo();
        ownerTwo.setId(OWNER_TWO_ID);
        whenSaveOwnerTwoInMap();
        assertEquals(ownerServiceMap.findAll().size(), 2);
        assertEquals(ownerServiceMap.findById(OWNER_TWO_ID), ownerTwo);
    }

    @Test
    void saveWithNoId() {
        givenOwnerTwo();
        whenSaveOwnerTwoInMap();
        assertEquals(ownerServiceMap.findAll().size(), 2);
        assertEquals(ownerServiceMap.findById(OWNER_TWO_ID), ownerTwo);
    }

    @Test
    void saveWithNullObject() {
        Owner nullOwner = null;
        Owner savedOwner = ownerServiceMap.save(nullOwner);
        assertNull(savedOwner);
    }


    @Test
    void runtimeException() {
        Set<Pet> pets = new HashSet<>();
        pets.add(Pet.builder().build());
        Owner owner = Owner.builder()
                .pets(pets)
                .build();
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class, () -> ownerServiceMap.save(owner));
        assertEquals(runtimeException.getMessage(), "Pet type is required");
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

    @Test
    void findByLastNameNull() {
        Owner owner2 = new Owner();
        Long owner2Id = 2L;
        owner2.setId(owner2Id);
        owner2.setLastName("smith");
        ownerServiceMap.save(owner2);

        Owner byLastName = ownerServiceMap.findByLastName("longmuir");
        assertNull(byLastName);
    }



    @Test
    void testFindByLastName() {
        Owner byLastJones = ownerServiceMap.findByLastName(lastNameJones);
        assertEquals(ownerOne, byLastJones);
    }

    @Test
    void testFindByLastNameNotFound() {
        Owner byLastNameSmith = ownerServiceMap.findByLastName(lastNameSmith);
        assertNull(byLastNameSmith);
    }

    private void givenOwnerOneInMap() {
        ownerOne = Owner.builder().lastName(lastNameJones).build();
        ownerServiceMap.save(ownerOne);

    }

    private void givenOwnerTwo() {
        ownerTwo = Owner.builder().lastName(lastNameSmith).build();
    }

    private void whenSaveOwnerTwoInMap() {
        Owner savedOwner = ownerServiceMap.save(ownerTwo);
    }
}