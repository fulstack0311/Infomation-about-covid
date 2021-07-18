package com.duynt.ncovid.entity;

import javax.persistence.*;

@Entity
@Table(name = "daily_patient")
public class DailyPatient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "new_patient")
    private String newPatient;

    @Column(name = "create_at_date")
    private String createAtDate;

    @Column(name = "create_at_time")
    private String createAtTime;

    @Column(name = "update_at_date")
    private String updateAtDate;

    @Column(name = "update_at_time")
    private String updateAtTime;

    @Column(name = "update_flag")
    private String firstUpdateFlag;

    public DailyPatient() {
    }

    public DailyPatient(String newPatient, String createAtDate, String createAtTime, String updateAtDate, String updateAtTime, String firstUpdateFlag) {
        this.newPatient = newPatient;
        this.createAtDate = createAtDate;
        this.createAtTime = createAtTime;
        this.updateAtDate = updateAtDate;
        this.updateAtTime = updateAtTime;
        this.firstUpdateFlag = firstUpdateFlag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNewPatient() {
        return newPatient;
    }

    public void setNewPatient(String newPatient) {
        this.newPatient = newPatient;
    }

    public String getCreateAtDate() {
        return createAtDate;
    }

    public void setCreateAtDate(String createAtDate) {
        this.createAtDate = createAtDate;
    }

    public String getCreateAtTime() {
        return createAtTime;
    }

    public void setCreateAtTime(String createAtTime) {
        this.createAtTime = createAtTime;
    }

    public String getUpdateAtDate() {
        return updateAtDate;
    }

    public void setUpdateAtDate(String updateAtDate) {
        this.updateAtDate = updateAtDate;
    }

    public String getUpdateAtTime() {
        return updateAtTime;
    }

    public void setUpdateAtTime(String updateAtTime) {
        this.updateAtTime = updateAtTime;
    }

    @Override
    public String toString() {
        return "[" + this.newPatient + " ; " + this.createAtDate + " ; " + this.createAtTime + "]";
    }
}
