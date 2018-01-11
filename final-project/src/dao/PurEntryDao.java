/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.sql.*;
import model.PurEntryModel;
import utills.DbConnection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bladestorm
 */
public class PurEntryDao {

    DbConnection db = new DbConnection();
    PreparedStatement pstmt;
    Statement stmt;
    ResultSet rs;
    boolean check = false;

    public void CreateTable() {
        try {
            stmt = db.cn.createStatement();
            stmt.execute("create table if not exists purchaseentry (id int primary key auto_increment not null,date date,billno int,companyname varchar(255),address varchar(255),phoneno varchar(30),totalamt decimal(13,2),vat decimal(13,2),netamount decimal(13,2))");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean insert(PurEntryModel purentrymodel) {
        Date d = new Date(purentrymodel.getDate().getTime());
        try {
            pstmt = db.cn.prepareStatement("insert into purchaseentry (date,billno,companyname,address,phoneno,totalamt,vat,netamount) values(?,?,?,?,?,?,?,?)");
            pstmt.setDate(1, d);
            pstmt.setInt(2, purentrymodel.getBillno());
            pstmt.setString(3, purentrymodel.getCompanyname());
            pstmt.setString(4, purentrymodel.getAddress());
            pstmt.setString(5, purentrymodel.getPhone());
            pstmt.setBigDecimal(6, purentrymodel.getTotalamt());
            pstmt.setBigDecimal(7, purentrymodel.getVat());
            pstmt.setBigDecimal(8, purentrymodel.getNetamount());
            int result = pstmt.executeUpdate();
            if (result > 0) {
                check = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return check;
    }

    public List<String> getCompanyName() {
        List<String> purchaselist = new ArrayList<>();
        try {
            stmt = db.cn.createStatement();
            rs = stmt.executeQuery("select distinct(companyname) from purchaseentry");
            while (rs.next()) {
                purchaselist.add(rs.getString("companyname"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return purchaselist;

    }
    
    public List<PurEntryModel> getCompanyDetails() {
		List<PurEntryModel> purchaselist = new ArrayList<>();
		try{
			stmt = db.cn.createStatement();
			rs = stmt.executeQuery("select distinct(companyname) from purchaseentry");
			while(rs.next()){
				PurEntryModel model = new PurEntryModel();
				model.setCompanyname(rs.getString("companyname"));
				pstmt =db.cn.prepareStatement("select address from purchaseentry where companyname=?");
				pstmt.setString(1, rs.getString("companyname"));
				ResultSet r=pstmt.executeQuery();
				if(r.next()){
					model.setAddress(r.getString("address"));
				}
				purchaselist.add(model);
			}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		return purchaselist;
		
	}	

}
