/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mainPackage.Pannels.Factory;

import com.mainPackage.MainProgram;
import com.mainPackage.Pannels.Dashboard;
import dao.ItemStockDao;
import dao.PurRimStockDao;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.ItemStockModel;
import model.PurRimStockModel;

/**
 *
 * @author Bladestorm
 */
public class Stock extends javax.swing.JPanel {

    /**
     * Creates new form Stock
     */
    PurRimStockDao rimstock = new PurRimStockDao();
    ItemStockDao stockdao = new ItemStockDao();

    public Stock() {
        initComponents();

        //Rim Stock
        DefaultTableModel model = (DefaultTableModel) tblRimStock.getModel();

        List<PurRimStockModel> rimlist = rimstock.selectAll();
        Iterator<PurRimStockModel> iterator1 = rimlist.iterator();
        int a = 1;
        while (iterator1.hasNext()) {
            PurRimStockModel rimstock = iterator1.next();
            model.addRow(new Object[]{a, rimstock.getRimsize(), rimstock.getQuantity()});
            a++;
        }
        
        //Item Stock
        DefaultTableModel model1= (DefaultTableModel) tblItemStock.getModel();
        List<ItemStockModel> itemlist = stockdao.selectAll();
        Iterator<ItemStockModel> iterator2 = itemlist.iterator();
        int b = 1;
        while (iterator2.hasNext()) {
        ItemStockModel m = iterator2.next();
        model1.addRow(new Object[]{b,m.getItemname(),m.getQuantity()});
            b++;
        }
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRimStock = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblItemStock = new javax.swing.JTable();
        btnBack1 = new javax.swing.JLabel();
        btnBack2 = new javax.swing.JLabel();
        btnBack3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnItemDetails = new javax.swing.JButton();

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/goback.png"))); // NOI18N
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tblRimStock.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblRimStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.N", "Rim Size", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRimStock.setRowHeight(25);
        tblRimStock.setRowMargin(2);
        tblRimStock.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblRimStock);
        if (tblRimStock.getColumnModel().getColumnCount() > 0) {
            tblRimStock.getColumnModel().getColumn(0).setResizable(false);
            tblRimStock.getColumnModel().getColumn(1).setResizable(false);
            tblRimStock.getColumnModel().getColumn(2).setResizable(false);
        }

        tblItemStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.N", "Item Names", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblItemStock.setRowHeight(25);
        tblItemStock.setRowMargin(2);
        tblItemStock.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblItemStock);
        if (tblItemStock.getColumnModel().getColumnCount() > 0) {
            tblItemStock.getColumnModel().getColumn(0).setResizable(false);
            tblItemStock.getColumnModel().getColumn(1).setResizable(false);
            tblItemStock.getColumnModel().getColumn(2).setResizable(false);
        }

        btnBack1.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        btnBack1.setText("Products");
        btnBack1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBack1MouseClicked(evt);
            }
        });

        btnBack2.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        btnBack2.setText("STOCK");
        btnBack2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBack2MouseClicked(evt);
            }
        });

        btnBack3.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        btnBack3.setText("Raw Materials");
        btnBack3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBack3MouseClicked(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton1.setText("Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnItemDetails.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnItemDetails.setText("Details");
        btnItemDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnItemDetailsActionPerformed(evt);
            }
        });

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
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(btnItemDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(106, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(322, 322, 322)
                .addComponent(btnBack3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(407, 407, 407))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(332, 332, 332)
                    .addComponent(btnBack2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(988, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(btnItemDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(612, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(btnBack2)
                    .addContainerGap(1163, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        // TODO add your handling code here:
        MainProgram.mainframe.mainPane.remove(this);
        MainProgram.mainframe.mainPane.add(new Dashboard());
        SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);
    }//GEN-LAST:event_btnBackMouseClicked

    private void btnBack1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBack1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBack1MouseClicked

    private void btnBack2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBack2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBack2MouseClicked

    private void btnBack3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBack3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBack3MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel model = (DefaultTableModel) tblRimStock.getModel();
             String itemname = model.getValueAt(tblRimStock.getSelectedRow(), 1).toString();
        MainProgram.mainframe.mainPane.remove(this);
        MainProgram.mainframe.mainPane.add(new RimStockDetails(itemname));
        SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Select Rim Size !!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnItemDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnItemDetailsActionPerformed
        // TODO add your handling code here:
       try {
            DefaultTableModel model1 = (DefaultTableModel) tblItemStock.getModel();
             String itemname = model1.getValueAt(tblItemStock.getSelectedRow(), 1).toString();
        MainProgram.mainframe.mainPane.remove(this);
        MainProgram.mainframe.mainPane.add(new ItemStockDetails(itemname));
        SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Select Item Row !!");
        }
        
    }//GEN-LAST:event_btnItemDetailsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnBack1;
    private javax.swing.JLabel btnBack2;
    private javax.swing.JLabel btnBack3;
    private javax.swing.JButton btnItemDetails;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblItemStock;
    private javax.swing.JTable tblRimStock;
    // End of variables declaration//GEN-END:variables
}
