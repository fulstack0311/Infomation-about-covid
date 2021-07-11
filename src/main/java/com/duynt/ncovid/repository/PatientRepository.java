package com.duynt.ncovid.repository;

import com.duynt.ncovid.entity.Patient;
import com.duynt.ncovid.repository.custom.PatientRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>, PatientRepositoryCustom {

    @Query("SELECT p.patient FROM Patient p WHERE p.id = (SELECT MAX(e.id) FROM Patient e WHERE e.firstUpdateFlag = '1')")
    String findByUpdateFlag();
}
