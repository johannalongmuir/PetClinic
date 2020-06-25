package com.longmuir.petclinic.controllers;

import com.longmuir.petclinic.model.Owner;
import com.longmuir.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OwnerControllerTest {

    OwnerService ownerServiceMock = mock(OwnerService.class);
    OwnerController ownerController = new OwnerController(ownerServiceMock);
    MockMvc mockMvc;
    Set<Owner> owners;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().build());
        owners.add(Owner.builder().build());

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }


    @Test
    void list_Owners() throws Exception {
        when(ownerServiceMock.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void list_owners_by_index() throws Exception {
        when(ownerServiceMock.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void find_owners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notimplemented"));

        verifyNoInteractions(ownerServiceMock);

    }
}