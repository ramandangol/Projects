/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mainPackage.Pannels.Entry;

import com.mainPackage.MainProgram;
import com.mainPackage.Pannels.Dashboard;
import dao.PurEntryDao;
import dao.PurReceiptDao;
import dao.PurRimStockDao;
import dao.PurchaseRimDao;
import dao.RimStockDetailDao;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.PurEntryModel;
import model.PurRimModel;
import utills.DbConnection;
import java.util.List;
import java.util.Iterator;
import model.PurReceiptModel;
import model.PurRimStockModel;
import model.RimStockDetailModel;

/**
 *
 * @author Bladestorm
 */
public class PurchaseRim extends javax.swing.JPanel {

    /**
     * Creates new form PurchaseRim
     */
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    //raman dangol

    //Variable to Store user data
    String sdate, sbillno, scompanyname, saddress, sphone, snetamt, svat, stotal, srimsize,srimtype, srimno, srimprice, sgsm, samt;
    BigDecimal bg_rimprice, bg_amt, bg_total, bg_vat, bg_netamt;

    int nbillno, nvat, nrimno, nrimprice, ngsm;
    Date nDate;

    //DataBase Connection
    //Rim Purchase
    PurchaseRimDao purchaserimdao = new PurchaseRimDao();
    PurRimModel purchaserimmodel = new PurRimModel();

    //Purchae Entry
    PurEntryDao purchasedao = new PurEntryDao();
    PurEntryModel purchasemodel = new PurEntryModel();

    //Purchase Receipt for Ledger
    PurReceiptDao purreceiptdao = new PurReceiptDao();
    PurReceiptModel purreceiptmodel = new PurReceiptModel();

    //Purchase Rim Stock
    PurRimStockDao rimstock = new PurRimStockDao();
    PurRimStockModel rimmodel = new PurRimStockModel();
    
    //RimStock Details
    RimStockDetailDao rimstockdetaildao = new RimStockDetailDao();
    RimStockDetailModel rimstockdetail = new RimStockDetailModel();

    DbConnection db = new DbConnection();

    public PurchaseRim() {
        purchaserimdao.CreateTable();
        purchasedao.CreateTable();
        purreceiptdao.createTable();
        rimstock.createTable();
        rimstockdetaildao.CreateTable();
        initComponents();

        txtDate.setText(dateFormat.format(date));

        txtVat.setText("0");

        //Company name list
        List<String> a = purchasedao.getCompanyName();
        Iterator<String> ab = a.iterator();
        while (ab.hasNext()) {
            txtCompanyname.addItem(ab.next());
        }

        //Rim List
        List<String> allrimsize = purchaserimdao.getRimSize();
        Iterator<String> rimiterator = allrimsize.iterator();
        while (rimiterator.hasNext()) {
            txtRimSize.addItem(rimiterator.next());
        }

    }

//    public int getSum() {
//        int sum = 0;
//        int rowsCount = jTableRim.getRowCount();
//        for (int i = 0; i < rowsCount; i++) {
//            sum = sum + Integer.parseInt(jTableRim.getValueAt(i, 6).toString());
//        }
//        return sum;
//    }
    
    public BigDecimal getSum(){
        BigDecimal sum = new BigDecimal(0);
        int rowcount = jTableRim.getRowCount();
        for(int i = 0; i<rowcount;i++){
           String amt = jTableRim.getValueAt(i,6).toString();
            sum = sum.add(new BigDecimal(amt));
        }
        return sum;
    }

    public BigDecimal getNetAmount() {

        String sTotal = txtTotal.getText();
        String sVat = txtVat.getText();
        BigDecimal y;
        BigDecimal bgTotal = new BigDecimal(sTotal);
        BigDecimal hundred = new BigDecimal(100);
        BigDecimal bgNetAmount;
        BigDecimal bgVat;
        BigDecimal bgFixVat;
        if (sVat.isEmpty()) {
            y = new BigDecimal(0);
            bgFixVat = y.divide(hundred);
            bgNetAmount = bgFixVat.multiply(bgTotal).add(bgTotal);
            return bgNetAmount;

        } else {
            bgVat = new BigDecimal(sVat);
            bgFixVat = bgVat.divide(hundred);
            bgNetAmount = bgFixVat.multiply(bgTotal).add(bgTotal);
            return bgNetAmount;
        }

    }

    public Date getnDate() {
        sdate = txtDate.getText();
        try {
            /*final*/ nDate = dateFormat.parse(sdate);
        } catch (ParseException dateexception) {
            JOptionPane.showMessageDialog(null, "Please enter Date in the Format YYYY/MM/DD");
        }
        return nDate;
    }

