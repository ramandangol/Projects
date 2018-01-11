/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.DateFilterModel;
import model.PurReceiptModel;
import utills.DbConnection;
import java.util.Date;

/**
 *
 * @author Bladestorm
 */
public class PurReceiptDao {
    
    DbConnection dataCon = new DbConnection();
    PreparedStatement pstmt;
    Statement stmt;
    ResultSet rs;
    DateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd");
    boolean check = false;
    
    public void createTable() {
        try {
            stmt = dataCon.cn.createStatement();
            stmt.execute("create table if not exists purchasereceipt (id int primary key auto_increment not null,date date,companyname varchar(255),particular varchar(255),vchtype varchar(255),vchno int,debit decimal(13,2),credit decimal(13,2))");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    public boolean insert(PurReceiptModel model) {
        java.sql.Date date = new java.sql.Date(model.getDate().getTime());
        try {
            pstmt = dataCon.cn.prepareStatement("insert into purchasereceipt (date,companyname,particular,vchtype,vchno,debit,credit) values(?,?,?,?,?,?,?)");
            pstmt.setDate(1, date);
            pstmt.setString(2, model.getCompanyname());
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
    
    public List<PurReceiptModel> selectAll(String c) {

//        String companyname = c;
        List<PurReceiptModel> list = new ArrayList<PurReceiptModel>();

        try {
            pstmt = dataCon.cn.prepareStatement("select * from purchasereceipt where companyname=? order by date asc");
            pstmt.setString(1, c);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                PurReceiptModel receiptmodel = new PurReceiptModel();
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
    public BigDecimal getOpenBalance(String name,Date fromDate){
        java.sql.Date sqlFromdate = new java.sql.Date(fromDate.getTime());
        BigDecimal balance = new BigDecimal(0);
        try {
            pstmt = dataCon.cn.prepareStatement("SELECT debit,credit FROM purchasereceipt WHERE DATE <? AND companyname=?");
            pstmt.setDate(1, sqlFromdate);
            pstmt.setString(2, name);
            rs = pstmt.executeQuery();
            while(rs.next()){
                balance = balance.add(rs.getBigDecimal(1).subtract(rs.getBigDecimal(2)));
            }
        } catch (Exception e) {
        }
        return balance;
    }
    
    
    //View Data From-- To Date
    
    public List<PurReceiptModel> getFilterbyDate(DateFilterModel datafiltermodel){
        List<PurReceiptModel> list = new ArrayList<>();
        java.sql.Date sqlfromdate = new java.sql.Date(datafiltermodel.getFromDate().getTime());
        java.sql.Date sqltodate = new java.sql.Date(datafiltermodel.getToDate().getTime());
        try {
            pstmt = dataCon.cn.prepareStatement("select * from purchasereceipt where date>=? and date<=? and companyname=? order by date asc");
            pstmt.setDate(1, sqlfromdate);
            pstmt.setDate(2, sqltodate);
            pstmt.setString(3, datafiltermodel.getName());
            rs =pstmt.executeQuery();
            while(rs.next()){
                PurReceiptModel receiptmodel = new PurReceiptModel();
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
    
   
}
