package com.duynt.ncovid.repository.custom;

import com.duynt.ncovid.entity.Patient;

import java.util.List;

public interface PatientRepositoryCustom {
    void saveAll(List<Patient> patients) throws Exception;
}
