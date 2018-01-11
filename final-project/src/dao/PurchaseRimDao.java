/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import utills.DbConnection;
import java.sql.*;
import model.PurRimModel;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Bladestorm
 */


public class PurchaseRimDao {
    DbConnection db = new DbConnection();
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    boolean check = false;
    
    public PurchaseRimDao(){
        
    }
    
    public void CreateTable(){
        try {
            pstmt = db.cn.prepareStatement("create table if not exists rimpurchase (id int primary key auto_increment not null,date date,billno int,companyname varchar(255),rimsize varchar(255),quantity int,rimperprice decimal(13,2),gsm int,totalprice decimal(13,2))");
        pstmt.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean insert(PurRimModel rimmodel){
        java.sql.Date sqlDate = new java.sql.Date(rimmodel.getDate().getTime());
        try {
            pstmt = db.cn.prepareStatement("insert into rimpurchase(date,billno,companyname,rimsize,quantity,rimperprice,gsm,totalprice) values(?,?,?,?,?,?,?,?)");
        pstmt.setDate(1, sqlDate);
            pstmt.setInt(2, rimmodel.getBillno());
        pstmt.setString(3, rimmodel.getCompanyname());
        pstmt.setString(4, rimmodel.getRimsize());
        pstmt.setInt(5, rimmodel.getQuantity());
        pstmt.setBigDecimal(6, rimmodel.getRimperprice());
        pstmt.setInt(7, rimmodel.getGsm());
        pstmt.setBigDecimal(8, rimmodel.getTotalprice());
        int result = pstmt.executeUpdate();
        if(result>0){
            check = true;
        }
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return check;
    }
    
    
      public List<String> getRimSize() {
        List<String> Allrimsize = new ArrayList<>();
        try {
            pstmt = db.cn.prepareStatement("select distinct(rimsize) from rimpurchase");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Allrimsize.add(rs.getString("rimsize"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Allrimsize;
    }
    
}
