/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Bladestorm
 */
public class PurRimStockModel {
    private int id;
    private int quantity;
    private String rimsize;

    /**
     * @return the id
     */
    
    
    public PurRimStockModel() {
    }

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
}
