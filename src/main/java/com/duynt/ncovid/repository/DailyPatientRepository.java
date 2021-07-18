package com.duynt.ncovid.repository;

import com.duynt.ncovid.entity.DailyPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyPatientRepository extends JpaRepository<DailyPatient, Integer> {
}
