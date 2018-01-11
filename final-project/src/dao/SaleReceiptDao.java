package dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import model.DateFilterModel;

import model.SaleReceiptModel;
import utills.DbConnection;

public class SaleReceiptDao {

    DbConnection dataCon = new DbConnection();
    PreparedStatement pstmt;
    Statement stmt;
    ResultSet rs;
    DateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd");
    boolean check = false;

    public SaleReceiptDao() {

    }

    public void createTable() {
        try {
            stmt = dataCon.cn.createStatement();
            stmt.execute("create table if not exists salereceipt (id int primary key auto_increment not null,date date,clientname varchar(255),particular varchar(255),vchtype varchar(255),vchno int,debit decimal(13,2),credit decimal(13,2))");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean insert(SaleReceiptModel model) {
        java.sql.Date date = new java.sql.Date(model.getDate().getTime());
        try {
            pstmt = dataCon.cn.prepareStatement("insert into salereceipt (date,clientname,particular,vchtype,vchno,debit,credit) values(?,?,?,?,?,?,?)");
            pstmt.setDate(1, date);
            pstmt.setString(2, model.getClientname());
            pstmt.setString(3, model.getParticular());
            pstmt.setString(4, model.getVchtype());
            pstmt.setInt(5, model.getVchno());
            pstmt.setBigDecimal(6, model.getDebit());
            pstmt.setBigDecimal(7, model.getCredit());
            int result = pstmt.executeUpdate();
            if (result > 0) {
                check = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;

    }

    public List<SaleReceiptModel> selectAll(String c) {

        String clientname = c;
        List<SaleReceiptModel> list = new ArrayList<SaleReceiptModel>();

        try {
            pstmt = dataCon.cn.prepareStatement("select * from salereceipt where clientname=? order by date asc");
            pstmt.setString(1, clientname);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SaleReceiptModel receiptmodel = new SaleReceiptModel();
                receiptmodel.setDate(rs.getDate(2));
                receiptmodel.setParticular(rs.getString(4));
                receiptmodel.setVchtype(rs.getString(5));
                receiptmodel.setVchno(rs.getInt(6));
                receiptmodel.setDebit(rs.getBigDecimal(7));
                receiptmodel.setCredit(rs.getBigDecimal(8));
                list.add(receiptmodel);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    //Balance function
    public BigDecimal getOpenBalance(String name, Date fromDate) {
        java.sql.Date sqlFromdate = new java.sql.Date(fromDate.getTime());
        BigDecimal balance = new BigDecimal(0);
        try {
            pstmt = dataCon.cn.prepareStatement("SELECT debit,credit FROM salereceipt WHERE DATE <? AND clientname=?");
            pstmt.setDate(1, sqlFromdate);
            pstmt.setString(2, name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                balance = balance.add(rs.getBigDecimal(1).subtract(rs.getBigDecimal(2)));
            }
        } catch (Exception e) {
        }
        return balance;
    }

    //View Data From-- To Date
    public List<SaleReceiptModel> getFilterbyDate(DateFilterModel datafiltermodel) {
        List<SaleReceiptModel> list = new ArrayList<>();
        java.sql.Date sqlfromdate = new java.sql.Date(datafiltermodel.getFromDate().getTime());
        java.sql.Date sqltodate = new java.sql.Date(datafiltermodel.getToDate().getTime());
        try {
            pstmt = dataCon.cn.prepareStatement("select * from salereceipt where date>=? and date<=? and clientname=? order by date asc");
            pstmt.setDate(1, sqlfromdate);
            pstmt.setDate(2, sqltodate);
            pstmt.setString(3, datafiltermodel.getName());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SaleReceiptModel receiptmodel = new SaleReceiptModel();
                receiptmodel.setDate(rs.getDate(2));
                receiptmodel.setParticular(rs.getString(4));
                receiptmodel.setVchtype(rs.getString(5));
                receiptmodel.setVchno(rs.getInt(6));
                receiptmodel.setDebit(rs.getBigDecimal(7));
                receiptmodel.setCredit(rs.getBigDecimal(8));
                list.add(receiptmodel);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<SaleReceiptModel> getsalesDetail(){
        List<SaleReceiptModel> salesList = new ArrayList<>();
        try {
            pstmt = dataCon.cn.prepareStatement("select * from salereceipt order by date asc");
            rs = pstmt.executeQuery();
            while (rs.next()) {                
                SaleReceiptModel ls = new SaleReceiptModel();
                ls.setDate(rs.getDate(2));
                ls.setVchno(rs.getInt(6));
                ls.setClientname(rs.getString(3));
                ls.setDebit(rs.getBigDecimal(7));
                salesList.add(ls);
            }
        } catch (Exception e) {
        }
        return salesList;
    }
  
}
