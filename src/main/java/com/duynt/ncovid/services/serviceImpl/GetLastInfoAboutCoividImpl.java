package com.duynt.ncovid.services.serviceImpl;

import com.duynt.ncovid.common.Constant;
import com.duynt.ncovid.entity.Patient;
import com.duynt.ncovid.repository.PatientRepository;
import com.duynt.ncovid.services.GetLastInfoAboutCovid;
import javassist.NotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetLastInfoAboutCoividImpl implements GetLastInfoAboutCovid {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Get patient information
     *
     * @throws IOException
     */
    @Override
    public void getCaseInfoFrPageAndSaveDb() throws Exception {
        Document document = Jsoup.connect(Constant.URL_NCOV_MOH).sslSocketFactory(CreateSslSocketFactory.getSslSocketFactory()).get();
        List<String> listAllPages = getAllCaseInfoPages(document);
        List<Patient> listPatients = getListPatientFromPage(document, listAllPages);
        if (listPatients == null) {
            throw new NotFoundException("empty patient list");
        }
        patientRepository.saveAll(listPatients);
    }

    @Override
    public void getDevelopDisease() {

    }

    /**
     * get all the case infomation pages
     *
     * @param doc document of page
     * @return list link
     * @throws IOException
     */
    private List<String> getAllCaseInfoPages(Document doc) throws IOException {
        Document document = doc;
        List<String> listPages = new ArrayList<String>();
        boolean checkPage = true;
        while (checkPage) {
            Element element = document.select(".lfr-pagination-buttons > li > a").last();
            String url = element.attr("href");
            if (!url.startsWith("https")) {
                checkPage = false;
                continue;
            }
            listPages.add(url);
            document = Jsoup.connect(url).sslSocketFactory(CreateSslSocketFactory.getSslSocketFactory()).get();
        }
        return listPages;
    }

    /**
     * get patient list from page
     *
     * @param doc          document of page
     * @param listAllPages list page
     * @return list patient
     * @throws NumberFormatException
     * @throws IOException
     */
    private List<Patient> getListPatientFromPage(Document doc, List<String> listAllPages) throws NumberFormatException, IOException {
        Document document = doc;
        List<Patient> listPatients = new ArrayList<Patient>();
        for (int i = 0; i < listAllPages.size() + 1; i++) {
            Elements elements = document.select("#sailorTable > tbody > tr");
            if (elements == null || elements.size() == 0) {
                continue;
            }
            Patient patient = null;
            for (Element element : elements) {
                patient = new Patient();
                Elements patientInfo = element.select("td");
                if (patientInfo.size() < 5) {
                    continue;
                }
                patient.setPatient(patientInfo.get(0).text());
                patient.setAge(Integer.parseInt(patientInfo.get(1).text()));
                patient.setAddress(patientInfo.get(2).text());
                patient.setStatus(patientInfo.get(3).text());
                patient.setCountry(patientInfo.get(4).text());
                listPatients.add(patient);
            }
            if (i == listAllPages.size()) {
                continue;
            }
            document = Jsoup.connect(listAllPages.get(i)).sslSocketFactory(CreateSslSocketFactory.getSslSocketFactory()).get();
        }
        return listPatients;
    }
}
