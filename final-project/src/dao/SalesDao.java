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
import model.SaleEntryModel;
import utills.DbConnection;

/**
 *
 * @author Bladestorm
 */
public class SalesDao {
    DbConnection dataCon = new DbConnection();
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    boolean check = false;

    public SalesDao() {

    }

    public void createTable() {
        try {
            stmt = dataCon.cn.createStatement();
            stmt.execute("create table if not exists saleentry (id int primary key auto_increment not null,date date,billno int,clientname varchar(255),address varchar(255),phone varchar(10),twamt decimal(13,2),vat decimal(13,2),net decimal(13,2))");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean insert(SaleEntryModel salemodel) {
        java.sql.Date d = new java.sql.Date(salemodel.getDate().getTime());
        try {
            pstmt = dataCon.cn.prepareStatement("insert into saleentry (date,billno,clientname,address,phone,twamt,vat,net) values(?,?,?,?,?,?,?,?)");
            pstmt.setDate(1, d);
            pstmt.setInt(2, salemodel.getBillno());
            pstmt.setString(3, salemodel.getClientname());
            pstmt.setString(4, salemodel.getAddress());
            pstmt.setString(5, salemodel.getPhone());
            pstmt.setBigDecimal(6, salemodel.getTotalamt());
            pstmt.setBigDecimal(7, salemodel.getVat());
            pstmt.setBigDecimal(8, salemodel.getNetamt());

            int result = pstmt.executeUpdate();
            if (result > 0) {
                check = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }
    
       public List<String> getClientName() {
        List<String> salelist = new ArrayList<>();
        try {
            stmt = dataCon.cn.createStatement();
            rs = stmt.executeQuery("select distinct(clientname) from saleentry");
            while (rs.next()) {
                salelist.add(rs.getString("clientname"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return salelist;

    }
       
  public List<SaleEntryModel> getClientDetails() {
        List<SaleEntryModel> salelist = new ArrayList<>();
        try {
            stmt = dataCon.cn.createStatement();
            rs = stmt.executeQuery("select distinct(clientname) from saleentry");
            while (rs.next()) {
                SaleEntryModel model = new SaleEntryModel();
                model.setClientname(rs.getString("clientname"));
                pstmt = dataCon.cn.prepareStatement("select address from saleentry where clientname=?");
                pstmt.setString(1, rs.getString("clientname"));
                ResultSet r = pstmt.executeQuery();
                if (r.next()) {
                    model.setAddress(r.getString("address"));
                }
                salelist.add(model);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return salelist;

    }
	
     public List<SaleEntryModel> getsalesDetail(){
        List<SaleEntryModel> salesList = new ArrayList<>();
        try {
            pstmt = dataCon.cn.prepareStatement("select * from saleentry order by date asc");
            rs = pstmt.executeQuery();
            while (rs.next()) {                
                SaleEntryModel ls = new SaleEntryModel();
                ls.setDate(rs.getDate(2));
                ls.setBillno(rs.getInt(3));
                ls.setClientname(rs.getString(4));
                ls.setAddress(rs.getString(5));
                ls.setNetamt(rs.getBigDecimal(9));
                salesList.add(ls);
            }
        } catch (Exception e) {
        }
        return salesList;
    }  
  
  
  
  
}