    public int getnBill() {
        sbillno = txtBillno.getText();
        nbillno = Integer.parseInt(sbillno);
        return nbillno;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtBillno = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCompanyname = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtVat = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtNetAmount = new javax.swing.JTextField();
        btnSubmit = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtRimSize = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtRimPer = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtWeight = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        btnAddItems = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtRimType = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRim = new javax.swing.JTable();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/goback.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel2.setText("Date");

        txtDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDateFocusLost(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel10.setText("Bill no");

        jLabel11.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel11.setText("Company name");

        txtCompanyname.setEditable(true);
        txtCompanyname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCompanynameActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel12.setText("Address");

        jLabel13.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel13.setText("Phone no");

        jLabel14.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel14.setText("Total Amount");

        txtTotal.setEditable(false);

        jLabel15.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel15.setText("VAT");

        txtVat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtVatFocusLost(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel16.setText("Net Amount");

        txtNetAmount.setEditable(false);
        txtNetAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNetAmountFocusGained(evt);
            }
        });

        btnSubmit.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel9.setText("Calculation");

        jLabel17.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel17.setText(" Details");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(132, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCompanyname, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135))))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(txtBillno, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(txtVat, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(txtNetAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(13, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(237, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(128, 128, 128)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCompanyname, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(166, 166, 166)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(30, 30, 30)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBillno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(108, 108, 108)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(30, 30, 30)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(78, 78, 78)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(30, 30, 30)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtVat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(30, 30, 30)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNetAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(664, Short.MAX_VALUE)))
        );

        jLabel3.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel3.setText("Rim Size");

        txtRimSize.setEditable(true);
        txtRimSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRimSizeActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel4.setText("No. of Rim");

        jLabel5.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel5.setText("Rim Per Price");

        txtRimPer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRimPerFocusLost(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel6.setText("Weight (gm)");

        jLabel7.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel7.setText("Amount");

        txtAmount.setEditable(false);

        btnAddItems.setText("Add Items");
        btnAddItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemsActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel8.setText("Rim Details");

        jLabel18.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel18.setText("Rim Type");

        txtRimType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nepali", "English", "Plane" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(319, 319, 319)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRimSize, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRimType, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(289, 289, 289)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtRimPer, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(146, 146, 146)
                            .addComponent(btnAddItems)
                            .addGap(9, 9, 9)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(147, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRimSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRimType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(72, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtRimPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(59, 59, 59)
                            .addComponent(btnAddItems)))
                    .addContainerGap(39, Short.MAX_VALUE)))
        );

        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTableRim.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableRim.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.n", "Rim Size", "Rim Type", "Quantity", "Per Price", "Weight", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableRim.setRowHeight(25);
        jTableRim.setSelectionBackground(new java.awt.Color(153, 255, 153));
        jTableRim.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableRim);
        if (jTableRim.getColumnModel().getColumnCount() > 0) {
            jTableRim.getColumnModel().getColumn(0).setResizable(false);
            jTableRim.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableRim.getColumnModel().getColumn(1).setResizable(false);
            jTableRim.getColumnModel().getColumn(2).setResizable(false);
            jTableRim.getColumnModel().getColumn(3).setResizable(false);
            jTableRim.getColumnModel().getColumn(4).setResizable(false);
            jTableRim.getColumnModel().getColumn(5).setResizable(false);
            jTableRim.getColumnModel().getColumn(6).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(239, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        MainProgram.mainframe.mainPane.remove(this);
        MainProgram.mainframe.mainPane.add(new Dashboard());
        SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);

    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        sdate = txtDate.getText();
        //Pattern pattern = Pattern.compile("^(\\d+)*/" + "(\\d+)$");

        // Matcher matcher = pattern.matcher(sdate);
        try {
            txtNetAmount.setText(getNetAmount().setScale(2, RoundingMode.DOWN).toString());
        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Enter vat");
        }

        sbillno = txtBillno.getText();
        Object ocompanyname = txtCompanyname.getSelectedItem();
        scompanyname = ocompanyname.toString();
        saddress = txtAddress.getText();
        sphone = txtPhone.getText();
        stotal = txtTotal.getText();

        svat = txtVat.getText();

        snetamt = txtNetAmount.getText();

        if (sdate.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Date is Empty !!");
        } else if (!sdate.matches("\\d{4}/\\d{1,2}/\\d{1,2}")) {
            JOptionPane.showMessageDialog(null, "Date not valid date should be YYYY/MM/DD");
        } else if (sbillno.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bill no is Empty !! ");
        } else if (!sbillno.matches("[0-9_]+")) {
            JOptionPane.showMessageDialog(null, "Enter digits in bill no. Alphabet not valid !!");
        } else if (scompanyname.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Company name is Empty !!");
        } else if (saddress.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Address is Empty !!");
        } else if (sphone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Phone number is Empty !!");
        } else if (!sphone.matches("[0-9]+")) {

            JOptionPane.showMessageDialog(null, "Enter digits in phone no !!");
        } else if (stotal.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Amount is empty, First entry rim details !!");
        } else if (snetamt.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Net Amount is empty !!");
        } else if (!snetamt.matches("[0-9._]+")) {
            JOptionPane.showMessageDialog(null, "Error Calculating !!");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Are you sure want to save data " + "\n Date :- " + sdate + "\n Bill no :-" + sbillno + "\n Company Name :- " + scompanyname + "\n Address :- " + saddress + "\n Phone no :-" + sphone + "\n Total Amount :- " + stotal + "\n VAT %" + svat + "\n Net Amount :- " + snetamt, "Saved Data", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Object companyname = txtCompanyname.getSelectedItem();
                /*final*/ scompanyname = companyname.toString();

                int rows = jTableRim.getRowCount();

                for (int row = 0; row < rows; row++) {
                    srimsize = (String) jTableRim.getValueAt(row, 1);
                    srimtype = (String) jTableRim.getValueAt(row, 2);
                    srimno = (String) jTableRim.getValueAt(row, 3);
                    int srimno1 = Integer.parseInt(srimno);
                    srimprice = (String) jTableRim.getValueAt(row, 4);
                    bg_rimprice = new BigDecimal(srimprice);
                    sgsm = (String) jTableRim.getValueAt(row, 5);
                    int sgsm1 = Integer.parseInt(sgsm);
                    samt = (String) jTableRim.getValueAt(row, 6);
                    bg_amt = new BigDecimal(samt);

                    //Purchase Rim Details
                    purchaserimmodel.setDate(getnDate());
                    purchaserimmodel.setBillno(getnBill());
                    purchaserimmodel.setCompanyname(scompanyname);
                    purchaserimmodel.setRimsize(srimsize);
                    purchaserimmodel.setQuantity(srimno1);
                    purchaserimmodel.setRimperprice(bg_rimprice);
                    purchaserimmodel.setGsm(sgsm1);
                    purchaserimmodel.setTotalprice(bg_amt);
                    purchaserimdao.insert(purchaserimmodel);

                    //Purchase Rim Stock
                    rimmodel.setRimsize(srimsize + " " + srimtype);
                    rimmodel.setQuantity(srimno1);
                    rimstock.insert(rimmodel);
                    
                    //Rim Stock Details
                    rimstockdetail.setDate(getnDate());
                    rimstockdetail.setRimsize(srimsize + " " + srimtype);
                    rimstockdetail.setIncreaserim(srimno1);
                    rimstockdetail.setDecreaserim(0);
                    rimstockdetaildao.insert(rimstockdetail);

                }
                bg_total = new BigDecimal(stotal);
                bg_vat = new BigDecimal(svat);
                bg_netamt = new BigDecimal(snetamt);
                //Purchase Entry
                purchasemodel.setDate(getnDate());
                purchasemodel.setBillno(getnBill());
                purchasemodel.setCompanyname(scompanyname);
                purchasemodel.setAddress(saddress);
                purchasemodel.setPhone(sphone);
                purchasemodel.setTotalamt(bg_total);
                purchasemodel.setVat(bg_vat);
                purchasemodel.setNetamount(bg_netamt);
                purchasedao.insert(purchasemodel);

                //Purchase Receipt for Ledger
                purreceiptmodel.setDate(getnDate());
                purreceiptmodel.setCompanyname(scompanyname);
                purreceiptmodel.setParticular(" Purchase Account ");
                purreceiptmodel.setVchtype(" Bill No ");
                purreceiptmodel.setVchno(getnBill());
                purreceiptmodel.setDebit(bg_netamt);
                purreceiptmodel.setCredit(new BigDecimal(0));
                purreceiptdao.insert(purreceiptmodel);

                JOptionPane.showMessageDialog(null, "Record saved");

                MainProgram.mainframe.mainPane.remove(this);
                MainProgram.mainframe.mainPane.add(new PurchaseRim());
                SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);
            } else {
                JOptionPane.showMessageDialog(null, "Record not saved !!");
            }

        }


    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnAddItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemsActionPerformed
        // TODO add your handling code here:

        try {

            Object rimsize = txtRimSize.getSelectedItem();
            srimsize = rimsize.toString();
            
            Object rimtype =txtRimType.getSelectedItem();
            String srimtype = rimtype.toString();
            Pattern pattern = Pattern.compile("^(\\d+)*x" + "(\\d+)$");

            Matcher matcher = pattern.matcher(srimsize);

            //Validation rim size
            if (srimsize.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Rim size is Empty !!");
            } else if (!matcher.matches()) {
                JOptionPane.showMessageDialog(null, "Invalid Rim Size Should be in 11x22 format !!");
            } //Validation No. of Rim
            else if (txtQuantity.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No of Rim is Empty !! ");
            } else if (!txtQuantity.getText().matches("[0-9_]+")) {
                JOptionPane.showMessageDialog(null, "Enter digits in No. of Rim...Alphabets not valid !!");
            } //Validation Rim per Price
            else if (txtRimPer.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Rim Per Price is Empty !! ");
            } else if (!txtRimPer.getText().matches("[0-9._]+")) {
                JOptionPane.showMessageDialog(null, "Enter digits in Rim Per Price.. Alphabets not valid !!");
            } //Validation Rim Weight
            else if (txtWeight.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Weight is Empty !! ");
            } else if (!txtWeight.getText().matches("[0-9_]+")) {
                JOptionPane.showMessageDialog(null, "Enter digits in Weight (GSM).. Alphabets not valid !!");
            } //Validation Amount
            else if (txtAmount.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Amount is Empty !! ");
            } else if (!txtAmount.getText().matches("[0-9._]+")) {
                JOptionPane.showMessageDialog(null, "Enter digits in Amount .. Alphabets not valid !!");
            } else {
                //rim quantity multiply per rim price
                srimno = txtQuantity.getText();
                srimprice = txtRimPer.getText();

                //integer
//                nrimno = Integer.parseInt(srimno);
//                nrimprice = Integer.parseInt(srimprice);

                BigDecimal a = new BigDecimal(srimno);
                BigDecimal b = new BigDecimal(srimprice);

                txtAmount.setText(a.multiply(b).toString());

                DefaultTableModel model = (DefaultTableModel) jTableRim.getModel();
                Object[] add = new Object[7];
                int i = 1;
                add[0] = i + jTableRim.getRowCount();
                add[1] = srimsize;
                add[2] = srimtype;
                add[3] = txtQuantity.getText();
                add[4] = txtRimPer.getText();
                add[5] = txtWeight.getText();
                add[6] = txtAmount.getText();
                model.addRow(add);
//                txtTotal.setText(Integer.toString(getSum()));
                    txtTotal.setText(getSum().toString());
    
                txtRimSize.setSelectedIndex(-1);
                txtQuantity.setText("");
                txtRimPer.setText("");
                txtWeight.setText("");
                txtAmount.setText("");
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Fields are empty !! ");
        }


    }//GEN-LAST:event_btnAddItemsActionPerformed

    private void txtRimSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRimSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRimSizeActionPerformed

    private void txtRimPerFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRimPerFocusLost
        // TODO add your handling code here:
        srimno = txtQuantity.getText();
        srimprice = txtRimPer.getText();

        //integer
