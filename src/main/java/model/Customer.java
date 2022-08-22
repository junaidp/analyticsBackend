package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Customer {

    @Id
    private String id;
    private String jcCode;
    private String date;
    private String clDate;
    private String type;
    private String custDescription;
    private String service;
    private String amt;
    private String amtWarranty;
    private String taxAmount;
    private String totalAmount;
    private String day;
    private String month1;
    private String month;
    private String year;
    private String dt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJcCode() {
        return jcCode;
    }

    public void setJcCode(String jcCode) {
        this.jcCode = jcCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClDate() {
        return clDate;
    }

    public void setClDate(String clDate) {
        this.clDate = clDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustDescription() {
        return custDescription;
    }

    public void setCustDescription(String custDescription) {
        this.custDescription = custDescription;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getAmtWarranty() {
        return amtWarranty;
    }

    public void setAmtWarranty(String amtWarranty) {
        this.amtWarranty = amtWarranty;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth1() {
        return month1;
    }

    public void setMonth1(String month1) {
        this.month1 = month1;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
}
