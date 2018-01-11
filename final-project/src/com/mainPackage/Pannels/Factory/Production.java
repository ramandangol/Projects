/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mainPackage.Pannels.Factory;

import com.mainPackage.MainProgram;
import com.mainPackage.Pannels.Dashboard;
import dao.ItemStockDao;
import dao.ItemStockDetailsDao;
import dao.PurRimStockDao;
import dao.RimStockDetailDao;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import javax.swing.SwingUtilities;
import model.ItemStockModel;
import model.PurRimStockModel;
import java.util.List;
import javax.swing.JOptionPane;
import model.ItemStockDetailsModel;
import model.RimStockDetailModel;

/**
 *
 * @author Bladestorm
 */
public class Production extends javax.swing.JPanel {

    ItemStockModel model = new ItemStockModel();
    ItemStockDao itemstock = new ItemStockDao();

    PurRimStockModel mm = new PurRimStockModel();
    PurRimStockDao rimdao = new PurRimStockDao();
    
    //RimStock Details
    RimStockDetailModel rimstockdetailmodel = new RimStockDetailModel();
    RimStockDetailDao rimstockdetaildao = new RimStockDetailDao();
    
    //ItemStock Details
    ItemStockDetailsModel itemdetailmodel = new ItemStockDetailsModel();
    ItemStockDetailsDao itemdetaildao = new ItemStockDetailsDao();

    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    Date nDate;

    /**
     * Creates new form Production
     */
    public Production() {
        itemstock.createTable();
        itemdetaildao.CreateTable();
        initComponents();

        txtDate.setText(dateFormat.format(date));
        txtDate1.setText(dateFormat.format(date));
        //Items List
        List<ItemStockModel> item = itemstock.selectAll();
        Iterator<ItemStockModel> iterator = item.iterator();
        while (iterator.hasNext()) {
            ItemStockModel m = iterator.next();
            txtitem.addItem(m.getItemname());
        }

        //Damaged stock item list
        List<String> allItem1 = itemstock.getAlItems();
        Iterator<String> itemIterator1 = allItem1.iterator();
        while (itemIterator1.hasNext()) {
            txtitem1.addItem(itemIterator1.next());
        }

        List<String> allItem = rimdao.getAllRimSizes();
        Iterator<String> itemIterator = allItem.iterator();
        while (itemIterator.hasNext()) {
            txtrimsize.addItem(itemIterator.next());
        }

    }

    public Date getnDate() {
        String sdate = txtDate.getText();
        try {
            /*final*/ nDate = dateFormat.parse(sdate);
        } catch (ParseException dateexception) {
            JOptionPane.showMessageDialog(null, "Please enter Date in the Format YYYY/MM/DD");
        }
        return nDate;
    }

