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
import model.RimStockDetailModel;
import utills.DbConnection;

/**
 *
 * @author Bladestorm
 */
public class RimStockDetailDao {

    DbConnection dataCon = new DbConnection();
    PreparedStatement pstmt;
    Statement stmt;
    ResultSet rs;
    boolean check = false;

    public RimStockDetailDao() {

    }

    public void CreateTable() {
        try {
            stmt = dataCon.cn.createStatement();
            stmt.execute("create table if not exists rimstockdetails (id int primary key auto_increment not null,date date,rimsize varchar(255),increaserim int,decreaserim int)");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    public boolean insert(RimStockDetailModel rimStockDetailModel){
        java.sql.Date sqlDate = new java.sql.Date(rimStockDetailModel.getDate().getTime());
        try {
            pstmt = dataCon.cn.prepareStatement("insert into rimstockdetails(date,rimsize,increaserim,decreaserim) values(?,?,?,?)");
            pstmt.setDate(1, sqlDate);
            pstmt.setString(2, rimStockDetailModel.getRimsize());
            pstmt.setInt(3, rimStockDetailModel.getIncreaserim());
            pstmt.setInt(4, rimStockDetailModel.getDecreaserim());
           int result = pstmt.executeUpdate();
           if(result>0){
               check= true;
           }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }

    //Rim Stock Details by Itemname
    public List<RimStockDetailModel> getRimDetails(String itemname){
        List<RimStockDetailModel> rimdetailmodel = new ArrayList<>();
        try {
            pstmt = dataCon.cn.prepareStatement("select * from rimstockdetails where rimsize=? order by date asc");
            pstmt.setString(1, itemname);
            rs = pstmt.executeQuery();
            while (rs.next()) {                
                RimStockDetailModel ls = new RimStockDetailModel();
                ls.setDate(rs.getDate(2));
                ls.setRimsize(rs.getString(3));
                ls.setIncreaserim(rs.getInt(4));
                ls.setDecreaserim(rs.getInt(5));
                rimdetailmodel.add(ls);
            }
        } catch (Exception e) {
        }
        return rimdetailmodel;
    }
    
}
