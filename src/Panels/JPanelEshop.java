package Panels;

import Frames.MenuFrame;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vit
 */
public class JPanelEshop extends javax.swing.JPanel {

    public JPanelEshop() {
        initComponents();
        myInit();
    }

    private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    private static final String URL = "jdbc:mysql://localhost/objednavky?useUnicode=true&characterEncoding=UTF-8";
   
    
    public final void myInit() {

        jDialog1.setLocationRelativeTo(null);
        //  jTextFieldOXID.setVisible(false);
        Vector<Object> vOXID = new Vector<>();

        String[] sloupce = {"Číslo obj.", "Datum obj.", "Jméno", "Příjmení", "Město", "Stav"};
        DefaultTableModel model = new DefaultTableModel(sloupce, 0);
        jTable2.setModel(model);
        jTable2.setDefaultEditor(Object.class, null); //non editable cells

        model.setRowCount(0);

        try {
            Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");
            String selectSQL = "SELECT CISLO_OBJ, STAV.OXID, DATUM, STAV, OXORDER.OXBILLFNAME, OXORDER.OXBILLLNAME, "
                    + "OXORDER.OXBILLCITY FROM stav JOIN OXORDER ON STAV.OXID = OXORDER.OXID";
            PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
            ResultSet rset = preparedStatement.executeQuery();

            while (rset.next()) {
                String cisloObj;
                if (rset.getInt(1) == 0) {
                    cisloObj = "";
                } else {
                    cisloObj = Integer.toString(rset.getInt(1));
                }
                
                vOXID.add(rset.getString(2));
                String datumObj = format.format(rset.getDate(3));
                String stav = "";
                if (rset.getInt(4) == 0) {
                    stav = "Nepřidáno";
                } else if (rset.getInt(4) == 1) {
                    stav = "Přidáno";
                }
                String jmeno = rset.getString(5);
                String prijmeni = rset.getString(6);
                String mesto = rset.getString(7);
                Vector<Object> radek = new Vector<>();

                radek.add(cisloObj);
                radek.add(datumObj);
                radek.add(jmeno);
                radek.add(prijmeni);
                radek.add(mesto);
                radek.add(stav);
                model.addRow(radek);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JPanelZakaznici.class.getName()).log(Level.SEVERE, null, ex);
        }

        jTable2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    try {
                        int sRow = jTable2.getSelectedRow();
                        String OXID = "";
                        OXID = vOXID.elementAt(0).toString();
                        JFrame frame = new JFrame();
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.add(new JPanelZakazniciEshop(OXID), BorderLayout.CENTER);
                        frame.pack();
                        frame.setVisible(true);
                    } catch (SQLException ex) {
                        Logger.getLogger(JPanelEshop.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButtonAdd = new javax.swing.JButton();
        jButtonRenew = new javax.swing.JButton();
        jTextFieldOXID = new javax.swing.JTextField();

        jDialog1.setTitle("Seznam zákazníků");
        jDialog1.setMinimumSize(new java.awt.Dimension(1000, 400));

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

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(204, 255, 153));
        setMaximumSize(new java.awt.Dimension(600, 550));
        setMinimumSize(new java.awt.Dimension(600, 550));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(600, 550));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 560, 430));

        jButtonAdd.setText("Přidat");
        jButtonAdd.setMaximumSize(new java.awt.Dimension(71, 23));
        jButtonAdd.setMinimumSize(new java.awt.Dimension(71, 23));
        jButtonAdd.setPreferredSize(new java.awt.Dimension(71, 23));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        add(jButtonAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 500, 80, 30));

        jButtonRenew.setText("Obnovit");
        jButtonRenew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRenewActionPerformed(evt);
            }
        });
        add(jButtonRenew, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 500, 80, 30));

        jTextFieldOXID.setEditable(false);
        add(jTextFieldOXID, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, 80, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        //  modify(Work.add);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonRenewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRenewActionPerformed
        //  modify(Work.renew);
    }//GEN-LAST:event_jButtonRenewActionPerformed

    /*
    private void refresh() {
       
        try {
            String str = jTextFieldFind.getText();
            Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");
            String selectSQL = "SELECT ID_ZAKAZNIK, JMENO, PRIJMENI, ULICE, POPISNE, MESTO, PSC, FIRMA, TEL, MOBIL"
                    + " FROM zakaznici WHERE LOWER(JMENO) LIKE LOWER(?) or LOWER(PRIJMENI) LIKE LOWER(?)"
                    + " or LOWER(MESTO) LIKE LOWER(?) ORDER BY ID_ZAKAZNIK ASC";
            PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
            preparedStatement.setString(1, "%" + str + "%");
            preparedStatement.setString(2, "%" + str + "%");
            preparedStatement.setString(3, "%" + str + "%");
            ResultSet rset = preparedStatement.executeQuery();

            String[] sloupce = {"Kód zákazníka", "Jméno", "Příjmeni", "Ulice", "Popisné", "Město", "PSČ", "Firma", "Telefon", "Mobil"};
            DefaultTableModel model = new DefaultTableModel(sloupce, 0);
            jTable1.setModel(model);
            jTable1.setDefaultEditor(Object.class, null); //non editable cells

            while (rset.next()) {
                int id = rset.getInt("ID_ZAKAZNIK");
                String jmeno = rset.getString("JMENO");
                String prijmeni = rset.getString("PRIJMENI");
                String ulice = rset.getString("ULICE");
                String popisne = rset.getString("POPISNE");
                String mesto = rset.getString("MESTO");
                int psc = rset.getInt("PSC");
                String firma = rset.getString("FIRMA");
                String tel = rset.getString("TEL");
                String mobil = rset.getString("MOBIL");
                Vector<Object> radek = new Vector<>();

                radek.add(id);
                radek.add(jmeno);
                radek.add(prijmeni);
                radek.add(ulice);
                radek.add(popisne);
                radek.add(mesto);
                radek.add(psc);
                radek.add(firma);
                radek.add(tel);
                radek.add(mobil);
                model.addRow(radek);
            }

            jDialog1.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(JPanelZakaznici.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void modify(JPanelZakaznici.Work w) {
        try {
            String idZak = jTextFieldIDZakaznik.getText();
            String jmeno = jTextFieldJmeno.getText();
            String prijmeni = jTextFieldPrijmeni.getText();
            String ulice = jTextFieldUlice.getText();
            String popisne = jTextFieldPopisne.getText();
            String mesto = jTextFieldMesto.getText();
            String psc = jTextFieldPSC.getText();
            String firma = jTextFieldFirma.getText();
            String tel = jTextFieldTel.getText();
            String mobil = jTextFieldMobil.getText();
            // String email = "";

            Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");

            String sql = "";
            switch (w) {
                case insert:
                    sql = "INSERT INTO zakaznici(JMENO, PRIJMENI, ULICE, POPISNE, MESTO, PSC, FIRMA, TEL, MOBIL)"
                            + " VALUES (?,?,?,?,?,?,?,?,?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, jmeno);
                    stmt.setString(2, prijmeni);
                    stmt.setString(3, ulice);
                    stmt.setString(4, popisne);
                    stmt.setString(5, mesto);
                    stmt.setInt(6, Integer.parseInt(psc));
                    stmt.setString(7, firma);
                    stmt.setString(8, tel);
                    stmt.setString(9, mobil);

                    stmt.executeUpdate();
                    stmt.close();

                    JOptionPane.showMessageDialog(new JFrame(), "Zákazník úspěšně přidán");
                    break;
                case update:
                    sql = "UPDATE zakaznici SET JMENO = ?, PRIJMENI = ?, ULICE = ?, POPISNE = ?, MESTO = ?, PSC = ?, FIRMA = ?,"
                            + " TEL = ?, MOBIL = ? WHERE ID_ZAKAZNIK = ?";

                    PreparedStatement stmt2 = conn.prepareStatement(sql);
                    stmt2.setString(1, jmeno);
                    stmt2.setString(2, prijmeni);
                    stmt2.setString(3, ulice);
                    stmt2.setString(4, popisne);
                    stmt2.setString(5, mesto);
                    stmt2.setInt(6, Integer.parseInt(psc));
                    stmt2.setString(7, firma);
                    stmt2.setString(8, tel);
                    stmt2.setString(9, mobil);
                    //  stmt2.setString(10, email);
                    stmt2.setInt(10, Integer.parseInt(idZak));

                    stmt2.executeUpdate();
                    stmt2.close();

                    JOptionPane.showMessageDialog(new JFrame(), "Zákazník úspěšně upraven");
                    break;
                case delete:
                    sql = "DELETE FROM zakaznici WHERE ID_ZAKAZNIK = ?";

                    PreparedStatement stmt3 = conn.prepareStatement(sql);
                    stmt3.setInt(1, Integer.parseInt(idZak));

                    stmt3.executeUpdate();
                    stmt3.close();

                    JOptionPane.showMessageDialog(new JFrame(), "Zákazník úspěšně smazán");
                    break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MenuFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        refresh(); 
    }
    
    private enum Work {
        add, renew
    }
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonRenew;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextFieldOXID;
    // End of variables declaration//GEN-END:variables
}
