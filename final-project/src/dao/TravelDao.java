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
import model.TravelExpensesDetailModel;
import utills.DbConnection;

/**
 *
 * @author Bladestorm
 */
public class TravelDao {
     DbConnection dataCon = new DbConnection();
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    boolean check = false;
    
    public TravelDao(){
        
    }
    
   public void createTable() {
        try {
            stmt = dataCon.cn.createStatement();
            stmt.execute("create table if not exists travelexpdetail (id int primary key auto_increment not null,date date,place varchar(255),expensesfor varchar(255),amt decimal(13,2))");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
   
   public boolean insert(TravelExpensesDetailModel travelmodel){
       java.sql.Date sqldate = new java.sql.Date(travelmodel.getDate().getTime());
       try {
           pstmt = dataCon.cn.prepareStatement("insert into travelexpdetail(date,place,expensesfor,amt) values(?,?,?,?)");
           pstmt.setDate(1, sqldate);
           pstmt.setString(2, travelmodel.getPlacename());
           pstmt.setString(3, travelmodel.getExpensesFor());
           pstmt.setBigDecimal(4, travelmodel.getAmount());
          int result = pstmt.executeUpdate();
          if(result>0){
              check = true;
          }
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
       return check;
   }
   
   public List<TravelExpensesDetailModel> getTravelDetail(){
       List<TravelExpensesDetailModel> travelmodel = new ArrayList<>();
       try {
           stmt = dataCon.cn.createStatement();
           rs = stmt.executeQuery("select * from travelexpdetail order by date asc");
           while (rs.next()) { 
               TravelExpensesDetailModel tlist = new TravelExpensesDetailModel();
               tlist.setDate(rs.getDate(2));
               tlist.setPlacename(rs.getString(3));
               tlist.setExpensesFor(rs.getString(4));
               tlist.setAmount(rs.getBigDecimal(5));
               travelmodel.add(tlist);
           }
           
       } catch (Exception e) {
       }
       return travelmodel;
   }
    
}
