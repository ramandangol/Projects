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
public class stafLedgerModel {
    private Date date;
    private String staffname;
    private String particular;
    private BigDecimal debit;
    private BigDecimal credit;

    
    
    public stafLedgerModel(){
        
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
     * @return the staffname
     */
    public String getStaffname() {
        return staffname;
    }

    /**
     * @param staffname the staffname to set
     */
    public void setStaffname(String staffname) {
        this.staffname = staffname;
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
    
}
