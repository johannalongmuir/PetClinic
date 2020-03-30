package com.longmuir.petclinic.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity{

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "Visits")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visit = new HashSet<>();

}
