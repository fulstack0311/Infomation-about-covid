package com.duynt.ncovid.repository;

import com.duynt.ncovid.entity.Patient;
import com.duynt.ncovid.repository.customrepository.PatientRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>, PatientRepositoryCustom {

}
