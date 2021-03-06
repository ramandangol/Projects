/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mainPackage.Pannels.Factory;

import com.mainPackage.MainProgram;
import dao.ItemStockDetailsDao;
import dao.RimStockDetailDao;
import java.util.Iterator;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.ItemStockDetailsModel;
import model.RimStockDetailModel;

/**
 *
 * @author Bladestorm
 */
public class ItemStockDetails extends javax.swing.JPanel {

    /**
     * Creates new form RimStockDetails
     */
    //Rim Stock Details from item name
    ItemStockDetailsDao itemdetail = new ItemStockDetailsDao();

    public ItemStockDetails(String itemname) {
        initComponents();
        txtItem.setText(itemname);
        DefaultTableModel model = (DefaultTableModel) tblItemDeails.getModel();
        List<ItemStockDetailsModel> itemstockdetail = itemdetail.getItemDetail(itemname);
        Iterator<ItemStockDetailsModel> rimiter = itemstockdetail.iterator();
       int i = 1;
        while (rimiter.hasNext()) {
            ItemStockDetailsModel md = rimiter.next();
             model.addRow(new Object[]{i, md.getDate(), md.getItemname(), md.getIncrement(), md.getDecrement()});
            i++;
        }
        
        
         TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tblItemDeails.getModel());
        tblItemDeails.setRowSorter(rowSorter);
        txtFilter.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtFilter.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
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
        txtItem = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItemDeails = new javax.swing.JTable();
        txtFilter = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtItem1 = new javax.swing.JLabel();

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/goback.png"))); // NOI18N
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });

        txtItem.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N

        tblItemDeails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.N", "Date", "Item Name ", "Manufacture (Increase)", "Sales (Decrease)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblItemDeails.setRowHeight(25);
        tblItemDeails.setRowMargin(2);
        jScrollPane1.setViewportView(tblItemDeails);
        if (tblItemDeails.getColumnModel().getColumnCount() > 0) {
            tblItemDeails.getColumnModel().getColumn(0).setResizable(false);
            tblItemDeails.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblItemDeails.getColumnModel().getColumn(1).setResizable(false);
            tblItemDeails.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblItemDeails.getColumnModel().getColumn(2).setResizable(false);
            tblItemDeails.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblItemDeails.getColumnModel().getColumn(3).setResizable(false);
            tblItemDeails.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblItemDeails.getColumnModel().getColumn(4).setResizable(false);
            tblItemDeails.getColumnModel().getColumn(4).setPreferredWidth(80);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Filter Data");

        txtItem1.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        txtItem1.setText("Item Name =>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112)
                        .addComponent(txtItem1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtItem, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 853, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(240, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack)
                    .addComponent(txtItem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtItem1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        // TODO add your handling code here:
        MainProgram.mainframe.mainPane.remove(this);
        MainProgram.mainframe.mainPane.add(new Stock());
        SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);
    }//GEN-LAST:event_btnBackMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblItemDeails;
    private javax.swing.JTextField txtFilter;
    private javax.swing.JLabel txtItem;
    private javax.swing.JLabel txtItem1;
    // End of variables declaration//GEN-END:variables
}
