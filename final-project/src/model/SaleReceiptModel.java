package model;

import java.math.BigDecimal;
import java.util.Date;

public class SaleReceiptModel {

    private Date date;
    private String clientname;
    private String particular;
    private String vchtype;
    private int vchno;
    private BigDecimal debit;
    private BigDecimal credit;
    private BigDecimal balance;

    public SaleReceiptModel() {
    }

    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getParticular() {
        return particular;
    }

    public void setParticular(String particular) {
        this.particular = particular;
    }

    public String getVchtype() {
        return vchtype;
    }

    public void setVchtype(String vchtype) {
        this.vchtype = vchtype;
    }

    public int getVchno() {
        return vchno;
    }

    public void setVchno(int vchno) {
        this.vchno = vchno;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
