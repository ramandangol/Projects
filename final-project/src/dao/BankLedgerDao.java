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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.BankLedgerModel;
import model.DateFilterModel;
import model.PurReceiptModel;
import utills.DbConnection;

/**
 *
 * @author Bladestorm
 */
public class BankLedgerDao {
    
     DbConnection dataCon = new DbConnection();
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    boolean check = false;
    
      public void createTable() {
        try {
            pstmt = dataCon.cn.prepareStatement("create table if not exists bankledger (id int primary key auto_increment not null,date date,bankname varchar(255),particular varchar(255),debit decimal(13,2),credit decimal(13,2))");
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
      
      public boolean insert(BankLedgerModel bankledmodel){
          java.sql.Date sql = new java.sql.Date(bankledmodel.getDate().getTime());
          try {
              pstmt = dataCon.cn.prepareStatement("insert into bankledger(date,bankname,particular,debit,credit) values(?,?,?,?,?)");
              pstmt.setDate(1, sql);
              pstmt.setString(2, bankledmodel.getBankname());
              pstmt.setString(3, bankledmodel.getParticular());
              pstmt.setBigDecimal(4, bankledmodel.getDebit());
              pstmt.setBigDecimal(5, bankledmodel.getCredit());
              int result = pstmt.executeUpdate();
              if(result>0){
                  check = true;
              }
          } catch (Exception e) {
          }
          return check;
      }
      
      public List<BankLedgerModel> selectAll(String bName){
          List<BankLedgerModel> Blistmodel = new ArrayList<BankLedgerModel>();
          try {
              pstmt = dataCon.cn.prepareStatement("select * from bankledger where bankname=? order by date asc");
              pstmt.setString(1, bName);
              rs = pstmt.executeQuery();
              while (rs.next()) {                  
                  BankLedgerModel blist = new BankLedgerModel();
                  blist.setDate(rs.getDate(2));
                   blist.setParticular(rs.getString(4));
                   blist.setDebit(rs.getBigDecimal(5));
                   blist.setCredit(rs.getBigDecimal(6));
                   Blistmodel.add(blist);
                  
              }
              
          } catch (Exception e) {
          }
          return Blistmodel;
      }
      
      //Balance function
    public BigDecimal getOpenBalance(String name,Date fromDate){
        java.sql.Date sqlFromdate = new java.sql.Date(fromDate.getTime());
        BigDecimal balance = new BigDecimal(0);
        try {
            pstmt = dataCon.cn.prepareStatement("SELECT debit,credit FROM bankledger WHERE DATE <? AND bankname=?");
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
    
    public List<BankLedgerModel> getFilterbyDate(DateFilterModel datafiltermodel){
        List<BankLedgerModel> list = new ArrayList<>();
        java.sql.Date sqlfromdate = new java.sql.Date(datafiltermodel.getFromDate().getTime());
        java.sql.Date sqltodate = new java.sql.Date(datafiltermodel.getToDate().getTime());
        try {
            pstmt = dataCon.cn.prepareStatement("select * from bankledger where date>=? and date<=? and bankname=? order by date asc");
            pstmt.setDate(1, sqlfromdate);
            pstmt.setDate(2, sqltodate);
            pstmt.setString(3, datafiltermodel.getName());
            rs =pstmt.executeQuery();
            while(rs.next()){
                BankLedgerModel blist = new BankLedgerModel();
                  blist.setDate(rs.getDate(2));
                   blist.setParticular(rs.getString(4));
                   blist.setDebit(rs.getBigDecimal(5));
                   blist.setCredit(rs.getBigDecimal(6));
                   list.add(blist);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
}
