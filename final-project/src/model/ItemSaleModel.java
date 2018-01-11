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
public class ItemSaleModel {
	private int billno;
	private int quantity;
	private Date date;
	private String clientname;
	private String item;
	private BigDecimal itemprice;
	private BigDecimal profit;
	private BigDecimal totalprice;

    public ItemSaleModel() {
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
     * @return the item
     */
    public String getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * @return the itemprice
     */
    public BigDecimal getItemprice() {
        return itemprice;
    }

    /**
     * @param itemprice the itemprice to set
     */
    public void setItemprice(BigDecimal itemprice) {
        this.itemprice = itemprice;
    }

    /**
     * @return the profit
     */
    public BigDecimal getProfit() {
        return profit;
    }

    /**
     * @param profit the profit to set
     */
    public void setProfit(BigDecimal profit) {
        this.profit = profit;
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
