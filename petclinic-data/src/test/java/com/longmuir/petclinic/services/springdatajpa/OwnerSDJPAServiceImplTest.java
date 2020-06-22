package com.longmuir.petclinic.services.springdatajpa;

import com.longmuir.petclinic.model.Owner;
import com.longmuir.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OwnerSDJPAServiceImplTest {

    private final OwnerRepository ownerRepositoryMock = mock(OwnerRepository.class);
    OwnerSDJPAServiceImpl victim =  new OwnerSDJPAServiceImpl(ownerRepositoryMock);
    private static final String LAST_NAME_SMITH = "Smith";
    private static final String LAST_NAME_JONES = "Jones";
    Set<Owner> expectedOwnerSet;
    Set<Owner> resultOwnerSet;
    Owner expectedOwner;
    Owner resultOwner;


    // can add set up for expected owner to always be reassigned just in case test messes with it


    @Test
    void find_By_Id() {
        expectedOwner = Owner.builder()
                .lastName(LAST_NAME_SMITH)
                .build();

        when(ownerRepositoryMock.findById(anyLong())).thenReturn(Optional.ofNullable(expectedOwner));

        Owner result = victim.findById(1L);
        assertNotNull(result);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

        Owner result = victim.findById(1L);

        assertNull(result);
    }

    @Test
    void find_By_Last_Name() {
        givenOwnerExistsInRepoWithLastName(LAST_NAME_JONES); // refactor name? as the method also returns jones by last name search only
        whenFindByLastName(LAST_NAME_JONES);
        thenReturnedOwnerIsNotNull();
        thenReturnedOwnerLastNameEquals(LAST_NAME_JONES);
    }

    @Test
    void find_By_Last_Name_Return_Null_When_Not_In_Repo() {
        givenOwnerNotExistInRepo(LAST_NAME_JONES);
        whenFindByLastName(LAST_NAME_JONES);
        thenReturnedOwnerIsNull();
    }

    @Test
    void find_All_empty() {
        givenRepositoryContainsNoOwners();
        resultOwnerSet = victim.findAll();
        verify(ownerRepositoryMock, times(1)).findAll();
        assertEquals(0, resultOwnerSet.size());
    }

    @Test
    void find_All() {
        givenExpectedOwnerSetContainingTwoOwners();
        givenRepositoryReturnsExpectedSet();
        resultOwnerSet = victim.findAll();
        assertNotNull(resultOwnerSet);
        verify(ownerRepositoryMock, times(1)).findAll();
        assertEquals(expectedOwnerSet, resultOwnerSet);
        assertEquals(2, resultOwnerSet.size());
    }


    @Test
    void save() {
        Owner ownerToSave = Owner.builder().build();
        ownerToSave.setId(3L);

        when(ownerRepositoryMock.save(any())).thenReturn(ownerToSave);

        Owner result = victim.save(ownerToSave);

        assertNotNull(result);
        verify(ownerRepositoryMock, times(1)).save(any());
    }

    @Test
    void delete() {
        Owner ownerToDelete = Owner.builder().build();
        victim.delete(ownerToDelete);
        verify(ownerRepositoryMock).delete(any());

    }

    @Test
    void deleteById() {
        victim.deleteById(5L);
        //below default is assuming hit one time
        verify(ownerRepositoryMock).deleteById(anyLong());
    }




    private void givenExpectedOwnerSetContainingTwoOwners() {
        Owner ownerJones = Owner.builder().lastName(LAST_NAME_JONES).build();
        Owner ownerSmith = Owner.builder().lastName(LAST_NAME_SMITH).build();
        ownerJones.setId(1L);
        ownerSmith.setId(2L);
        expectedOwnerSet = new HashSet<>();
        expectedOwnerSet.add(ownerJones);
        expectedOwnerSet.add(ownerSmith);
    }

    private void givenRepositoryReturnsExpectedSet() {
        when(ownerRepositoryMock.findAll()).thenReturn(expectedOwnerSet);
    }

    private void givenRepositoryContainsNoOwners() {
        when(ownerRepositoryMock.findAll()).thenReturn(new HashSet<Owner>());
    }


    private void givenOwnerExistsInRepoWithLastName(String lastName) {
        expectedOwner = Owner.builder().lastName(lastName).build();
        when(ownerRepositoryMock.findByLastName(any())).thenReturn(expectedOwner);
    }

    private void givenOwnerNotExistInRepo(String lastNameJones) {
        expectedOwner = null;
        when(ownerRepositoryMock.findByLastName(lastNameJones)).thenReturn(null);
    }

    private void whenFindByLastName(String lastName) {
        resultOwner = victim.findByLastName(lastName);
    }



    private void thenReturnedOwnerIsNotNull() {
        assertNotNull(resultOwner);
    }

    private void thenReturnedOwnerLastNameEquals(String lastName) {
        assertEquals(lastName, resultOwner.getLastName());
    }

    private void thenReturnedOwnerIsNull() {
        assertNull(resultOwner);
    }

}