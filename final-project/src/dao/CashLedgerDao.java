/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.CashLedgerModel;
import utills.DbConnection;

/**
 *
 * @author Bladestorm
 */
public class CashLedgerDao {

    DbConnection dataCon = new DbConnection();
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    boolean check = false;

    public void createTable() {
        try {
            pstmt = dataCon.cn.prepareStatement("create table if not exists cashledger (id int primary key auto_increment not null,date date,particular varchar(255),debit decimal(13,2),credit decimal(13,2))");
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean insert(CashLedgerModel cashldmodel) {
        java.sql.Date sqlDate = new java.sql.Date(cashldmodel.getDate().getTime());
        try {
            pstmt = dataCon.cn.prepareCall("insert into cashledger(date,particular,debit,credit) values(?,?,?,?)");
            pstmt.setDate(1, sqlDate);
            pstmt.setString(2, cashldmodel.getParticular());
            pstmt.setBigDecimal(3, cashldmodel.getDebit());
            pstmt.setBigDecimal(4, cashldmodel.getCredit());
            int result = pstmt.executeUpdate();
            if (result > 0) {
                check = true;
            }
        } catch (Exception e) {
        }
        return check;
    }
    
    public List<CashLedgerModel> getCashDetails(){
         List<CashLedgerModel> cashlist = new ArrayList<>();
         try {
            pstmt = dataCon.cn.prepareStatement("select * from cashledger");
            rs = pstmt.executeQuery();
            while(rs.next()){
                CashLedgerModel lsmodel = new CashLedgerModel();
                lsmodel.setDate(rs.getDate("date"));
                lsmodel.setParticular(rs.getString("particular"));
                lsmodel.setDebit(rs.getBigDecimal("debit"));
                lsmodel.setCredit(rs.getBigDecimal("credit"));
                cashlist.add(lsmodel);
            }
        } catch (Exception e) {
        }
        return cashlist;
        
    }
    
    public static void main(String[] args) {
        CashLedgerDao ls = new CashLedgerDao();
        System.out.println(ls.getCashDetails());
    }

}
