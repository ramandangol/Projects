/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mainPackage.Pannels.Ledger;

import com.mainPackage.MainProgram;
import dao.BankDepositDao;
import dao.BankLedgerDao;
import dao.CashDepositDao;
import dao.CashLedgerDao;
import dao.ExpensesDao;
import dao.PurReceiptDao;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.BankDepositMode;
import model.BankLedgerModel;
import model.CashDepositModel;
import model.CashLedgerModel;
import model.DailyBookModel;
import model.DateFilterModel;
import model.PurReceiptModel;

/**
 *
 * @author Bladestorm
 */
public class PurchaseLedgerDetails extends javax.swing.JPanel {

    String sdate, sbillno, sMoney;
    Date ndate;
    BigDecimal bg_money;
    int nbillno;

    /**
     * Creates new form PurchaseLedgerDetails
     */
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    PurReceiptDao receiptdao = new PurReceiptDao();
    PurReceiptModel purreceiptmodel = new PurReceiptModel();

    BankDepositDao bankdepositdao = new BankDepositDao();
    BankDepositMode bankdepmodel = new BankDepositMode();

    BankLedgerModel bankledgermodel = new BankLedgerModel();
    BankLedgerDao bankLedDao = new BankLedgerDao();

    //Cash decrease and cash ledger
    CashDepositModel cashdpmodel = new CashDepositModel();
    CashDepositDao cashdpdao = new CashDepositDao();
    CashLedgerModel cashledgermodel = new CashLedgerModel();
    CashLedgerDao cashledgerdao = new CashLedgerDao();

    //Expenses book for daily book
    ExpensesDao expdao = new ExpensesDao();
    DailyBookModel dailybookmodel = new DailyBookModel();

    DateFilterModel dateftmod = new DateFilterModel();

    public PurchaseLedgerDetails(String pcompanyname, String paddress) {
        initComponents();

        //Bank name list
        List<String> blst = bankdepositdao.getBankname();
        Iterator<String> ls = blst.iterator();
        while (ls.hasNext()) {
            txtBankName.addItem(ls.next());

//            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
//        table.setRowSorter(rowSorter);
//        txtFromdate.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                String text = txtFromdate.getText();
//                if (text.trim().length() == 0) {
//                    rowSorter.setRowFilter(null);
//                } else {
//                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
//                }
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                String text = txtFromdate.getText();
//
//                if (text.trim().length() == 0) {
//                    rowSorter.setRowFilter(null);
//                } else {
//                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
//                }
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//            }
//        });
        }

        lblCompanyName.setText(pcompanyname);
        lblAddress.setText(paddress);
        txtCompanyname.setText(pcompanyname);
        txtDate.setText(dateFormat.format(date));
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        List<PurReceiptModel> receiptlist = receiptdao.selectAll(txtCompanyname.getText());
        Iterator<PurReceiptModel> iterator = receiptlist.iterator();
        BigDecimal balance = new BigDecimal(0);
        BigDecimal dbalance = new BigDecimal(0);
        BigDecimal cbalance = new BigDecimal(0);
        BigDecimal zero = new BigDecimal(0);
        String sbalance = null;
        while (iterator.hasNext()) {
            PurReceiptModel list = iterator.next();
            String p = list.getVchtype();
            balance = balance.add(list.getDebit().subtract(list.getCredit()));
            dbalance = dbalance.add(list.getDebit());
            cbalance = cbalance.add(list.getCredit());
            int result = balance.compareTo(zero);
            if (result == 1) {
                sbalance = balance.toString() + "  DR";
            } else {
                sbalance = balance.abs().toString() + "  CR";

            }
            if (p.equals("Receipt No")) {
                model.addRow(new Object[]{list.getDate(), list.getParticular(), list.getVchtype(), list.getVchno(), "", list.getCredit(), sbalance});
            } else {
                model.addRow(new Object[]{list.getDate(), list.getParticular(), list.getVchtype(), list.getVchno(), list.getDebit(), "", sbalance});
            }
        }
        model.addRow(new Object[]{"", "", "", "", "", "", ""});
        model.addRow(new Object[]{"", "Total", "", "", dbalance, cbalance, sbalance});

    }

