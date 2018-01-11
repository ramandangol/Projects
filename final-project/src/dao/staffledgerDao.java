/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Bladestorm
 */
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.stafLedgerModel;
import utills.DbConnection;
import java.util.Date;
import model.DateFilterModel;

public class staffledgerDao {
     DbConnection dataCon = new DbConnection();
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    boolean check = false;
    
    public void createTable() {
        try {
            pstmt = dataCon.cn.prepareStatement("create table if not exists staffledger (id int primary key auto_increment not null,date date,staffname varchar(255),particular varchar(255),debit decimal(13,2),credit decimal(13,2))");
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    public boolean insert(stafLedgerModel staffmodel){
        java.sql.Date sqldate = new java.sql.Date(staffmodel.getDate().getTime());
        try {
            pstmt = dataCon.cn.prepareStatement("insert into staffledger(date,staffname,particular,debit,credit) values(?,?,?,?,?)");
            pstmt.setDate(1, sqldate);
            pstmt.setString(2, staffmodel.getStaffname());
            pstmt.setString(3, staffmodel.getParticular());
            pstmt.setBigDecimal(4, staffmodel.getDebit());
            pstmt.setBigDecimal(5, staffmodel.getCredit());
            int result = pstmt.executeUpdate();
            if(result>0){
                check=true;
            }
        } catch (Exception e) {
        }
        return check;
    }
    
    
    public List<String> getStaffname(){
        List<String> staffname = new ArrayList<>();
        try {
            stmt = dataCon.cn.createStatement();
            rs = stmt.executeQuery("select distinct(staffname) from staffledger");
            while (rs.next()) {                
                staffname.add(rs.getString("staffname"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return staffname;
    }
    
    public List<stafLedgerModel> getAllStaffLedger(String name){
        List<stafLedgerModel> stafflist = new ArrayList<>();
        try {
            pstmt =dataCon.cn.prepareStatement("select *from staffledger where staffname=? order by date asc");
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            while (rs.next()) {                
                stafLedgerModel smodel = new stafLedgerModel();
                smodel.setDate(rs.getDate(2));
//                smodel.setStaffname(rs.getString(3));
                smodel.setParticular(rs.getString(4));
                smodel.setDebit(rs.getBigDecimal(5));
                smodel.setCredit(rs.getBigDecimal(6));
                stafflist.add(smodel);
            }
        } catch (Exception e) {
        }
        return stafflist;
    }
    
    //Balance function
    public BigDecimal getOpenBalance(String name,Date fromDate){
        java.sql.Date sqlFromDate = new java.sql.Date(fromDate.getTime());
        BigDecimal balance = new BigDecimal(0);
        try {
            pstmt = dataCon.cn.prepareStatement("select debit,credit from staffledger where date<? and staffname=?");
            pstmt.setDate(1, sqlFromDate);
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
    public List<stafLedgerModel> getFilterbyDate(DateFilterModel datafiltermodel) {
        List<stafLedgerModel> list = new ArrayList<>();
        java.sql.Date sqlfromdate = new java.sql.Date(datafiltermodel.getFromDate().getTime());
        java.sql.Date sqltodate = new java.sql.Date(datafiltermodel.getToDate().getTime());
        try {
            pstmt = dataCon.cn.prepareStatement("select * from staffledger where date>=? and date<=? and staffname=? order by date asc");
            pstmt.setDate(1, sqlfromdate);
            pstmt.setDate(2, sqltodate);
            pstmt.setString(3, datafiltermodel.getName());
            rs = pstmt.executeQuery();
            while (rs.next()) {
               stafLedgerModel smodel = new stafLedgerModel();
                smodel.setDate(rs.getDate(2));
//                smodel.setStaffname(rs.getString(3));
                smodel.setParticular(rs.getString(4));
                smodel.setDebit(rs.getBigDecimal(5));
                smodel.setCredit(rs.getBigDecimal(6));
                list.add(smodel);
            }
        } catch (Exception e) {
        }
        return list;
    }
}
