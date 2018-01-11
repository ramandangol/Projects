/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mainPackage.Pannels;

import com.mainPackage.Pannels.Entry.ExpensesEntry;
import com.mainPackage.Pannels.Entry.PurchaseRim;
import com.mainPackage.MainProgram;
import com.mainPackage.Pannels.Administrator.admindash;
import com.mainPackage.Pannels.Entry.BalanceEntry;
import com.mainPackage.Pannels.Entry.SalesEntry;
import com.mainPackage.Pannels.Factory.Balance;
import com.mainPackage.Pannels.Factory.Production;
import com.mainPackage.Pannels.Factory.Stock;
import com.mainPackage.Pannels.Ledger.BankLedger;
import com.mainPackage.Pannels.Ledger.PurchaseLedger;
import com.mainPackage.Pannels.Ledger.SalesLedger;
import com.mainPackage.Pannels.Ledger.StaffLedger;
import dao.ExpensesDao;
import dao.IncomeDao;
import dao.ItemSalesDao;
import dao.SalesDao;
import dao.TravelDao;
import java.awt.Color;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.DailyBookModel;
import model.ItemSaleModel;
import model.SaleEntryModel;
import model.TravelExpensesDetailModel;

/**
 *
 * @author Bladestorm
 */
public class Dashboard extends javax.swing.JPanel {

    IncomeDao incomedao = new IncomeDao();
    ExpensesDao expensesdao = new ExpensesDao();

    ItemSalesDao itemdetaildao = new ItemSalesDao();
//    SaleReceiptDao salesdao = new SaleReceiptDao();

    //sales report client details
    SalesDao salesdao = new SalesDao();
    
    //sales report travel details
    TravelDao traveldao = new TravelDao();

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();

        DefaultTableModel model = (DefaultTableModel) tblincome.getModel();
        //Table Income
        List<DailyBookModel> inls = incomedao.getAllIncome();
        Iterator<DailyBookModel> incomeiter = inls.iterator();
        while (incomeiter.hasNext()) {
            DailyBookModel incomemodel = incomeiter.next();
            model.addRow(new Object[]{incomemodel.getDate(), incomemodel.getParticular(), incomemodel.getAmount()});

        }

        //Table Expenses
        DefaultTableModel model1 = (DefaultTableModel) tblExpenses.getModel();

        List<DailyBookModel> exls = expensesdao.getAllExpenses();
        Iterator<DailyBookModel> expensesiter = exls.iterator();
        while (expensesiter.hasNext()) {
            DailyBookModel expensesmodel = expensesiter.next();
            model1.addRow(new Object[]{expensesmodel.getDate(), expensesmodel.getParticular(), expensesmodel.getAmount()});
        }

        //Items sales details
        DefaultTableModel mItemDetail = (DefaultTableModel) tblItemSalesDetails.getModel();
        List<ItemSaleModel> lsitem = itemdetaildao.getItemDetails();
        Iterator<ItemSaleModel> itemIter = lsitem.iterator();
        while (itemIter.hasNext()) {
            ItemSaleModel itmodel = itemIter.next();
            mItemDetail.addRow(new Object[]{itmodel.getDate(), itmodel.getBillno(), itmodel.getClientname(), itmodel.getItem(), itmodel.getQuantity(), itmodel.getTotalprice(), itmodel.getProfit()});

        }

        //Items sales client
//        DefaultTableModel msalesclint = (DefaultTableModel) tblsales.getModel();
//        List<SaleReceiptModel> lssales = salesdao.getsalesDetail();
//        Iterator<SaleReceiptModel> saleIter = lssales.iterator();
//        while (saleIter.hasNext()) {            
//            SaleReceiptModel salemodel = saleIter.next();
//            msalesclint.addRow(new Object[]{salemodel.getDate(),salemodel.getVchno(),salemodel.getClientname(),salemodel.getDebit()});
//        }
//        
        //Items sales client
        DefaultTableModel msalesclint = (DefaultTableModel) tblsales.getModel();
        List<SaleEntryModel> lssales = salesdao.getsalesDetail();
        Iterator<SaleEntryModel> saleIter = lssales.iterator();
        while (saleIter.hasNext()) {
            SaleEntryModel salemodel = saleIter.next();
            msalesclint.addRow(new Object[]{salemodel.getDate(), salemodel.getBillno(), salemodel.getClientname(),salemodel.getAddress(), salemodel.getNetamt()});
        }
        
