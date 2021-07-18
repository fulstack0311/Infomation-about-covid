package com.duynt.ncovid.services.serviceImpl;

import com.duynt.ncovid.common.Constant;
import com.duynt.ncovid.common.Utils;
import com.duynt.ncovid.entity.DailyPatient;
import com.duynt.ncovid.services.GetLatestPatientOfDay;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class GetLatestPatientOfDayImpl implements GetLatestPatientOfDay {
    @Override
    public void getDailyPatientAndSaveDb() throws IOException {
        Document document = Jsoup.connect(Constant.TIMELINE).sslSocketFactory(CreateSslSocketFactory.getSslSocketFactory()).get();
        //getAllLinkDailyPatient(document);
        List<String> linkList = getAllLinkDailyPatient(document);
//        List<DailyPatient> dailyPatientList = getListDailyPatientFromLink(document, linkList);
//        for (DailyPatient patient : dailyPatientList) {
//            System.out.println(patient.toString());
//        }
//        Elements elements = document.select(".timeline-head");
//        for (Element element : elements) {
//            System.out.println(element.text());
//        }
    }

    private List<String> getAllLinkDailyPatient(Document document) throws IOException, ConnectException {
        Document newDoc = document;
        List<String> listLink = new ArrayList<String>();
        boolean checkLink = true;
        while (checkLink) {
            Element element = newDoc.select(".lfr-pagination-buttons > li > a").last();
            String url = element.attr("href");
            if (!url.startsWith("https")) {
                checkLink = false;
                continue;
            }
            listLink.add(url);
            newDoc = Jsoup.connect(url).sslSocketFactory(CreateSslSocketFactory.getSslSocketFactory()).get();
        }
        return listLink;
    }

    private List<DailyPatient> getListDailyPatientFromLink(Document document, List<String> listLink) throws IOException {
        Document newDoc = document;
        List<DailyPatient> dailyPatientList = new ArrayList<DailyPatient>();
        String firstUpdateFlag = "1";
        for (String link : listLink) {
            Elements details = newDoc.select(".timeline-detail");
            for (Element detail : details) {
                Elements head = detail.select(".timeline-head");
                String[] headArray = head.toString().split(" ");
                String createTime = Utils.formatTime(headArray[0]);
                String createDate = Utils.formatDate(headArray[1]);
                Elements content = detail.select(".timeline-content");
                dailyPatientList.add(new DailyPatient(content.get(1).text(), createDate, createTime, Utils.getNowDate(), Utils.getNowTime(), firstUpdateFlag));
                firstUpdateFlag = "0";
            }
            newDoc = Jsoup.connect(link).sslSocketFactory(CreateSslSocketFactory.getSslSocketFactory()).get();
        }
        return dailyPatientList;
    }
}
