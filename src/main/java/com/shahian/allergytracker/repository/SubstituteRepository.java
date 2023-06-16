package com.shahian.allergytracker.repository;

import com.shahian.allergytracker.model.entity.Allergy;
import com.shahian.allergytracker.model.entity.Substitute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubstituteRepository extends JpaRepository<Substitute,Long> {
    List<Substitute> findAllByAllergies(Allergy allergy);
}