//        nrimno = Integer.parseInt(srimno);
//        nrimprice = Integer.parseInt(srimprice);

        BigDecimal a = new BigDecimal(srimno);
        BigDecimal b = new BigDecimal(srimprice);

        txtAmount.setText(a.multiply(b).toString());
    }//GEN-LAST:event_txtRimPerFocusLost

    private void txtVatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVatFocusLost
        // TODO add your handling code here:

//        try {
//            txtNetAmount.setText(getNetAmount().setScale(2, RoundingMode.DOWN).toString());
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Enter vat");
//        }
    }//GEN-LAST:event_txtVatFocusLost

    private void txtDateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDateFocusLost
        // TODO add your handling code here:
        sdate = txtDate.getText();
        try {
            /*final*/ nDate = dateFormat.parse(sdate);
        } catch (ParseException dateexception) {
            JOptionPane.showMessageDialog(null, "Please enter Date in the Format YYYY/MM/DD");
        }

    }//GEN-LAST:event_txtDateFocusLost

    private void txtNetAmountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNetAmountFocusGained
        // TODO add your handling code here:
        try {
            txtNetAmount.setText(getNetAmount().setScale(2, RoundingMode.DOWN).toString());
        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Enter vat");
        }
    }//GEN-LAST:event_txtNetAmountFocusGained

    private void txtCompanynameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCompanynameActionPerformed
        // TODO add your handling code here:
//        Object companayname = txtCompanyname.getSelectedItem();
//        String scompanyname = companayname.toString();
//        if(scompanyname.equals("ram")){
//            txtAddress.setText("abc");
//            txtPhone.setText("111");
//        }else{
//            txtAddress.setText("");
//            txtPhone.setText("");
//        }
    }//GEN-LAST:event_txtCompanynameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddItems;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableRim;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtBillno;
    private javax.swing.JComboBox<String> txtCompanyname;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtNetAmount;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtRimPer;
    private javax.swing.JComboBox<String> txtRimSize;
    private javax.swing.JComboBox<String> txtRimType;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtVat;
    private javax.swing.JTextField txtWeight;
    // End of variables declaration//GEN-END:variables
}
