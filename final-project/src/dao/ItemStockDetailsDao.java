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
import model.ItemStockDetailsModel;
import utills.DbConnection;

/**
 *
 * @author Bladestorm
 */
public class ItemStockDetailsDao {

    DbConnection dataCon = new DbConnection();
    PreparedStatement pstmt;
    Statement stmt;
    ResultSet rs;
    boolean check = false;

    public void CreateTable() {
        try {
            stmt = dataCon.cn.createStatement();
            stmt.execute("create table if not exists itemstockdetails (id int primary key auto_increment not null,date date,itemname varchar(255),increaseitem decimal(13,2),decreaseitem decimal(13,2))");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean insert(ItemStockDetailsModel itemdetailmodel) {
        java.sql.Date sqldate = new java.sql.Date(itemdetailmodel.getDate().getTime());
        try {
            pstmt = dataCon.cn.prepareStatement("insert into itemstockdetails(date,itemname,increaseitem,decreaseitem) values(?,?,?,?)");
            pstmt.setDate(1, sqldate);
            pstmt.setString(2, itemdetailmodel.getItemname());
            pstmt.setBigDecimal(3, itemdetailmodel.getIncrement());
            pstmt.setBigDecimal(4, itemdetailmodel.getDecrement());
            int result = pstmt.executeUpdate();
            if (result > 0) {
                check = true;
            }
        } catch (Exception e) {
        }
        return check;
    }

    public List<ItemStockDetailsModel> getItemDetail(String itemname) {
        List<ItemStockDetailsModel> itemdetail = new ArrayList<>();
        try {
            pstmt = dataCon.cn.prepareStatement("select * from itemstockdetails where itemname=? order by date asc");
            pstmt.setString(1, itemname);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ItemStockDetailsModel list = new ItemStockDetailsModel();
                list.setDate(rs.getDate(2));
                list.setItemname(rs.getString(3));
                list.setIncrement(rs.getBigDecimal(4));
                list.setDecrement(rs.getBigDecimal(5));
                itemdetail.add(list);
            }
        } catch (Exception e) {
        }
        return itemdetail;
    }

}
