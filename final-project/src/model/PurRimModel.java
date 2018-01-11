/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Bladestorm
 */
public class PurRimModel {
    private int id;
    private int billno;
    private int quantity;
    private int gsm;
    private Date date;
    private String companyname;
    private String rimsize;
    private BigDecimal rimperprice;
    private BigDecimal totalprice;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the billno
     */
    public int getBillno() {
        return billno;
    }

    /**
     * @param billno the billno to set
     */
    public void setBillno(int billno) {
        this.billno = billno;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the gsm
     */
    public int getGsm() {
        return gsm;
    }

    /**
     * @param gsm the gsm to set
     */
    public void setGsm(int gsm) {
        this.gsm = gsm;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the companyname
     */
    public String getCompanyname() {
        return companyname;
    }

    /**
     * @param companyname the companyname to set
     */
    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    /**
     * @return the rimsize
     */
    public String getRimsize() {
        return rimsize;
    }

    /**
     * @param rimsize the rimsize to set
     */
    public void setRimsize(String rimsize) {
        this.rimsize = rimsize;
    }

    /**
     * @return the rimperprice
     */
    public BigDecimal getRimperprice() {
        return rimperprice;
    }

    /**
     * @param rimperprice the rimperprice to set
     */
    public void setRimperprice(BigDecimal rimperprice) {
        this.rimperprice = rimperprice;
    }

    /**
     * @return the totalprice
     */
    public BigDecimal getTotalprice() {
        return totalprice;
    }

    /**
     * @param totalprice the totalprice to set
     */
    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }
    
    
}
