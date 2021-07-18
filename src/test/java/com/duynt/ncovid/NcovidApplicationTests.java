package com.duynt.ncovid;

import com.duynt.ncovid.entity.Patient;
import com.duynt.ncovid.repository.PatientRepository;
import com.duynt.ncovid.services.GetLastInfoAboutCovid;
import com.duynt.ncovid.services.serviceImpl.GetLastInfoAboutCoividImpl;
import com.duynt.ncovid.services.serviceImpl.GetLatestPatientOfDayImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class NcovidApplicationTests {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void getCaseInfoFromPage() throws Exception {
        GetLastInfoAboutCoividImpl infoAboutCoivid = new GetLastInfoAboutCoividImpl();
        infoAboutCoivid.getCaseInfoFrPageAndSaveDb();
        System.out.println("success!");
    }

    @Test
    void getDailyPatient() throws IOException {
        GetLatestPatientOfDayImpl patientOfDay = new GetLatestPatientOfDayImpl();
        patientOfDay.getDailyPatientAndSaveDb();
    }

}
