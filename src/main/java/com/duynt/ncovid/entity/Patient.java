package com.duynt.ncovid.entity;

import javax.persistence.*;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String patient;

    private Integer age;

    private String address;

    private String status;

    private String country;

    @Column(name = "up_time")
    private String timeUpdate;

    @Column(name = "up_date")
    private String dayUpdate;

    @Column(name = "update_flag")
    private String firstUpdateFlag;

    public Patient() {
    }

    public Patient(String patient, Integer age, String address, String status, String country, String timeUpdate, String dayUpdate, String firstUpdateFlag) {
        this.patient = patient;
        this.age = age;
        this.address = address;
        this.status = status;
        this.country = country;
        this.timeUpdate = timeUpdate;
        this.dayUpdate = dayUpdate;
        this.firstUpdateFlag = firstUpdateFlag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstUpdateFlag() {
        return firstUpdateFlag;
    }

    public void setFirstUpdateFlag(String firstUpdateFlag) {
        this.firstUpdateFlag = firstUpdateFlag;
    }

    public String getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(String timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public String getDayUpdate() {
        return dayUpdate;
    }

    public void setDayUpdate(String dayUpdate) {
        this.dayUpdate = dayUpdate;
    }

    @Override
    public String toString() {
        return this.getPatient();
    }
}
