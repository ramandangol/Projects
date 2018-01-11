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
public class TravelExpensesDetailModel {
    private Date date;
    private String placename;
    private String expensesFor;
    private BigDecimal amount;

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
     * @return the placename
     */
    public String getPlacename() {
        return placename;
    }

    /**
     * @param placename the placename to set
     */
    public void setPlacename(String placename) {
        this.placename = placename;
    }

    /**
     * @return the expensesFor
     */
    public String getExpensesFor() {
        return expensesFor;
    }

    /**
     * @param expensesFor the expensesFor to set
     */
    public void setExpensesFor(String expensesFor) {
        this.expensesFor = expensesFor;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    
}