    public Date getnDate1() {
        String sdate = txtDate1.getText();
        try {
            /*final*/ nDate = dateFormat.parse(sdate);
        } catch (ParseException dateexception) {
            JOptionPane.showMessageDialog(null, "Please enter Date in the Format YYYY/MM/DD");
        }
        return nDate;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtsheetreq = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtrimsize = new javax.swing.JComboBox<>();
        btnManufacture = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtquantity = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtrimno = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtsheet = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtitem = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtitem1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtquantity1 = new javax.swing.JTextField();
        btnDamaged = new javax.swing.JButton();
        txtDate1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/goback.png"))); // NOI18N
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });

        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        txtsheetreq.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "500", "1000", "1500", "2000" }));

        jLabel13.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel13.setText("1 rim = sheet");

        btnManufacture.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        btnManufacture.setText("Manufacture");
        btnManufacture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManufactureActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel4.setText("Quantity (dozen)");

        jLabel3.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel3.setText("Use Rim");

        txtrimno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtrimnoFocusLost(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel12.setText("Rim Size");

        jLabel2.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel2.setText("Sheet Required");

        txtitem.setEditable(true);

        jLabel11.setFont(new java.awt.Font("Sylfaen", 0, 24)); // NOI18N
        jLabel11.setText("Manufacture");

        jLabel10.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel10.setText("Date");

        jLabel15.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel15.setText("Items");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(txtrimno, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(txtsheet, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtitem, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtrimsize, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(txtquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtsheetreq, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDate))
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(452, 452, 452)
                        .addComponent(btnManufacture, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(467, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtitem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsheet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtrimsize, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(txtsheetreq, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtrimno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtquantity, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(btnManufacture, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Manufacture", jPanel1);

        jLabel14.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel14.setText("Items");

        jLabel5.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel5.setText("Quantity (dozen)");

        txtquantity1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtquantity1ActionPerformed(evt);
            }
        });

        btnDamaged.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        btnDamaged.setText("Submit");
        btnDamaged.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDamagedActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Sylfaen", 0, 18)); // NOI18N
        jLabel16.setText("Date");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(297, 297, 297)
                        .addComponent(btnDamaged, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDate1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtquantity1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtitem1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(548, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtitem1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtquantity1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(btnDamaged, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(292, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Copy damaged", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1095, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        // TODO add your handling code here:
        MainProgram.mainframe.mainPane.remove(this);
        MainProgram.mainframe.mainPane.add(new Dashboard());
        SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);
    }//GEN-LAST:event_btnBackMouseClicked

    private void btnManufactureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManufactureActionPerformed
        // TODO add your handling code here:
        String sDate = txtDate.getText();
        Object Oitem = txtitem.getSelectedItem();
        String sitem = Oitem.toString();
        String sSheet = txtsheet.getText();
        Object orimsize = txtrimsize.getSelectedItem();
        String srimsize = orimsize.toString();
        String sRimno = txtrimno.getText();
        String sDozon = txtquantity.getText();
        Object osheet = txtsheetreq.getSelectedItem();
        String sSheetreq = osheet.toString();
        int s = Integer.parseInt(sSheetreq);

        if (sDate.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Date is Empty");
        } else if (!sDate.matches("\\d{4}/\\d{1,2}/\\d{1,2}")) {
            JOptionPane.showMessageDialog(null, "Date not valid date should be YYYY/MM/DD");
        } else if (sitem.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Item is Empty !!");
        } else if (sSheet.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sheet required !!");
        } else if (!sSheet.matches("[0-9_]+")) {
            JOptionPane.showMessageDialog(null, "No. of Sheet not valid");
        } else if (sRimno.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Number of rim is Empty !!");
        } else if (!sRimno.matches("[0-9_]+")) {
            JOptionPane.showMessageDialog(null, "Number of rim not vaild");
        } else if (!rimdao.ItemStockCheck(srimsize, new Integer(sRimno))) {
            JOptionPane.showMessageDialog(null, "Number of rim Entered is greater than stock");
        } else if (sDozon.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Quanity is Empty !!");
        } else if (!sDozon.matches("[0-9._]+")) {
            JOptionPane.showMessageDialog(null, "Quanity not vaild");
        } else {
            int a = Integer.parseInt(sSheet);
            int b = Integer.parseInt(sRimno);
            int c = (b * s) / a;

            int quotient = c / 12;
            int remaindar = c % 12;
            if (remaindar <= 9) {
                txtquantity.setText(quotient + ".0" + remaindar);
            } else {
                txtquantity.setText(quotient + "." + remaindar);
            }

            if (JOptionPane.showConfirmDialog(null, "Item : " + txtitem.getSelectedItem() + "\n Sheets Required : " + txtsheet.getText() + "\n Rim size : " + txtrimsize.getSelectedItem() + "\n Use Rim : " + txtrimno.getText() + "\n Quantity : " + txtquantity.getText() + "\n\n ARE YOU SURE", "Confirm Message", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                model.setItemname(txtitem.getSelectedItem().toString());
                model.setQuantity(new BigDecimal(txtquantity.getText()));
                itemstock.createTable();

                //decrease in rim
                mm.setRimsize(srimsize);
                mm.setQuantity(new Integer(sRimno));
                rimdao.RimDecrease(mm);
                
                //Rim Stock Details insert
                rimstockdetailmodel.setDate(getnDate());
                rimstockdetailmodel.setRimsize(srimsize);
                rimstockdetailmodel.setIncreaserim(0);
                rimstockdetailmodel.setDecreaserim(new Integer(sRimno));
                rimstockdetaildao.insert(rimstockdetailmodel);
                
                //insert item details
              itemdetailmodel.setDate(getnDate1());
              itemdetailmodel.setItemname(sitem);
              itemdetailmodel.setIncrement(new BigDecimal(sDozon));
              itemdetailmodel.setDecrement(new BigDecimal(0));
              itemdetaildao.insert(itemdetailmodel);
                
                
                if (itemstock.insert(model)) {
                    JOptionPane.showMessageDialog(null, "Manufactured");
                    

                    MainProgram.mainframe.mainPane.remove(this);
                    MainProgram.mainframe.mainPane.add(new Production());
                    SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Manufacturing cancelled");
            }
//            JOptionPane.showMessageDialog(null, "Manufactured Success");
        }
    }//GEN-LAST:event_btnManufactureActionPerformed

    private void txtrimnoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtrimnoFocusLost
        // TODO add your handling code here:
        String sSheet = txtsheet.getText();
        String srimno = txtrimno.getText();
        Object osheet = txtsheetreq.getSelectedItem();
        String sSheetreq = osheet.toString();
        int s = Integer.parseInt(sSheetreq);
        if (sSheet.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No of Sheet Required");
        } else if (!sSheet.matches("[0-9_]+")) {
            JOptionPane.showMessageDialog(null, "Not Valid Sheet");
        } else {
            int a = Integer.parseInt(sSheet);
            int b = Integer.parseInt(srimno);
            int c = (b * s) / a;

            int quotient = c / 12;
            int remaindar = c % 12;
            if (remaindar <= 9) {
                txtquantity.setText(quotient + ".0" + remaindar);
            } else {
                txtquantity.setText(quotient + "." + remaindar);
            }

        }
    }//GEN-LAST:event_txtrimnoFocusLost

    private void txtquantity1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtquantity1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtquantity1ActionPerformed

    private void btnDamagedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDamagedActionPerformed
        // TODO add your handling code here:
        Object oItem = txtitem1.getSelectedItem();
        String sitem = oItem.toString();
        String sqty = txtquantity1.getText();
        String sDate1 = txtDate1.getText();
        try {

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid");
        }
        
        if (sDate1.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Date is Empty");
        } else if (!sDate1.matches("\\d{4}/\\d{1,2}/\\d{1,2}")) {
            JOptionPane.showMessageDialog(null, "Date not valid date should be YYYY/MM/DD");
        }
       else if (sqty.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Qty Dorzon is Empty !!");
        } else if (!sqty.matches("[0-9._]+")) {
            JOptionPane.showMessageDialog(null, "Invalid qty !!");
        } else if (!sqty.matches("\\d{0,9}.\\d{2}")) {
            JOptionPane.showMessageDialog(null, "Invalid qty , qty should by 00.00 format !!");
        } else if (!sqty.isEmpty()) {
            BigDecimal bg_qty = new BigDecimal(sqty);
            if (!itemstock.ItemStockCheck(sitem, bg_qty)) {
                JOptionPane.showMessageDialog(null, "We dont have enought stock !!");
            } else {
                //to Decrease copy stock
                model.setItemname(sitem);
                model.setQuantity(new BigDecimal(sqty));
//                itemstock.itemDecrease(model);
                itemstock.damageDecrease(model);
                
                //item stock decrese details
                //insert item details
              itemdetailmodel.setDate(getnDate1());
              itemdetailmodel.setItemname(sitem);
              itemdetailmodel.setIncrement(new BigDecimal(0));
              itemdetailmodel.setDecrement(new BigDecimal(sqty));
              itemdetaildao.insert(itemdetailmodel);
                
                JOptionPane.showMessageDialog(null, "success !!");
                txtquantity1.setText("");
            }

        } else {
            JOptionPane.showMessageDialog(null, "success 22!!");
        }

    }//GEN-LAST:event_btnDamagedActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JButton btnDamaged;
    private javax.swing.JButton btnManufacture;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDate1;
    private javax.swing.JComboBox<String> txtitem;
    private javax.swing.JComboBox<String> txtitem1;
    private javax.swing.JTextField txtquantity;
    private javax.swing.JTextField txtquantity1;
    private javax.swing.JTextField txtrimno;
    private javax.swing.JComboBox<String> txtrimsize;
    private javax.swing.JTextField txtsheet;
    private javax.swing.JComboBox<String> txtsheetreq;
    // End of variables declaration//GEN-END:variables

}