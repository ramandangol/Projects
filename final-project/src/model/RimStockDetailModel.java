/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Bladestorm
 */
public class RimStockDetailModel {
    private Date date;
    private String rimsize;
    private int increaserim;
    private int decreaserim;

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
     * @return the increaserim
     */
    public int getIncreaserim() {
        return increaserim;
    }

    /**
     * @param increaserim the increaserim to set
     */
    public void setIncreaserim(int increaserim) {
        this.increaserim = increaserim;
    }

    /**
     * @return the decreaserim
     */
    public int getDecreaserim() {
        return decreaserim;
    }

    /**
     * @param decreaserim the decreaserim to set
     */
    public void setDecreaserim(int decreaserim) {
        this.decreaserim = decreaserim;
    }
    
}
