package com.sassenach.backendexample.entities;

import java.sql.Date;
import java.util.ArrayList;

public class Query {

    private ArrayList<String> sensors;
    private String statisticType;
    private String metrics;
    private Date startDate;
    private Date endDate;
    
    public ArrayList<String> getSensors() {
        return sensors;
    }
    public void setSensors(ArrayList<String> sensors) {
        this.sensors = sensors;
    }
    public String getStatisticType() {
        return statisticType;
    }
    public void setStatisticType(String statisticType) {
        this.statisticType = statisticType;
    }

    public Query(ArrayList<String> sensors, String statisticType, String metrics,
            Date startDate, Date endDate) {
        this.sensors = sensors;
        this.statisticType = statisticType;
        this.metrics = metrics;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getMetrics() {
        return metrics;
    }
    public void setMetrics(String metrics) {
        this.metrics = metrics;
    }


    
    
}
