package com.duynt.ncovid.controllers;

import com.duynt.ncovid.services.serviceImpl.GetLastInfoAboutCoividImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private GetLastInfoAboutCoividImpl getLastInfoAboutCoivid;

    @GetMapping("/hello")
    public String hello() throws Exception {
        getLastInfoAboutCoivid.getCaseInfoFrPageAndSaveDb();
        return "hello!";
    }
}
