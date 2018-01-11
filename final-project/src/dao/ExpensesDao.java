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
import model.DailyBookModel;
import utills.DbConnection;

/**
 *
 * @author Bladestorm
 */
public class ExpensesDao {
    
    DbConnection dataCon = new DbConnection();
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    boolean check = false;

    public void createTable() {
        try {
            pstmt = dataCon.cn.prepareStatement("create table if not exists expensesbook (id int primary key auto_increment not null,date date,particular varchar(255),amount decimal(13,2))");
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean insertExpen(DailyBookModel dailybookmodel) {
        java.sql.Date sqldate = new java.sql.Date(dailybookmodel.getDate().getTime());
        try {
            pstmt = dataCon.cn.prepareStatement("insert into expensesbook(date,particular,amount) values(?,?,?)");
            pstmt.setDate(1, sqldate);
            pstmt.setString(2, dailybookmodel.getParticular());
            pstmt.setBigDecimal(3, dailybookmodel.getAmount());
            int result = pstmt.executeUpdate();
            if (result > 0) {
                check = true;
            }

        } catch (Exception e) {
        }
        return check;
    }

    public List<DailyBookModel> getAllExpenses(){
       List<DailyBookModel> expenseslist = new ArrayList<>();
       try {
           pstmt= dataCon.cn.prepareStatement("select * from expensesbook order by date asc");
           rs= pstmt.executeQuery();
           while (rs.next()) {               
               DailyBookModel lsmodel = new DailyBookModel();
               lsmodel.setDate(rs.getDate("date"));
               lsmodel.setParticular(rs.getString("particular"));
               lsmodel.setAmount(rs.getBigDecimal("amount"));
               expenseslist.add(lsmodel);
           }
       } catch (Exception e) {
       }
       return expenseslist;
   }
    
}
