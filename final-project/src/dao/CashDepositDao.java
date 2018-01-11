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
import java.util.Iterator;
import java.util.List;
import model.CashDepositModel;
import utills.DbConnection;

/**
 *
 * @author Bladestorm
 */
public class CashDepositDao {

    DbConnection dataCon = new DbConnection();
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    boolean check = false;

    public void createTable() {
        try {
            pstmt = dataCon.cn.prepareStatement("create table if not exists cashdeposit (id int primary key auto_increment not null,particular varchar(255),amount decimal(13,2))");
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

      public List<CashDepositModel> selectAll() {
        List<CashDepositModel> cashlist = new ArrayList<CashDepositModel>();
        try {
            stmt = dataCon.cn.createStatement();
            rs = stmt.executeQuery("select * from cashdeposit");
            while (rs.next()) {
                CashDepositModel cmodel = new CashDepositModel();
                cmodel.setParticular(rs.getString(2));
                cmodel.setCash(rs.getBigDecimal(3));
                cashlist.add(cmodel);
            }
        } catch (Exception e) {
        }
        return cashlist;
    }
    
    public boolean insert(CashDepositModel cashdepositmodel) {
        boolean duplicate = false;
        try {
            BigDecimal total = new BigDecimal(0);
            List<CashDepositModel> cashlist = selectAll();
            Iterator<CashDepositModel> iter = cashlist.iterator();
            while (iter.hasNext()) {
                CashDepositModel model = iter.next();
                if (cashdepositmodel.getParticular().equals(model.getParticular())) {
                   duplicate = true;
                    total = cashdepositmodel.getCash().add(model.getCash());
                }
            }
            if (duplicate) {
                pstmt = dataCon.cn.prepareStatement("update cashdeposit set amount=? where particular=?");
               pstmt.setBigDecimal(1, total);
               pstmt.setString(2, cashdepositmodel.getParticular());
                int i = pstmt.executeUpdate();
                if (i > 0) {
                    check = true;
                }
            } 
            else {
                pstmt = dataCon.cn.prepareStatement("insert into cashdeposit (particular,amount) values(?,?)");
                pstmt.setString(1, cashdepositmodel.getParticular());
                pstmt.setBigDecimal(2, cashdepositmodel.getCash());
                int result = pstmt.executeUpdate();
                if (result > 0) {
                    check = true;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }

  
    public boolean cashDecrease(CashDepositModel cashdepositmodel) {
        boolean duplicate = false;
        try {
            BigDecimal total = new BigDecimal(0);
            List<CashDepositModel> cashlist = selectAll();
            Iterator<CashDepositModel> iter = cashlist.iterator();
            while (iter.hasNext()) {
                CashDepositModel model = iter.next();
                if (cashdepositmodel.getParticular().equals(model.getParticular())) {
                   duplicate = true;
                    total = model.getCash().subtract(cashdepositmodel.getCash());
                }
            }
            if (duplicate) {
                pstmt = dataCon.cn.prepareStatement("update cashdeposit set amount=? where particular=?");
               pstmt.setBigDecimal(1, total);
               pstmt.setString(2, cashdepositmodel.getParticular());
                int i = pstmt.executeUpdate();
                if (i > 0) {
                    check = true;
                }
            } 
           
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }

    
    public boolean cashCheck(String name,BigDecimal amount){
        boolean check=false;
        try {
            pstmt = dataCon.cn.prepareStatement("select * from cashdeposit where particular=? and amount>=?");
            pstmt.setString(1, name);
            pstmt.setBigDecimal(2, amount);
            rs = pstmt.executeQuery();
            if(rs.next()){
                check=true;
            }
        } catch (Exception e) {
        }
        return check;
    }

}
