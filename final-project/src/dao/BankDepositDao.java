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
import model.BankDepositMode;
import utills.DbConnection;

/**
 *
 * @author Bladestorm
 */
public class BankDepositDao {

    DbConnection dataCon = new DbConnection();
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    boolean check = false;

    public BankDepositDao() {

    }

    public void createTable() {
        try {
            pstmt = dataCon.cn.prepareStatement("create table if not exists bankdeposit (id int primary key auto_increment not null,bankname varchar(255),amount decimal(13,2))");
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

//    public boolean insert(BankDepositMode bankdepmodel) {
//
//        try {
//            pstmt = dataCon.cn.prepareStatement("insert into bankdeposit(bankname,amount) values(?,?)");
//            pstmt.setString(1, bankdepmodel.getBankname());
//            pstmt.setBigDecimal(2, bankdepmodel.getAmount());
//            int result = pstmt.executeUpdate();
//            if (result > 0) {
//                check = false;
//            }
//        } catch (Exception e) {
//        }
//        return check;
//    }
    
    public List<BankDepositMode> SelectAll(){
        List<BankDepositMode> banklist= new ArrayList<BankDepositMode>();
        try {
            stmt = dataCon.cn.createStatement();
            rs = stmt.executeQuery("select * from bankdeposit");
            while(rs.next()){
                BankDepositMode bankLsModel = new BankDepositMode();
                bankLsModel.setBankname(rs.getString(2));
                bankLsModel.setAmount(rs.getBigDecimal(3));
                banklist.add(bankLsModel);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return banklist;
    }
    
    //bank name if not exist in table insert data else add total amount to bank name
    public boolean bInsert(BankDepositMode bankmodel){
        boolean duplicate = false;
        try {
            BigDecimal totatAmount = new BigDecimal(0);
            List<BankDepositMode> banklist = SelectAll();
            Iterator<BankDepositMode> iterator = banklist.iterator();
            while(iterator.hasNext()){
                BankDepositMode model = iterator.next();
                if(bankmodel.getBankname().equals(model.getBankname())){
                    duplicate = true;
                    totatAmount = model.getAmount().add(bankmodel.getAmount());
                }
            }
            if(duplicate){
                pstmt = dataCon.cn.prepareStatement("update bankdeposit set amount=? where bankname=?");
                pstmt.setBigDecimal(1, totatAmount);
                pstmt.setString(2, bankmodel.getBankname());
                int result = pstmt.executeUpdate();
                if(result>0){
                    check = true;
                }
            }else{
                pstmt = dataCon.cn.prepareStatement("insert into bankdeposit(bankname,amount) values(?,?)");
                pstmt.setString(1, bankmodel.getBankname());
                pstmt.setBigDecimal(2, bankmodel.getAmount());
                int result1 =pstmt.executeUpdate();
                if(result1>0){
                    check = true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }
    
    
    //Bank amount decrease
     //bank name if not exist in table insert data else add total amount to bank name
    public boolean bankAmountDecrese(BankDepositMode bankmodel){
        boolean duplicate = false;
        try {
            BigDecimal totatAmount = new BigDecimal(0);
            List<BankDepositMode> banklist = SelectAll();
            Iterator<BankDepositMode> iterator = banklist.iterator();
            while(iterator.hasNext()){
                BankDepositMode model = iterator.next();
                if(bankmodel.getBankname().equals(model.getBankname())){
                    duplicate = true;
                    totatAmount = model.getAmount().subtract(bankmodel.getAmount());
                }
            }
            if(duplicate){
                pstmt = dataCon.cn.prepareStatement("update bankdeposit set amount=? where bankname=?");
                pstmt.setBigDecimal(1, totatAmount);
                pstmt.setString(2, bankmodel.getBankname());
                int result = pstmt.executeUpdate();
                if(result>0){
                    check = true;
                }
            }else{
                pstmt = dataCon.cn.prepareStatement("insert into bankdeposit(bankname,amount) values(?,?)");
                pstmt.setString(1, bankmodel.getBankname());
                pstmt.setBigDecimal(2, bankmodel.getAmount());
                int result1 =pstmt.executeUpdate();
                if(result1>0){
                    check = true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }
    
    //get bank name list
    public List<String> getBankname(){
        List<String> banklist = new ArrayList<String>();
        try {
            stmt = dataCon.cn.createStatement();
            rs = stmt.executeQuery("select bankname from bankdeposit");
            while (rs.next()) {                
                banklist.add(rs.getString("bankname"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return banklist;
    }
    
    //get Bank  details
    public List<BankDepositMode> getBankDetails(){
        List<BankDepositMode> bankdetail = new ArrayList<BankDepositMode>();
        try {
            stmt = dataCon.cn.createStatement();
            rs = stmt.executeQuery("select bankname , amount from bankdeposit");
            while(rs.next()){
                BankDepositMode bankmodel = new BankDepositMode();
                bankmodel.setBankname(rs.getString("bankname"));
                bankmodel.setAmount(rs.getBigDecimal("amount"));
                bankdetail.add(bankmodel);
            }
        } catch (Exception e) {
        }
        return bankdetail;
    }
    
    //check balance if balance is less can't paid
    public boolean balanceCheck(String bankname,BigDecimal amount){
        boolean check = false;
        try {
            pstmt = dataCon.cn.prepareStatement("select * from bankdeposit where bankname=? and amount>=?");
            pstmt.setString(1, bankname);
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