    public Date getnDate() {
        sdate = txtDate.getText();
        try {
            /*final*/ ndate = dateFormat.parse(sdate);
        } catch (ParseException dateexception) {
            JOptionPane.showMessageDialog(null, "Please enter Date in the Format YYYY/MM/DD");
        }
        return ndate;
    }

    public Date getFromDate() {
        sdate = txtFromdate.getText();
        try {
            ndate = dateFormat.parse(sdate);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please enter Date in the Format YYYY/MM/DD");
        }
        return ndate;
    }

    public Date getToDate() {
        sdate = txtTodate.getText();
        try {
            ndate = dateFormat.parse(sdate);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please enter Date in the Format YYYY/MM/DD");
        }
        return ndate;
    }

    public int getnBill() {
        sbillno = txtReceipt.getText();
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

        buttonGroupReceipt = new javax.swing.ButtonGroup();
        lblAddress = new javax.swing.JLabel();
        lblCompanyName = new javax.swing.JLabel();
        lblPrint = new javax.swing.JLabel();
        lblCom = new javax.swing.JLabel();
        txtAddress2 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblReceipt = new javax.swing.JLabel();
        lblAmt = new javax.swing.JLabel();
        lblDate1 = new javax.swing.JLabel();
        txtFromdate = new javax.swing.JTextField();
        lblDate2 = new javax.swing.JLabel();
        txtTodate = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        txtCompanyname = new javax.swing.JTextField();
        txtReceipt = new javax.swing.JTextField();
        txtAmount = new javax.swing.JTextField();
        btnPaid = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnBack = new javax.swing.JLabel();
        jRadioButtonCash = new javax.swing.JRadioButton();
        jRadioButtonBank = new javax.swing.JRadioButton();
        lblPayment = new javax.swing.JLabel();
        txtBankName = new javax.swing.JComboBox<>();
        lblBankName = new javax.swing.JLabel();
        btnViewDetails = new javax.swing.JButton();

        lblAddress.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        lblCompanyName.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        lblPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print.png"))); // NOI18N
        lblPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrintMouseClicked(evt);
            }
        });

        lblCom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCom.setText("Company name");

        txtAddress2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtAddress2.setText("Cash Payment");

        lblDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDate.setText("Date");

        lblReceipt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblReceipt.setText("Receipt no");

        lblAmt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAmt.setText("Amount");

        lblDate1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDate1.setText("From");

        txtFromdate.setText("2017/01/01");

        lblDate2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDate2.setText("To");

        txtTodate.setText("2017/12/30");

        txtCompanyname.setEditable(false);

        txtAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAmountActionPerformed(evt);
            }
        });

        btnPaid.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnPaid.setText("Paid");
        btnPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaidActionPerformed(evt);
            }
        });

        table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "    Date", "    Particular", "    Vch Type", "   Vch No", "    Debit", "    Credit", "   Balance"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setRowHeight(25);
        table.setRowMargin(2);
        table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(1).setPreferredWidth(150);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(3).setPreferredWidth(30);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(6).setResizable(false);
        }

        btnBack.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/goback.png"))); // NOI18N
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });

        buttonGroupReceipt.add(jRadioButtonCash);
        jRadioButtonCash.setText("Cash");
        jRadioButtonCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCashActionPerformed(evt);
            }
        });

        buttonGroupReceipt.add(jRadioButtonBank);
        jRadioButtonBank.setText("Bank");
        jRadioButtonBank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonBankActionPerformed(evt);
            }
        });

        lblPayment.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtBankName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBankNameActionPerformed(evt);
            }
        });

        lblBankName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblBankName.setText("BankName");

        btnViewDetails.setText("View");
        btnViewDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBankName, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 398, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(321, 321, 321)
                .addComponent(lblPrint)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(lblReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCom, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCompanyname, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRadioButtonCash)
                                        .addGap(35, 35, 35)
                                        .addComponent(jRadioButtonBank))
                                    .addComponent(txtBankName, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(btnPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtFromdate, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(lblDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTodate, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnViewDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(68, 68, 68)
                    .addComponent(txtAddress2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1022, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBack)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCom, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCompanyname, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButtonCash)
                            .addComponent(jRadioButtonBank)
                            .addComponent(lblPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBankName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBankName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTodate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnViewDetails))
                            .addComponent(txtFromdate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(140, 140, 140)
                    .addComponent(txtAddress2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(496, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAmountActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtAmountActionPerformed

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
        // TODO add your handling code here:
        MainProgram.mainframe.mainPane.remove(this);
        MainProgram.mainframe.mainPane.add(new PurchaseLedger());
        SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);

    }//GEN-LAST:event_btnBackMouseClicked

    private void lblPrintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrintMouseClicked
        // TODO add your handling code here:
        String name = lblCompanyName.getText();
        String address = lblAddress.getText();

        try {
            PrinterJob job = PrinterJob.getPrinterJob();

            MessageFormat[] header = new MessageFormat[3];

            header[0] = new MessageFormat("line 1");
            header[1] = new MessageFormat("                                                          " + name);
            header[2] = new MessageFormat("                                                          " + address);

            MessageFormat[] footer = new MessageFormat[2];
            footer[0] = new MessageFormat("");
            footer[1] = new MessageFormat("-{1}-");
            job.setPrintable(new MyTablePrintable(table, JTable.PrintMode.FIT_WIDTH, header, footer));
            job.printDialog();
            job.print();

        } catch (Exception ex) {
        }

    }//GEN-LAST:event_lblPrintMouseClicked

    private void jRadioButtonCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCashActionPerformed
        // TODO add your handling code here:
        String pay;
        jRadioButtonBank.setActionCommand("Cheque");
        jRadioButtonCash.setActionCommand("Cash");
        if (buttonGroupReceipt.getSelection().getActionCommand().equals(jRadioButtonBank.getActionCommand())) {
            lblPayment.setText(jRadioButtonBank.getActionCommand());
            pay = lblPayment.getText();
        } else if (buttonGroupReceipt.getSelection().getActionCommand().equals(jRadioButtonCash.getActionCommand())) {
            lblPayment.setText(jRadioButtonCash.getActionCommand());
            pay = lblPayment.getText();

        }
    }//GEN-LAST:event_jRadioButtonCashActionPerformed

    private void btnPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaidActionPerformed
        // TODO add your handling code here:
        String sPurReceipt = lblPayment.getText();
        String name = "Cash Deposit";

        sdate = txtDate.getText();
        sbillno = txtReceipt.getText();
        String sCompanyname = txtCompanyname.getText();
        String saddress = lblAddress.getText();
        sMoney = txtAmount.getText();

        if (sdate.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Date is Empty !!");
        } else if (!sdate.matches("\\d{4}/\\d{1,2}/\\d{1,2}")) {
            JOptionPane.showMessageDialog(null, "Date not valid date should be YYYY/MM/DD");
        } else if (sbillno.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Bill no is Empty !! ");
        } else if (!sbillno.matches("[0-9_]+")) {
            JOptionPane.showMessageDialog(null, "Enter digits in bill no. Alphabet not valid !!");
        } else if (sMoney.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter money");
        } else if (sPurReceipt.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Checked Payment Style !!");
        } else if (sPurReceipt.equalsIgnoreCase("Cheque")) {
            Object oBankName = txtBankName.getSelectedItem();
            String sBankName = oBankName.toString();

            if (sBankName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Select bank name");
            } else if (!bankdepositdao.balanceCheck(sBankName, new BigDecimal(sMoney))) {
                JOptionPane.showMessageDialog(null, "You don't have sufficient balance to paid by " + sBankName);
            } else {
                if (JOptionPane.showConfirmDialog(null, "\nDate :-" + sdate + "\n Receipt No :- " + sbillno + "\n Company Name :- " + sCompanyname + "\n Amount :- Rs " + sMoney + "\n paid by " + sPurReceipt, "Cash Receipt", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    bg_money = new BigDecimal(sMoney);
                    purreceiptmodel.setDate(getnDate());
                    purreceiptmodel.setCompanyname(sCompanyname);
                    purreceiptmodel.setParticular(sPurReceipt);
                    purreceiptmodel.setVchtype("Receipt No");
                    purreceiptmodel.setVchno(getnBill());
                    purreceiptmodel.setDebit(new BigDecimal(0));
                    purreceiptmodel.setCredit(bg_money);
                    receiptdao.insert(purreceiptmodel);

                    //BankBalance
                    bankdepmodel.setBankname(sBankName);
                    bankdepmodel.setAmount(bg_money);
                    bankdepositdao.bankAmountDecrese(bankdepmodel);

                    //Bankbalance entry ledger
                    bankledgermodel.setDate(getnDate());
                    bankledgermodel.setBankname(sBankName);
//                    bankledgermodel.setParticular("Rim Purchase paid");
                    bankledgermodel.setParticular("Purchase Payment to " +sCompanyname);
                    bankledgermodel.setDebit(bg_money);
                    bankledgermodel.setCredit(new BigDecimal(0));
                    bankLedDao.insert(bankledgermodel);

                    //Expense book
                    dailybookmodel.setDate(getnDate());
                    dailybookmodel.setParticular("Rim pur. " + sCompanyname + " by Cheque " + sBankName);
                    dailybookmodel.setAmount(bg_money);
                    expdao.insertExpen(dailybookmodel);

                    JOptionPane.showMessageDialog(null, "Data Saved");
                    MainProgram.mainframe.mainPane.remove(this);
                    MainProgram.mainframe.mainPane.add(new PurchaseLedgerDetails(sCompanyname, saddress));
                    SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);
                } else {
                    JOptionPane.showMessageDialog(null, "Record not saved");
                }
            }

        } else if (!cashdpdao.cashCheck(name, new BigDecimal(sMoney))) {
            JOptionPane.showMessageDialog(null, "You don't have sufficent balance !!");
        } else {
            if (JOptionPane.showConfirmDialog(null, "\nDate :-" + sdate + "\n Receipt No :- " + sbillno + "\n Company Name :- " + sCompanyname + "\n Amount :- Rs " + sMoney + "\n paid by " + sPurReceipt, "Cash Receipt", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                bg_money = new BigDecimal(sMoney);
                purreceiptmodel.setDate(getnDate());
                purreceiptmodel.setCompanyname(sCompanyname);
                purreceiptmodel.setParticular(sPurReceipt);
                purreceiptmodel.setVchtype("Receipt No");
                purreceiptmodel.setVchno(getnBill());
                purreceiptmodel.setDebit(new BigDecimal(0));
                purreceiptmodel.setCredit(bg_money);
                receiptdao.insert(purreceiptmodel);

                cashdpmodel.setParticular("Cash Deposit");
                cashdpmodel.setCash(bg_money);
                cashdpdao.cashDecrease(cashdpmodel);

                cashledgermodel.setDate(getnDate());
//                cashledgermodel.setParticular("Purchase Payment");
                cashledgermodel.setParticular("Payment To " +sCompanyname);
                cashledgermodel.setDebit(bg_money);
                cashledgermodel.setCredit(new BigDecimal(0));
                cashledgerdao.insert(cashledgermodel);

                //Expense book
                dailybookmodel.setDate(getnDate());
                dailybookmodel.setParticular("Rim pur. " + sCompanyname + " by Cash");
                dailybookmodel.setAmount(bg_money);
                expdao.insertExpen(dailybookmodel);

                JOptionPane.showMessageDialog(null, "success with cash");

                MainProgram.mainframe.mainPane.remove(this);
                MainProgram.mainframe.mainPane.add(new PurchaseLedgerDetails(sCompanyname, saddress));
                SwingUtilities.updateComponentTreeUI(MainProgram.mainframe.mainPane);

            } else {
                JOptionPane.showMessageDialog(null, "Record not saved");
            }
        }

    }//GEN-LAST:event_btnPaidActionPerformed

    private void jRadioButtonBankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonBankActionPerformed
        // TODO add your handling code here:
        String pay;
        jRadioButtonBank.setActionCommand("Cheque");
        jRadioButtonCash.setActionCommand("Cash");
        if (buttonGroupReceipt.getSelection().getActionCommand().equals(jRadioButtonBank.getActionCommand())) {
            lblPayment.setText(jRadioButtonBank.getActionCommand());
            pay = lblPayment.getText();
        } else if (buttonGroupReceipt.getSelection().getActionCommand().equals(jRadioButtonCash.getActionCommand())) {
            lblPayment.setText(jRadioButtonCash.getActionCommand());
            pay = lblPayment.getText();

        }
    }//GEN-LAST:event_jRadioButtonBankActionPerformed

    private void txtBankNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBankNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBankNameActionPerformed

    private void btnViewDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsActionPerformed
        // TODO add your handling code here:
        String scompanyname = txtCompanyname.getText();
        String sFromDate = txtFromdate.getText();
        String sToDate = txtTodate.getText();
        if (sFromDate.isEmpty()) {
            JOptionPane.showMessageDialog(null, "From Date is Empty !!");
        } else if (!sFromDate.matches("\\d{4}/\\d{1,2}/\\d{1,2}")) {
            JOptionPane.showMessageDialog(null, "Date not valid date should be YYYY/MM/DD");
        } else if (!sFromDate.matches("[0-9/_]+")) {
            JOptionPane.showMessageDialog(null, "From Date not Valid !!");
        } else if (sToDate.isEmpty()) {
            JOptionPane.showMessageDialog(null, "To Date is Empty !!");
        } else if (!sToDate.matches("\\d{4}/\\d{1,2}/\\d{1,2}")) {
            JOptionPane.showMessageDialog(null, "Date not valid date should be YYYY/MM/DD");
        } else if (!sToDate.matches("[0-9/_]+")) {
            JOptionPane.showMessageDialog(null, "To Date not Valid !!");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Are you sure want to view !! " + "\n From :- " + sFromDate + "\n To " + sToDate, "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);

                BigDecimal initial = receiptdao.getOpenBalance(scompanyname, getFromDate());
                BigDecimal ze = new BigDecimal(0);
                int r = initial.compareTo(ze);

                if (r == 1) {
                    model.addRow(new Object[]{sFromDate, "Opening Balance", "", "", initial, "", initial + "  DR"});
                } else {
                    model.addRow(new Object[]{sFromDate, "Capital", "", "", "", initial.abs(), initial.abs() + " CR"});
                }

                dateftmod.setFromDate(getFromDate());
                dateftmod.setToDate(getToDate());
                dateftmod.setName(scompanyname);
                receiptdao.getFilterbyDate(dateftmod);

                List<PurReceiptModel> receiptlist = receiptdao.getFilterbyDate(dateftmod);
                Iterator<PurReceiptModel> iterator = receiptlist.iterator();
                BigDecimal zero = new BigDecimal(0);

                String sbalance = null;
                while (iterator.hasNext()) {
                    PurReceiptModel list = iterator.next();
                    String p = list.getVchtype();
                    initial = initial.add(list.getDebit().subtract(list.getCredit()));

                    int result = initial.compareTo(zero);
                    if (result == 1) {
                        sbalance = initial.toString() + "  DR";

                    } else {
                        sbalance = initial.abs().toString() + "  CR";

                    }
                    if (p.equals("Receipt No")) {
                        model.addRow(new Object[]{list.getDate(), list.getParticular(), list.getVchtype(), list.getVchno(), "", list.getCredit(), sbalance});
                    } else {
                        model.addRow(new Object[]{list.getDate(), list.getParticular(), list.getVchtype(), list.getVchno(), list.getDebit(), "", sbalance});
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "cancelled");
            }

        }
    }//GEN-LAST:event_btnViewDetailsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JButton btnPaid;
    private javax.swing.JButton btnViewDetails;
    private javax.swing.ButtonGroup buttonGroupReceipt;
    private javax.swing.JRadioButton jRadioButtonBank;
    private javax.swing.JRadioButton jRadioButtonCash;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblAmt;
    private javax.swing.JLabel lblBankName;
    private javax.swing.JLabel lblCom;
    private javax.swing.JLabel lblCompanyName;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDate1;
    private javax.swing.JLabel lblDate2;
    private javax.swing.JLabel lblPayment;
    public javax.swing.JLabel lblPrint;
    private javax.swing.JLabel lblReceipt;
    public javax.swing.JTable table;
    private javax.swing.JLabel txtAddress2;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JComboBox<String> txtBankName;
    private javax.swing.JTextField txtCompanyname;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtFromdate;
    private javax.swing.JTextField txtReceipt;
    private javax.swing.JTextField txtTodate;
    // End of variables declaration//GEN-END:variables
}
