package com.duynt.ncovid.services;

import java.io.IOException;

public interface GetLatestPatientOfDay {
    void getDailyPatientAndSaveDb() throws IOException;
}
