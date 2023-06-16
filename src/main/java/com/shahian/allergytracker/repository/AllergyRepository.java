package com.shahian.allergytracker.repository;

import com.shahian.allergytracker.model.entity.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy,Long> {
}
