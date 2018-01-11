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
public class SaleEntryModel {
    private Date date;
    private int billno;
    private String clientname;
    private String address;
    private String phone;
    private BigDecimal totalamt;
    private BigDecimal vat;
    private BigDecimal netamt;

    public SaleEntryModel() {
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
     * @return the clientname
     */
    public String getClientname() {
        return clientname;
    }

    /**
     * @param clientname the clientname to set
     */
    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the totalamt
     */
    public BigDecimal getTotalamt() {
        return totalamt;
    }

    /**
     * @param totalamt the totalamt to set
     */
    public void setTotalamt(BigDecimal totalamt) {
        this.totalamt = totalamt;
    }

    /**
     * @return the vat
     */
    public BigDecimal getVat() {
        return vat;
    }

    /**
     * @param vat the vat to set
     */
    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    /**
     * @return the netamt
     */
    public BigDecimal getNetamt() {
        return netamt;
    }

    /**
     * @param netamt the netamt to set
     */
    public void setNetamt(BigDecimal netamt) {
        this.netamt = netamt;
    }
    
}
