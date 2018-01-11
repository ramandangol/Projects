package dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import utills.DbConnection;
import model.ItemStockModel;

public class ItemStockDao {

    DbConnection dataCon = new DbConnection();
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    boolean check = false;

    public ItemStockDao() {

    }

    public void createTable() {

        try {
            stmt = dataCon.cn.createStatement();
            stmt.execute("create table if not exists itemstock (id int primary key auto_increment not null,itemname varchar(255),quantity decimal(8,2))");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean update(ItemStockModel itemmodel) {
        boolean check = false;
        try {
            pstmt = dataCon.cn.prepareStatement("select quantity from itemstock where itemname=?");
            pstmt.setString(1, itemmodel.getItemname());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                BigDecimal i = rs.getBigDecimal(1);
                BigDecimal j = itemmodel.getQuantity();
                BigDecimal k = i.subtract(j);
                pstmt = dataCon.cn.prepareStatement("update itemstock set quantity=? where itemname=?");
                pstmt.setBigDecimal(1, k);
                pstmt.setString(2, itemmodel.getItemname());
                int ii = pstmt.executeUpdate();
                if (ii > 0) {
                    check = true;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }

    public boolean insert(ItemStockModel itemmodel) {
        boolean duplicate = false;
        try {
            BigDecimal set = null;
            List<ItemStockModel> modellist = selectAll();
            Iterator<ItemStockModel> iterator = modellist.iterator();
            while (iterator.hasNext()) {
                ItemStockModel model = iterator.next();
                if (itemmodel.getItemname().equals(model.getItemname())) {
                    duplicate = true;
                    BigDecimal update = model.getQuantity(); //2.20
                    BigDecimal fractionalpart = update.remainder(new BigDecimal("1")); //0.20
                    BigDecimal integralpart = update.subtract(fractionalpart); //2.00
                    BigDecimal a = itemmodel.getQuantity(); //2.20
                    BigDecimal b = a.remainder(new BigDecimal("1")); //0.20
                    BigDecimal c = a.subtract(b); //2.00
                    BigDecimal whole = fractionalpart.add(b); //0.20+0.20= 0.40
                    BigDecimal l = integralpart.add(c); //2.00+2.00 = 4.00
                    int xy = whole.compareTo(new BigDecimal("0.12"));//0.40 compare to 0.12
                    if (xy < 0) {
                        set = l.add(whole); //
                    } else if (xy == 0) {
                        set = l.add(new BigDecimal("1"));
                    } else {
                        BigDecimal yz = whole.subtract(new BigDecimal("0.12"));
                        set = (l.add(new BigDecimal("1"))).add(yz);
                    }
                }
            }

            if (duplicate) {
                pstmt = dataCon.cn.prepareStatement("update itemstock set quantity=? where itemname=?");
                pstmt.setBigDecimal(1, set);
                pstmt.setString(2, itemmodel.getItemname());
                int i = pstmt.executeUpdate();
                if (i > 0) {
                    check = true;
                }

            } else {
                pstmt = dataCon.cn.prepareStatement("insert into itemstock (itemname,quantity) values(?,?)");
                pstmt.setString(1, itemmodel.getItemname());
                pstmt.setBigDecimal(2, itemmodel.getQuantity());
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

    public List<ItemStockModel> selectAll() {
        List<ItemStockModel> stocklist = new ArrayList<ItemStockModel>();
        try {
            stmt = dataCon.cn.createStatement();
            rs = stmt.executeQuery("select * from itemstock");
            while (rs.next()) {
                ItemStockModel itemmodel = new ItemStockModel();
                itemmodel.setItemname(rs.getString(2));
                itemmodel.setQuantity(rs.getBigDecimal(3));
                stocklist.add(itemmodel);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return stocklist;

    }

    public List<String> getAlItems() {
        List<String> itemlist = new ArrayList<String>();
        try {
            stmt = dataCon.cn.createStatement();
            rs = stmt.executeQuery("select * from itemstock where quantity>0");
            while (rs.next()) {
                itemlist.add(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return itemlist;

    }

    public boolean ItemStockCheck(String citem, BigDecimal cqty) {

        boolean check = false;
        try {
            pstmt = dataCon.cn.prepareStatement("Select * from itemstock where itemname=? and quantity>=?");
            pstmt.setString(1, citem);
            pstmt.setBigDecimal(2, cqty);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (Exception e) {
            check = false;
        }
        return check;
    }

    //Item Decrease
    public boolean itemDecrease(ItemStockModel itemmodel) {
        boolean duplicate = false;
        try {
            BigDecimal set = null;
            List<ItemStockModel> modellist = selectAll();
            Iterator<ItemStockModel> iterator = modellist.iterator();
            while (iterator.hasNext()) {
                ItemStockModel model = iterator.next();
                if (itemmodel.getItemname().equals(model.getItemname())) {
                    duplicate = true;
                    set = model.getQuantity().subtract(itemmodel.getQuantity());

                }
            }

            if (duplicate) {
                pstmt = dataCon.cn.prepareStatement("update itemstock set quantity=? where itemname=?");
                pstmt.setBigDecimal(1, set);
                pstmt.setString(2, itemmodel.getItemname());
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

    public boolean damageDecrease(ItemStockModel itemmodel) {
        boolean duplicate = false;
        try {
            BigDecimal set = null;
            List<ItemStockModel> modellist = selectAll();
            Iterator<ItemStockModel> iterator = modellist.iterator();
            while (iterator.hasNext()) {
                ItemStockModel model = iterator.next();
                if (itemmodel.getItemname().equals(model.getItemname())) {
                    duplicate = true;

                    BigDecimal bg_num1 = model.getQuantity();
                    BigDecimal bg_num2 = itemmodel.getQuantity();
                    //10.11
                    BigDecimal frac1 = bg_num1.remainder(new BigDecimal("1")); //0.11
                    BigDecimal integral1 = bg_num1.subtract(frac1); //10.00
                    BigDecimal integralfirstpiece = integral1.multiply(new BigDecimal("12")); //10*12 =120
                    BigDecimal mulhund = frac1.multiply(new BigDecimal("100")); //0.11=11
                    BigDecimal addpiece1st = integralfirstpiece.add(mulhund); //120+11=131 piece
                    BigDecimal decValueFirst1 = addpiece1st.multiply(new BigDecimal("8.33")); //131*8.33 =1091.23

                    BigDecimal frac2 = bg_num2.remainder(new BigDecimal("1")); //0.11
                    BigDecimal integral2 = bg_num2.subtract(frac2); //10.00
                    BigDecimal integralfirstpiece2 = integral2.multiply(new BigDecimal("12")); //10*12 =120
                    BigDecimal mulhund2 = frac2.multiply(new BigDecimal("100")); //0.11=11
                    BigDecimal addpiece1st2 = integralfirstpiece2.add(mulhund2); //120+11=131 piece
                    BigDecimal decValueFirst2 = addpiece1st2.multiply(new BigDecimal("8.33")); //131*8.33 =1091.23

                    BigDecimal decSub = decValueFirst1.subtract(decValueFirst2);
                    BigDecimal decDivtopiece = decSub.divide(new BigDecimal("8.33"));
                    BigDecimal twelves = new BigDecimal("12");
                    BigDecimal total = decDivtopiece.divide(twelves, 2, RoundingMode.HALF_UP);

                    BigDecimal fract = total.remainder(new BigDecimal("1")); //0.67
                    BigDecimal integ = total.subtract(fract); //8.00
                    BigDecimal hunmul = fract.multiply(new BigDecimal("100"));
                    int a = hunmul.intValue();
                    BigDecimal pointback = new BigDecimal(a);
                    BigDecimal fixedval = pointback.divide(new BigDecimal("8.33"), 2, RoundingMode.HALF_UP);
                    double d = fixedval.doubleValue();
                    double fx = Math.round(d);
                    int fixedback = (int) fx;

                    BigDecimal finalf = null;
                    if (fixedback <= 9) {
                        finalf = new BigDecimal(".0" + fixedback);
                    } else {
                        finalf = new BigDecimal("." + fixedback);
                    }
                    set = integ.add(finalf);

                }
            }

            if (duplicate) {
                pstmt = dataCon.cn.prepareStatement("update itemstock set quantity=? where itemname=?");
                pstmt.setBigDecimal(1, set);
                pstmt.setString(2, itemmodel.getItemname());
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

}
