package com.shahian.allergytracker.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "substitutes")
@Data
public class Substitute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    // Other substitute fields


    @ManyToMany
    @JoinTable(
            name = "substitute_allergy",
            joinColumns = @JoinColumn(name = "substitute_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id")
    )
    @JsonIgnore
    private List<Allergy> allergies;
}
