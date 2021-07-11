package com.duynt.ncovid.repository.custom;

import com.duynt.ncovid.entity.Patient;
import com.duynt.ncovid.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PatientRepositoryImpl implements PatientRepositoryCustom {

    @Autowired
    private PatientRepository repository;

    @Override
    @Transactional
    public void saveAll(List<Patient> patients) {
        try {
            for (Patient patient : patients) {
                repository.save(patient);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
}
