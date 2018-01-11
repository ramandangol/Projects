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
public class ItemStockDetailsModel {
    private Date date;
    private String itemname;
    private BigDecimal increment;
    private BigDecimal decrement;

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
     * @return the itemname
     */
    public String getItemname() {
        return itemname;
    }

    /**
     * @param itemname the itemname to set
     */
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    /**
     * @return the increment
     */
    public BigDecimal getIncrement() {
        return increment;
    }

    /**
     * @param increment the increment to set
     */
    public void setIncrement(BigDecimal increment) {
        this.increment = increment;
    }

    /**
     * @return the decrement
     */
    public BigDecimal getDecrement() {
        return decrement;
    }

    /**
     * @param decrement the decrement to set
     */
    public void setDecrement(BigDecimal decrement) {
        this.decrement = decrement;
    }
    
}
