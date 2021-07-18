package com.duynt.ncovid.services.serviceImpl;

import com.duynt.ncovid.common.Constant;
import com.duynt.ncovid.common.Utils;
import com.duynt.ncovid.entity.Patient;
import com.duynt.ncovid.repository.PatientRepository;
import com.duynt.ncovid.services.GetLastInfoAboutCovid;
import javassist.NotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//    @Scheduled(fixedDelay = 86400)
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
        String updateFlag = "1";
        String nowDate = Utils.getNowDate();
        String nowTime = Utils.getNowTime();
        boolean isExist = false;
        for (int i = 0; i < listAllPages.size() + 1; i++) {
            Elements elements = document.select("#sailorTable > tbody > tr");
            if (elements == null || elements.size() == 0) {
                continue;
            }
            Patient patient = null;
            for (Element element : elements) {
                Elements patientInfo = element.select("td");
                if (patientInfo.size() < 5) {
                    continue;
                }
                if (isPatientExistInDb(patientInfo)) {
                    isExist = true;
                    break;
                }
                int age = !"".equals(patientInfo.get(1).text()) ? Integer.parseInt(patientInfo.get(1).text()) : 0;
                patient = new Patient(patientInfo.get(0).text(), age, patientInfo.get(2).text(), patientInfo.get(3).text(), patientInfo.get(4).text(), nowTime, nowDate, updateFlag);
                listPatients.add(patient);
                updateFlag = "0";
            }
            if (isExist) {
                break;
            }
            if (i == listAllPages.size()) {
                continue;
            }
            document = Jsoup.connect(listAllPages.get(i)).sslSocketFactory(CreateSslSocketFactory.getSslSocketFactory()).get();
        }
        return listPatients;
    }

    /**
     * Check patient existence taken from document in DB
     *
     * @param patientInfo Patient information from document
     * @return true : exist patient in db
     */
    private boolean isPatientExistInDb(Elements patientInfo) {
        String patientName = Utils.nullToBlank(patientRepository.findByUpdateFlag());
        if (patientName.equals(patientInfo.get(0).text())) {
            return true;
        }
        return false;
    }
}
