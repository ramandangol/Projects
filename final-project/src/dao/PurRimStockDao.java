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
import model.PurRimStockModel;
import utills.DbConnection;

/**
 *
 * @author Bladestorm
 */
public class PurRimStockDao {
    DbConnection dataCon = new DbConnection();
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    boolean check = false;
    
    public void createTable() {
        try {
            stmt = dataCon.cn.createStatement();
            stmt.execute("create table if not exists rimstock (id int primary key auto_increment not null,rimsize varchar(255),quantity int)");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    //Insert or add in rim stock
    public boolean insert(PurRimStockModel rimmodel) {
        boolean duplicate = false;
        try {
            int set = 0;
            List<PurRimStockModel> rimlist = selectAll();
            Iterator<PurRimStockModel> iterator = rimlist.iterator();
            while (iterator.hasNext()) {
                PurRimStockModel model = iterator.next();
                if (rimmodel.getRimsize().equals(model.getRimsize())) {
                    duplicate = true;
                    set = model.getQuantity() + rimmodel.getQuantity();
                }
            }

            if (duplicate) {
                pstmt = dataCon.cn.prepareStatement("update rimstock set quantity=? where rimsize=?");
                pstmt.setInt(1, set);
                pstmt.setString(2, rimmodel.getRimsize());
                int i = pstmt.executeUpdate();
                if (i > 0) {
                    check = true;
                }

            } else {
                pstmt = dataCon.cn.prepareStatement("insert into rimstock (rimsize,quantity) values(?,?)");
                pstmt.setString(1, rimmodel.getRimsize());
                pstmt.setInt(2, rimmodel.getQuantity());
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
    
    
    public List<PurRimStockModel> selectAll() {
        List<PurRimStockModel> rimlist = new ArrayList<PurRimStockModel>();
        try {
            stmt = dataCon.cn.createStatement();
            rs = stmt.executeQuery("select * from rimstock");
            while (rs.next()) {
                PurRimStockModel rimmodel = new PurRimStockModel();
                rimmodel.setRimsize(rs.getString(2));
                rimmodel.setQuantity(rs.getInt(3));
                rimlist.add(rimmodel);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rimlist;

    }

    public List<String> getAllRimSizes() {
        List<String> rimlist = new ArrayList<String>();
        try {
            stmt = dataCon.cn.createStatement();
            rs = stmt.executeQuery("select * from rimstock where quantity>0");
            while (rs.next()) {
                rimlist.add(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rimlist;
    }
    
    public boolean ItemStockCheck(String sitem,int sqty){
        
        boolean check=false;
        try {
            pstmt = dataCon.cn.prepareStatement("Select * from rimstock where rimsize=? and quantity>=?");
            pstmt.setString(1, sitem);
            pstmt.setInt(2, sqty);
            rs = pstmt.executeQuery();
            if(rs.next()){
                check=true;
            }
        } catch (Exception e) {
            check = false;
        }
        return check;
    }
    
    //Manufacture and Rim decrease
    public boolean RimDecrease(PurRimStockModel rimmodel){
        boolean check=false;
        try {
            int value =0;
            List<PurRimStockModel> rimlist = selectAll();
            Iterator<PurRimStockModel> ite = rimlist.iterator();
            while(ite.hasNext()){
                PurRimStockModel model = ite.next();
                if(rimmodel.getRimsize().equals(model.getRimsize())){
                    check=true;
                    value = model.getQuantity() - rimmodel.getQuantity();
                }
            }
            if(check){
                pstmt = dataCon.cn.prepareStatement("update rimstock set quantity=? where rimsize=?");
                pstmt.setInt(1, value);
                pstmt.setString(2, rimmodel.getRimsize());
                int result = pstmt.executeUpdate();
                if(result>0){
                    check = true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }

}
