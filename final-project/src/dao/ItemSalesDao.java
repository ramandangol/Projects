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
import model.ItemSaleModel;
import utills.DbConnection;

/**
 *
 * @author Bladestorm
 */
public class ItemSalesDao {
    DbConnection dataCon = new DbConnection();
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    boolean check = false;

    public ItemSalesDao() {

    }

    public void createTable() {
        try {
            pstmt = dataCon.cn.prepareStatement("create table if not exists itemsale (id int primary key auto_increment not null,date date,billno int,clientname varchar(255),item varchar(255),itemprice decimal(13,2),quantity int,totalprice decimal(13,2),profit decimal(13,2))");
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean insert(ItemSaleModel itemsalemodel) {
        java.sql.Date sql = new java.sql.Date(itemsalemodel.getDate().getTime());
        try {
            pstmt = dataCon.cn.prepareStatement("insert into itemsale (date,billno,clientname,item,itemprice,quantity,totalprice,profit) values(?,?,?,?,?,?,?,?)");
            pstmt.setDate(1, sql);
            pstmt.setInt(2, itemsalemodel.getBillno());
            pstmt.setString(3, itemsalemodel.getClientname());
            pstmt.setString(4, itemsalemodel.getItem());
            pstmt.setBigDecimal(5, itemsalemodel.getItemprice());
            pstmt.setInt(6, itemsalemodel.getQuantity());
            pstmt.setBigDecimal(7, itemsalemodel.getTotalprice());
            pstmt.setBigDecimal(8, itemsalemodel.getProfit());
            int result = pstmt.executeUpdate();
            if (result > 0) {
                check = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }
    
   public List<ItemSaleModel> getItemDetails(){
       List<ItemSaleModel> itemlist = new ArrayList<>();
       try {
           pstmt= dataCon.cn.prepareStatement("select * from itemsale order by date asc");
           rs = pstmt.executeQuery();
           while (rs.next()) {               
               ItemSaleModel itls = new ItemSaleModel();
               itls.setDate(rs.getDate(2));
               itls.setBillno(rs.getInt(3));
               itls.setClientname(rs.getString(4));
               itls.setItem(rs.getString(5));
               itls.setQuantity(rs.getInt(7));
               itls.setTotalprice(rs.getBigDecimal(8));
               itls.setProfit(rs.getBigDecimal(9));
               itemlist.add(itls);
           }
       } catch (Exception e) {
       }
       return itemlist;
   }
   
    
    
}
