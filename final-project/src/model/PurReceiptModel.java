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
public class PurReceiptModel {

    private Date date;
    private String companyname;
    private String particular;
    private String vchtype;
    private int vchno;
    private BigDecimal debit;
    private BigDecimal credit;
    private BigDecimal balance;

    public PurReceiptModel() {
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
     * @return the particular
     */
    public String getParticular() {
        return particular;
    }

    /**
     * @param particular the particular to set
     */
    public void setParticular(String particular) {
        this.particular = particular;
    }

    /**
     * @return the vchtype
     */
    public String getVchtype() {
        return vchtype;
    }

    /**
     * @param vchtype the vchtype to set
     */
    public void setVchtype(String vchtype) {
        this.vchtype = vchtype;
    }

    /**
     * @return the vchno
     */
    public int getVchno() {
        return vchno;
    }

    /**
     * @param vchno the vchno to set
     */
    public void setVchno(int vchno) {
        this.vchno = vchno;
    }

    /**
     * @return the debit
     */
    public BigDecimal getDebit() {
        return debit;
    }

    /**
     * @param debit the debit to set
     */
    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    /**
     * @return the credit
     */
    public BigDecimal getCredit() {
        return credit;
    }

    /**
     * @param credit the credit to set
     */
    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    /**
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
