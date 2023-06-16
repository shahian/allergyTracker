package com.shahian.allergytracker.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "foods")
@Data
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "foodName")
    private String foodName;

    @ManyToMany
    @JoinTable(
            name = "food_allergy",
            joinColumns = @JoinColumn(name = "food_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id")
    )
    @JsonIgnore
    private List<Allergy> allergies;
}