        //Sales Travel expenses Details
        DefaultTableModel travelmodel = (DefaultTableModel) tbltravelExpDetail.getModel();
        List<TravelExpensesDetailModel> lstravel = traveldao.getTravelDetail();
        Iterator<TravelExpensesDetailModel> travelIter = lstravel.iterator();
        while(travelIter.hasNext()){
            TravelExpensesDetailModel travelm = travelIter.next();
            travelmodel.addRow(new Object[]{travelm.getDate(),travelm.getPlacename(),travelm.getExpensesFor(),travelm.getAmount()});
        }

        //daily book filter
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tblincome.getModel());
        tblincome.setRowSorter(rowSorter);
        txtSearchdailybook.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearchdailybook.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearchdailybook.getText();

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
        //dailybook filter
        TableRowSorter<TableModel> rowSorter1 = new TableRowSorter<>(tblExpenses.getModel());
        tblExpenses.setRowSorter(rowSorter1);
        txtSearchdailybook.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text1 = txtSearchdailybook.getText();
                if (text1.trim().length() == 0) {
                    rowSorter1.setRowFilter(null);
                } else {
                    rowSorter1.setRowFilter(RowFilter.regexFilter("(?i)" + text1));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text1 = txtSearchdailybook.getText();

                if (text1.trim().length() == 0) {
                    rowSorter1.setRowFilter(null);
                } else {
                    rowSorter1.setRowFilter(RowFilter.regexFilter("(?i)" + text1));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        //Sales Item filter
        TableRowSorter<TableModel> rowSorterItem = new TableRowSorter<>(tblItemSalesDetails.getModel());
        tblItemSalesDetails.setRowSorter(rowSorterItem);
        txtsalesFilter.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String textItemSale = txtsalesFilter.getText();
                if (textItemSale.trim().length() == 0) {
                    rowSorterItem.setRowFilter(null);
                } else {
                    rowSorterItem.setRowFilter(RowFilter.regexFilter("(?i)" + textItemSale));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String textItemSale = txtsalesFilter.getText();

                if (textItemSale.trim().length() == 0) {
                    rowSorterItem.setRowFilter(null);
                } else {
                    rowSorterItem.setRowFilter(RowFilter.regexFilter("(?i)" + textItemSale));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

//        //Sales Client filter
//        TableRowSorter<TableModel> rowSorterItemclient = new TableRowSorter<>(tblsales.getModel());
//        tblsales.setRowSorter(rowSorterItemclient);
//        txtsalesFilter.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                String textItemSale1 = txtsalesFilter.getText();
//                if (textItemSale1.trim().length() == 0) {
//                    rowSorterItemclient.setRowFilter(null);
//                } else {
//                    rowSorterItemclient.setRowFilter(RowFilter.regexFilter("(?i)" + textItemSale1));
//                }
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                String textItemSale1 = txtsalesFilter.getText();
//
//                if (textItemSale1.trim().length() == 0) {
//                    rowSorterItemclient.setRowFilter(null);
//                } else {
//                    rowSorterItemclient.setRowFilter(RowFilter.regexFilter("(?i)" + textItemSale1));
//                }
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//            }
//        });
//        
        
        //sales client table 2 filter
        
        //Sales Client filter
        TableRowSorter<TableModel> rowSorterItemclient2 = new TableRowSorter<>(tblsales.getModel());
        tblsales.setRowSorter(rowSorterItemclient2);
        txtsalesfiltertable2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String textItemSale2 = txtsalesfiltertable2.getText();
                if (textItemSale2.trim().length() == 0) {
                    rowSorterItemclient2.setRowFilter(null);
                } else {
                    rowSorterItemclient2.setRowFilter(RowFilter.regexFilter("(?i)" + textItemSale2));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String textItemSale2 = txtsalesfiltertable2.getText();

                if (textItemSale2.trim().length() == 0) {
                    rowSorterItemclient2.setRowFilter(null);
                } else {
                    rowSorterItemclient2.setRowFilter(RowFilter.regexFilter("(?i)" + textItemSale2));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        //Sales place and Expenses Details
        //Sales Client filter
        TableRowSorter<TableModel> rowSorterItemclient3 = new TableRowSorter<>(tbltravelExpDetail.getModel());
        tbltravelExpDetail.setRowSorter(rowSorterItemclient3);
        txtsalesfiltertable2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String textItemSale3 = txtsalesfiltertable2.getText();
                if (textItemSale3.trim().length() == 0) {
                    rowSorterItemclient3.setRowFilter(null);
                } else {
                    rowSorterItemclient3.setRowFilter(RowFilter.regexFilter("(?i)" + textItemSale3));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String textItemSale3 = txtsalesfiltertable2.getText();

                if (textItemSale3.trim().length() == 0) {
                    rowSorterItemclient3.setRowFilter(null);
                } else {
                    rowSorterItemclient3.setRowFilter(RowFilter.regexFilter("(?i)" + textItemSale3));
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

        jPanel1 = new javax.swing.JPanel();
        lblSalesReport = new javax.swing.JLabel();
        lblEntry = new javax.swing.JLabel();
        lblFactory = new javax.swing.JLabel();
        lblLedger = new javax.swing.JLabel();
        lblDailyBook = new javax.swing.JLabel();
        lblAdmin = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanelEntry = new javax.swing.JPanel();
        lblPurchase = new javax.swing.JLabel();
        lblSales = new javax.swing.JLabel();
        lblBalance1 = new javax.swing.JLabel();
        lblExpenses = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanelFactory = new javax.swing.JPanel();
        lblProductionim = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblstockim = new javax.swing.JLabel();
        lblBalanceim = new javax.swing.JLabel();
        jPanelLedger = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        lblPurchaseledgerim = new javax.swing.JLabel();
        lblSalesLedger = new javax.swing.JLabel();
        lblstaffledger = new javax.swing.JLabel();
        btnbankledger = new javax.swing.JLabel();
        jPanelDailyBook = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblincome = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblExpenses = new javax.swing.JTable();
        txtSearchdailybook = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanelSalesReport = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblItemSalesDetails = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblsales = new javax.swing.JTable();
        txtsalesFilter = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtsalesfiltertable2 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbltravelExpDetail = new javax.swing.JTable();

        setBackground(new java.awt.Color(153, 153, 255));

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        lblSalesReport.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        lblSalesReport.setText("Sales Report");
        lblSalesReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSalesReportMouseClicked(evt);
            }
        });

        lblEntry.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        lblEntry.setText("Entry");
        lblEntry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEntryMouseClicked(evt);
            }
        });

        lblFactory.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        lblFactory.setText("Factory");
        lblFactory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFactoryMouseClicked(evt);
            }
        });

        lblLedger.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        lblLedger.setText("Ledger");
        lblLedger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLedgerMouseClicked(evt);
            }
        });

        lblDailyBook.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        lblDailyBook.setText("Daily Book");
        lblDailyBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDailyBookMouseClicked(evt);
            }
        });

        lblAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ImgAdmin.png"))); // NOI18N
        lblAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAdminMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblFactory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblSalesReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(27, 27, 27))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblEntry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblLedger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblDailyBook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAdmin)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(lblEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFactory, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLedger, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDailyBook, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSalesReport, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(lblAdmin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(102, 86, 166));
        jPanel2.setLayout(new java.awt.CardLayout());

        lblPurchase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ImgPurchase.png"))); // NOI18N
        lblPurchase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPurchaseMouseClicked(evt);
            }
        });

        lblSales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ImgSales.png"))); // NOI18N
        lblSales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSalesMouseClicked(evt);
            }
        });

        lblBalance1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ImgBalance.png"))); // NOI18N
        lblBalance1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBalance1MouseClicked(evt);
            }
        });

        lblExpenses.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ImgExpenses.png"))); // NOI18N
        lblExpenses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExpensesMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/entry.png"))); // NOI18N

        javax.swing.GroupLayout jPanelEntryLayout = new javax.swing.GroupLayout(jPanelEntry);
        jPanelEntry.setLayout(jPanelEntryLayout);
        jPanelEntryLayout.setHorizontalGroup(
            jPanelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEntryLayout.createSequentialGroup()
                .addGroup(jPanelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelEntryLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(lblExpenses, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(222, 222, 222))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEntryLayout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(lblPurchase)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBalance1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSales))
                .addContainerGap(733, Short.MAX_VALUE))
        );
        jPanelEntryLayout.setVerticalGroup(
            jPanelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEntryLayout.createSequentialGroup()
                .addGroup(jPanelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEntryLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(lblSales, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelEntryLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(lblPurchase, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelEntryLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelEntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBalance1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblExpenses, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(456, Short.MAX_VALUE))
        );

        jPanel2.add(jPanelEntry, "card2");

        lblProductionim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ImgProduction.png"))); // NOI18N
        lblProductionim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProductionimMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Sylfaen", 0, 24)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/factory.png"))); // NOI18N

        lblstockim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ImgStock.png"))); // NOI18N
        lblstockim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblstockimMouseClicked(evt);
            }
        });

        lblBalanceim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ImgFactorybalance.png"))); // NOI18N
        lblBalanceim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBalanceimMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelFactoryLayout = new javax.swing.GroupLayout(jPanelFactory);
        jPanelFactory.setLayout(jPanelFactoryLayout);
        jPanelFactoryLayout.setHorizontalGroup(
            jPanelFactoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFactoryLayout.createSequentialGroup()
                .addGroup(jPanelFactoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFactoryLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(lblProductionim, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(158, 158, 158)
                        .addComponent(lblstockim, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(158, 158, 158)
                        .addComponent(lblBalanceim, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelFactoryLayout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(682, Short.MAX_VALUE))
        );
        jPanelFactoryLayout.setVerticalGroup(
            jPanelFactoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFactoryLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanelFactoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblstockim, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProductionim, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBalanceim, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.add(jPanelFactory, "card3");

        jLabel17.setFont(new java.awt.Font("Sylfaen", 0, 24)); // NOI18N
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ledgerTitle.png"))); // NOI18N

        lblPurchaseledgerim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ImgPurchaseLedger.png"))); // NOI18N
        lblPurchaseledgerim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPurchaseledgerimMouseClicked(evt);
            }
        });

        lblSalesLedger.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ImgSalesLedger.png"))); // NOI18N
        lblSalesLedger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSalesLedgerMouseClicked(evt);
            }
        });

        lblstaffledger.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ImgStaffledger.png"))); // NOI18N
        lblstaffledger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblstaffledgerMouseClicked(evt);
            }
        });

        btnbankledger.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ImgBankLedger.png"))); // NOI18N
        btnbankledger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnbankledgerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelLedgerLayout = new javax.swing.GroupLayout(jPanelLedger);
        jPanelLedger.setLayout(jPanelLedgerLayout);
        jPanelLedgerLayout.setHorizontalGroup(
            jPanelLedgerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLedgerLayout.createSequentialGroup()
                .addGroup(jPanelLedgerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLedgerLayout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addGroup(jPanelLedgerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPurchaseledgerim, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblstaffledger, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(289, 289, 289)
                        .addGroup(jPanelLedgerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSalesLedger, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnbankledger, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelLedgerLayout.createSequentialGroup()
                        .addGap(382, 382, 382)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(757, Short.MAX_VALUE))
        );
        jPanelLedgerLayout.setVerticalGroup(
            jPanelLedgerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLedgerLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelLedgerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPurchaseledgerim)
                    .addComponent(lblSalesLedger))
                .addGap(85, 85, 85)
                .addGroup(jPanelLedgerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblstaffledger, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbankledger, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(489, Short.MAX_VALUE))
        );

        jPanel2.add(jPanelLedger, "card4");

        jLabel26.setFont(new java.awt.Font("Sylfaen", 0, 24)); // NOI18N
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/daily book.png"))); // NOI18N

        tblincome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblincome.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Income", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblincome.setRowHeight(25);
        tblincome.setRowMargin(2);
        tblincome.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblincome);
        if (tblincome.getColumnModel().getColumnCount() > 0) {
            tblincome.getColumnModel().getColumn(0).setResizable(false);
            tblincome.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblincome.getColumnModel().getColumn(1).setResizable(false);
            tblincome.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblincome.getColumnModel().getColumn(2).setResizable(false);
            tblincome.getColumnModel().getColumn(2).setPreferredWidth(50);
        }

        tblExpenses.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblExpenses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Expenses", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblExpenses.setRowHeight(25);
        tblExpenses.setRowMargin(2);
        tblExpenses.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblExpenses);
        if (tblExpenses.getColumnModel().getColumnCount() > 0) {
            tblExpenses.getColumnModel().getColumn(0).setResizable(false);
            tblExpenses.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblExpenses.getColumnModel().getColumn(1).setResizable(false);
            tblExpenses.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblExpenses.getColumnModel().getColumn(2).setResizable(false);
            tblExpenses.getColumnModel().getColumn(2).setPreferredWidth(50);
        }

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Search by Date");

        javax.swing.GroupLayout jPanelDailyBookLayout = new javax.swing.GroupLayout(jPanelDailyBook);
        jPanelDailyBook.setLayout(jPanelDailyBookLayout);
        jPanelDailyBookLayout.setHorizontalGroup(
            jPanelDailyBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDailyBookLayout.createSequentialGroup()
                .addGroup(jPanelDailyBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDailyBookLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDailyBookLayout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDailyBookLayout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(txtSearchdailybook, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(607, Short.MAX_VALUE))
        );
        jPanelDailyBookLayout.setVerticalGroup(
            jPanelDailyBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDailyBookLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDailyBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSearchdailybook, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanelDailyBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(483, Short.MAX_VALUE))
        );

        jPanel2.add(jPanelDailyBook, "card5");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sales Report.png"))); // NOI18N

        tblItemSalesDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Bill no", "Client Name", "Items", "Qty(Dorzon)", "Total", "Profit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblItemSalesDetails.setRowHeight(25);
        tblItemSalesDetails.setRowMargin(2);
        tblItemSalesDetails.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblItemSalesDetails);
        if (tblItemSalesDetails.getColumnModel().getColumnCount() > 0) {
            tblItemSalesDetails.getColumnModel().getColumn(0).setResizable(false);
            tblItemSalesDetails.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblItemSalesDetails.getColumnModel().getColumn(1).setResizable(false);
            tblItemSalesDetails.getColumnModel().getColumn(1).setPreferredWidth(30);
            tblItemSalesDetails.getColumnModel().getColumn(2).setResizable(false);
            tblItemSalesDetails.getColumnModel().getColumn(2).setPreferredWidth(130);
            tblItemSalesDetails.getColumnModel().getColumn(3).setResizable(false);
            tblItemSalesDetails.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblItemSalesDetails.getColumnModel().getColumn(4).setResizable(false);
            tblItemSalesDetails.getColumnModel().getColumn(4).setPreferredWidth(50);
            tblItemSalesDetails.getColumnModel().getColumn(5).setResizable(false);
            tblItemSalesDetails.getColumnModel().getColumn(5).setPreferredWidth(80);
            tblItemSalesDetails.getColumnModel().getColumn(6).setResizable(false);
            tblItemSalesDetails.getColumnModel().getColumn(6).setPreferredWidth(80);
        }

        tblsales.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblsales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Bill no", "Client Name", "address", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblsales.setRowHeight(25);
        tblsales.setRowMargin(2);
        tblsales.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblsales);
        if (tblsales.getColumnModel().getColumnCount() > 0) {
            tblsales.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblsales.getColumnModel().getColumn(1).setPreferredWidth(30);
            tblsales.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblsales.getColumnModel().getColumn(4).setPreferredWidth(80);
        }

        txtsalesFilter.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Search by any (Filter data)");

        tbltravelExpDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Place", "Expenses For", "Amount"
            }
        ));
        jScrollPane5.setViewportView(tbltravelExpDetail);

        javax.swing.GroupLayout jPanelSalesReportLayout = new javax.swing.GroupLayout(jPanelSalesReport);
        jPanelSalesReport.setLayout(jPanelSalesReportLayout);
        jPanelSalesReportLayout.setHorizontalGroup(
            jPanelSalesReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSalesReportLayout.createSequentialGroup()
                .addGroup(jPanelSalesReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSalesReportLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabel2))
                    .addGroup(jPanelSalesReportLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel4)
                        .addGap(27, 27, 27)
                        .addComponent(txtsalesFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(192, 192, 192)
                        .addComponent(txtsalesfiltertable2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelSalesReportLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelSalesReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(612, Short.MAX_VALUE))
        );
        jPanelSalesReportLayout.setVerticalGroup(
            jPanelSalesReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSalesReportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelSalesReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtsalesFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsalesfiltertable2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSalesReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelSalesReportLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(456, Short.MAX_VALUE))
        );

        jPanel2.add(jPanelSalesReport, "card6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblPurchaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPurchaseMouseClicked
        // TODO add your handling code here:
        MainProgram.mainframe.mainPane.remove(this);
        MainProgram.mainframe.mainPane.add(new PurchaseRim());
        SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);

    }//GEN-LAST:event_lblPurchaseMouseClicked

    private void lblBalance1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBalance1MouseClicked
        // TODO add your handling code here:
        MainProgram.mainframe.mainPane.remove(this);
        MainProgram.mainframe.mainPane.add(new BalanceEntry());
        SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);
    }//GEN-LAST:event_lblBalance1MouseClicked

    private void lblSalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSalesMouseClicked
        // TODO add your handling code here:
        MainProgram.mainframe.mainPane.remove(this);
        MainProgram.mainframe.mainPane.add(new SalesEntry());
        SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);

    }//GEN-LAST:event_lblSalesMouseClicked

    private void lblProductionimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProductionimMouseClicked
        // TODO add your handling code here:

        MainProgram.mainframe.mainPane.remove(this);
        MainProgram.mainframe.mainPane.add(new Production());
        SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);
    }//GEN-LAST:event_lblProductionimMouseClicked

    private void lblstockimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblstockimMouseClicked
        // TODO add your handling code here:
        MainProgram.mainframe.mainPane.remove(this);
        MainProgram.mainframe.mainPane.add(new Stock());
        SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);
    }//GEN-LAST:event_lblstockimMouseClicked

    private void lblPurchaseledgerimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPurchaseledgerimMouseClicked
        // TODO add your handling code here:
        MainProgram.mainframe.mainPane.remove(this);
        MainProgram.mainframe.mainPane.add(new PurchaseLedger());
        SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);
    }//GEN-LAST:event_lblPurchaseledgerimMouseClicked

    private void lblSalesReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSalesReportMouseClicked
        // TODO add your handling code here:
        //add
        jPanel2.removeAll();
        jPanel2.repaint();
        jPanel2.revalidate();

        //
        jPanel2.add(jPanelSalesReport);
        jPanel2.repaint();
        jPanel2.revalidate();

        lblEntry.setForeground(Color.black);
        lblFactory.setForeground(Color.black);
        lblLedger.setForeground(Color.black);
        lblDailyBook.setForeground(Color.black);
        lblSalesReport.setForeground(Color.blue);
    }//GEN-LAST:event_lblSalesReportMouseClicked

    private void lblDailyBookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDailyBookMouseClicked
        // TODO add your handling code here:
        //add
        jPanel2.removeAll();
        jPanel2.repaint();
        jPanel2.revalidate();

        //
        jPanel2.add(jPanelDailyBook);
        jPanel2.repaint();
        jPanel2.revalidate();

        lblEntry.setForeground(Color.black);
        lblFactory.setForeground(Color.black);
        lblLedger.setForeground(Color.black);
        lblDailyBook.setForeground(Color.blue);
        lblSalesReport.setForeground(Color.black);


    }//GEN-LAST:event_lblDailyBookMouseClicked

    private void lblLedgerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLedgerMouseClicked
        // TODO add your handling code here:
        //add
        jPanel2.removeAll();
        jPanel2.repaint();
        jPanel2.revalidate();

        //
        jPanel2.add(jPanelLedger);
        jPanel2.repaint();
        jPanel2.revalidate();

        lblEntry.setForeground(Color.black);
        lblFactory.setForeground(Color.black);
        lblLedger.setForeground(Color.blue);
        lblDailyBook.setForeground(Color.black);
        lblSalesReport.setForeground(Color.black);
    }//GEN-LAST:event_lblLedgerMouseClicked

    private void lblFactoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFactoryMouseClicked
        // TODO add your handling code here:
        //removed
        jPanel2.removeAll();
        jPanel2.repaint();
        jPanel2.revalidate();

        //add
        jPanel2.add(jPanelFactory);
        jPanel2.repaint();
        jPanel2.revalidate();

        lblEntry.setForeground(Color.black);
        lblFactory.setForeground(Color.blue);
        lblLedger.setForeground(Color.black);
        lblDailyBook.setForeground(Color.black);
        lblSalesReport.setForeground(Color.black);

    }//GEN-LAST:event_lblFactoryMouseClicked

    private void lblEntryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEntryMouseClicked
        // TODO add your handling code here:
        //removed
        jPanel2.removeAll();
        jPanel2.repaint();
        jPanel2.revalidate();

        //add
        jPanel2.add(jPanelEntry);
        jPanel2.repaint();
        jPanel2.revalidate();

        lblEntry.setForeground(Color.blue);
        lblFactory.setForeground(Color.black);
        lblLedger.setForeground(Color.black);
        lblDailyBook.setForeground(Color.black);
        lblSalesReport.setForeground(Color.black);


    }//GEN-LAST:event_lblEntryMouseClicked

    private void lblExpensesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExpensesMouseClicked
        // TODO add your handling code here:

        MainProgram.mainframe.mainPane.remove(this);
        MainProgram.mainframe.mainPane.add(new ExpensesEntry());
        SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);
    }//GEN-LAST:event_lblExpensesMouseClicked

    private void lblBalanceimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBalanceimMouseClicked
        // TODO add your handling code here:
        MainProgram.mainframe.mainPane.remove(this);
        MainProgram.mainframe.mainPane.add(new Balance());
        SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);

    }//GEN-LAST:event_lblBalanceimMouseClicked

    private void lblSalesLedgerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSalesLedgerMouseClicked
        // TODO add your handling code here:
        MainProgram.mainframe.mainPane.remove(this);
        MainProgram.mainframe.mainPane.add(new SalesLedger());
        SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);

    }//GEN-LAST:event_lblSalesLedgerMouseClicked

    private void btnbankledgerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbankledgerMouseClicked
        // TODO add your handling code here:
        MainProgram.mainframe.mainPane.remove(this);
        MainProgram.mainframe.mainPane.add(new BankLedger());
        SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);
    }//GEN-LAST:event_btnbankledgerMouseClicked

    private void lblAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAdminMouseClicked
        // TODO add your handling code here:
        String msg = JOptionPane.showInputDialog(null, "Enter Administrator password", "Administrator", JOptionPane.WARNING_MESSAGE);
        if (msg.equalsIgnoreCase("Arnika")) {
            MainProgram.mainframe.mainPane.remove(this);
            MainProgram.mainframe.mainPane.add(new admindash());
            SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid login", "", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_lblAdminMouseClicked

    private void lblstaffledgerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblstaffledgerMouseClicked
        // TODO add your handling code here:
        MainProgram.mainframe.mainPane.remove(this);
        MainProgram.mainframe.mainPane.add(new StaffLedger());
        SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);
    }//GEN-LAST:event_lblstaffledgerMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnbankledger;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelDailyBook;
    public javax.swing.JPanel jPanelEntry;
    public javax.swing.JPanel jPanelFactory;
    private javax.swing.JPanel jPanelLedger;
    private javax.swing.JPanel jPanelSalesReport;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblAdmin;
    private javax.swing.JLabel lblBalance1;
    private javax.swing.JLabel lblBalanceim;
    private javax.swing.JLabel lblDailyBook;
    private javax.swing.JLabel lblEntry;
    private javax.swing.JLabel lblExpenses;
    private javax.swing.JLabel lblFactory;
    private javax.swing.JLabel lblLedger;
    private javax.swing.JLabel lblProductionim;
    private javax.swing.JLabel lblPurchase;
    private javax.swing.JLabel lblPurchaseledgerim;
    private javax.swing.JLabel lblSales;
    private javax.swing.JLabel lblSalesLedger;
    private javax.swing.JLabel lblSalesReport;
    private javax.swing.JLabel lblstaffledger;
    private javax.swing.JLabel lblstockim;
    private javax.swing.JTable tblExpenses;
    private javax.swing.JTable tblItemSalesDetails;
    private javax.swing.JTable tblincome;
    private javax.swing.JTable tblsales;
    private javax.swing.JTable tbltravelExpDetail;
    private javax.swing.JTextField txtSearchdailybook;
    private javax.swing.JTextField txtsalesFilter;
    private javax.swing.JTextField txtsalesfiltertable2;
    // End of variables declaration//GEN-END:variables
}
