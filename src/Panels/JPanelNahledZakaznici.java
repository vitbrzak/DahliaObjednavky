/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Panels;

import Frames.NahledyFrame;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vitbr
 */
public class JPanelNahledZakaznici extends javax.swing.JPanel {

    private static final String URL = "jdbc:mysql://localhost/objednavky?useUnicode=true&characterEncoding=UTF-8";

    public JPanelNahledZakaznici() {
        initComponents();
        myInit();
    }

    public void myInit() {

        String[] sloupce = {"Firma", "Jméno", "Příjmení", "Adresa", "Město", "PSČ", "Telefon", "Mobil", "Email"};
        DefaultTableModel model = new DefaultTableModel(sloupce, 0);
        jTable1.setModel(model);

        try {
            Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");
            String selectSQL = "SELECT JMENO, PRIJMENI, FIRMA, MESTO, ULICE, "
                    + "POPISNE, PSC, TEL, MOBIL FROM zakaznici ORDER BY PRIJMENI;";
            PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
            ResultSet rset = preparedStatement.executeQuery();

            while (rset.next()) {

                String jmeno = rset.getString("JMENO");
                String prijmeni = rset.getString("PRIJMENI");
                String firma = rset.getString("FIRMA");
                String mesto = rset.getString("MESTO");
                String adresa = rset.getString("ULICE") + " " + rset.getString("POPISNE");
                int psc = rset.getInt("PSC");
                String tel = rset.getString("TEL");
                String mobil = rset.getString("MOBIL");
                String email = "";
                Vector<Object> radek = new Vector<>();

                radek.add(firma);
                radek.add(jmeno);
                radek.add(prijmeni);
                radek.add(adresa);
                radek.add(mesto);
                radek.add(psc);
                radek.add(tel);
                radek.add(mobil);
                radek.add(email);
                model.addRow(radek);
            }
            model.removeRow(0);
        } catch (SQLException ex) {
            Logger.getLogger(NahledyFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabelNadpis = new javax.swing.JLabel();
        jButtonPrint = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 255, 153));
        setMaximumSize(new java.awt.Dimension(1000, 600));
        setMinimumSize(new java.awt.Dimension(1000, 600));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabelNadpis.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelNadpis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNadpis.setText("Seznam zákazníků");
        jLabelNadpis.setPreferredSize(new java.awt.Dimension(100, 30));

        jButtonPrint.setLabel("Tisk");
        jButtonPrint.setMaximumSize(new java.awt.Dimension(100, 30));
        jButtonPrint.setMinimumSize(new java.awt.Dimension(100, 30));
        jButtonPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(380, 380, 380)
                        .addComponent(jLabelNadpis, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(441, 441, 441)
                .addComponent(jButtonPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(jLabelNadpis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintActionPerformed
        MessageFormat header = new MessageFormat("Seznam zákazníků");
        MessageFormat footer = new MessageFormat("Strana {0}");

        try {
            boolean complete = jTable1.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            if (complete) {
                JOptionPane.showMessageDialog(new JFrame(), "Tisk dokončen");
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Tisk zrušen");
            }
        } catch (PrinterException pe) {
            JOptionPane.showMessageDialog(new JFrame(), "Objednávka úspěšně přidána");
        }
    }//GEN-LAST:event_jButtonPrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JLabel jLabelNadpis;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
