package Panels;

import Frames.MenuFrame;
import Frames.ObjednavkaFrame;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vit
 */
public class JPanelZakaznici extends javax.swing.JPanel {

    public JPanelZakaznici() throws SQLException {
        initComponents();
        myInit();
    }

    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    private static final String URL = "jdbc:mysql://localhost/objednavky?useUnicode=true&characterEncoding=UTF-8";

    public final void myInit() throws SQLException {

        jDialog1.setLocationRelativeTo(null);

        String[] sloupce = {"Číslo objednávky", "Datum objednání", "Způsob odběru"};
        DefaultTableModel model = new DefaultTableModel(sloupce, 0);
        jTable2.setModel(model);
        jTable2.setDefaultEditor(Object.class, null); //non editable cells

        jTable1.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (jTable1.getSelectedRow() > -1) {
                jTextFieldIDZakaznik.setText(jTable1.getValueAt((int) jTable1.getSelectedRow(), 0).toString());
                jTextFieldJmeno.setText(jTable1.getValueAt((int) jTable1.getSelectedRow(), 1).toString());
                jTextFieldPrijmeni.setText(jTable1.getValueAt((int) jTable1.getSelectedRow(), 2).toString());
                jTextFieldUlice.setText(jTable1.getValueAt((int) jTable1.getSelectedRow(), 3).toString());
                jTextFieldPopisne.setText(jTable1.getValueAt((int) jTable1.getSelectedRow(), 4).toString());
                jTextFieldMesto.setText(jTable1.getValueAt((int) jTable1.getSelectedRow(), 5).toString());
                jTextFieldPSC.setText(jTable1.getValueAt((int) jTable1.getSelectedRow(), 6).toString());
                jTextFieldFirma.setText(jTable1.getValueAt((int) jTable1.getSelectedRow(), 7).toString());
                jTextFieldTel.setText(jTable1.getValueAt((int) jTable1.getSelectedRow(), 8).toString());
                jTextFieldMobil.setText(jTable1.getValueAt((int) jTable1.getSelectedRow(), 9).toString());
            }
        });
        
        model.addRow(new Vector<>());
        
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    model.setRowCount(0);
                    if (!"".equals(jTextFieldIDZakaznik.getText())) {
                        try {
                            Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");
                            String selectSQL = "SELECT DISTINCT CISLO_OBJ, DATUM_OBJ, ID_DOPRAVA"
                                    + " FROM objednavky WHERE ID_ZAKAZNIK = ?";
                            PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
                            preparedStatement.setInt(1, Integer.parseInt(jTextFieldIDZakaznik.getText()));
                            ResultSet rset = preparedStatement.executeQuery();

                            while (rset.next()) {
                                int cisloObj = rset.getInt("CISLO_OBJ");
                                String datumObj = format.format(rset.getDate("DATUM_OBJ"));
                                String doprava = "";
                                if (rset.getInt("ID_DOPRAVA") == 1) {
                                    doprava = "Poštou";
                                } else if (rset.getInt("ID_DOPRAVA") == 2) {
                                    doprava = "Osobně";
                                }
                                Vector<Object> radek = new Vector<>();

                                radek.add(cisloObj);
                                radek.add(datumObj);
                                radek.add(doprava);
                                model.addRow(radek);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(JPanelZakaznici.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    model.addRow(new Vector<>());
                }
            }
        });

        jTable2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    if (!"".equals(jTextFieldIDZakaznik.getText())) {
                        int radek = jTable2.getSelectedRow();
                        Object test = jTable2.getValueAt(radek, 0);
                        int cisloObj = 0;
                        int idObj = 0;
                        String datum = "";
                        int doprava = 0;
                        if (test != null) {
                            try {
                                Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");

                                PreparedStatement stmtNazev = conn.prepareStatement("SELECT ID_OBJEDNAVKA FROM objednavky WHERE CISLO_OBJ = ?");
                                stmtNazev.setInt(1, Integer.parseInt(jTable2.getValueAt(radek, 0).toString()));
                                ResultSet rs = stmtNazev.executeQuery();
                                if (rs.next()) {
                                    idObj = rs.getInt(1);
                                }
                                cisloObj = Integer.parseInt(jTable2.getValueAt(radek, 0).toString());
                                datum = jTable2.getValueAt(radek, 1).toString();
                                if (jTable2.getValueAt(radek, 2).toString().equals("Osobně")) {
                                    doprava = 1;
                                }

                            } catch (SQLException ex) {
                                Logger.getLogger(JPanelObjednavka.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        ObjednavkaFrame frame = new ObjednavkaFrame(Integer.parseInt(jTextFieldIDZakaznik.getText()), idObj, cisloObj, datum, doprava);
                        frame.setVisible(true);
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
        jTextFieldPSC = new javax.swing.JTextField();
        jTextFieldFind = new javax.swing.JTextField();
        jButtonFind = new javax.swing.JButton();
        jLabelPopisne = new javax.swing.JLabel();
        jTextFieldMobil = new javax.swing.JTextField();
        jLabelMobil = new javax.swing.JLabel();
        jLabelJmeno = new javax.swing.JLabel();
        jTextFieldJmeno = new javax.swing.JTextField();
        jTextFieldPrijmeni = new javax.swing.JTextField();
        jLabelPrijmeni = new javax.swing.JLabel();
        jLabelPSC = new javax.swing.JLabel();
        jLabelUlice = new javax.swing.JLabel();
        jTextFieldUlice = new javax.swing.JTextField();
        jTextFieldPopisne = new javax.swing.JTextField();
        jLabelMesto = new javax.swing.JLabel();
        jTextFieldMesto = new javax.swing.JTextField();
        jLabelIDKontakt = new javax.swing.JLabel();
        jTextFieldIDZakaznik = new javax.swing.JTextField();
        jLabelFirma = new javax.swing.JLabel();
        jTextFieldTel = new javax.swing.JTextField();
        jButtonAdd = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jTextFieldFirma = new javax.swing.JTextField();
        jLabelTel = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButtonUpdate = new javax.swing.JButton();

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
        add(jTextFieldPSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 170, 30));

        jTextFieldFind.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        add(jTextFieldFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 440, 30));

        jButtonFind.setText("Hledat");
        jButtonFind.setActionCommand("Najít");
        jButtonFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindActionPerformed(evt);
            }
        });
        add(jButtonFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 100, -1));

        jLabelPopisne.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelPopisne.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPopisne.setText("Číslo popisné");
        add(jLabelPopisne, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 90, 30));
        add(jTextFieldMobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 170, 30));

        jLabelMobil.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelMobil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelMobil.setText("Mobil");
        add(jLabelMobil, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 80, 30));

        jLabelJmeno.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelJmeno.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelJmeno.setText("Jméno");
        add(jLabelJmeno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 90, 30));
        add(jTextFieldJmeno, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 170, 30));
        add(jTextFieldPrijmeni, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 170, 30));

        jLabelPrijmeni.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelPrijmeni.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPrijmeni.setText("Příjmení");
        add(jLabelPrijmeni, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 90, 30));

        jLabelPSC.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelPSC.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelPSC.setText("PSČ");
        add(jLabelPSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 90, 30));

        jLabelUlice.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelUlice.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelUlice.setText("Ulice");
        add(jLabelUlice, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 90, 30));
        add(jTextFieldUlice, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 170, 30));
        add(jTextFieldPopisne, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 170, 30));

        jLabelMesto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelMesto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelMesto.setText("Město");
        add(jLabelMesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 90, 30));
        add(jTextFieldMesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 170, 30));

        jLabelIDKontakt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelIDKontakt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelIDKontakt.setText("Kód zákazníka");
        add(jLabelIDKontakt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 90, 30));

        jTextFieldIDZakaznik.setEditable(false);
        add(jTextFieldIDZakaznik, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 170, 30));

        jLabelFirma.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelFirma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelFirma.setText("Firma");
        add(jLabelFirma, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 80, 30));
        add(jTextFieldTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 170, 30));

        jButtonAdd.setText("Přidat");
        jButtonAdd.setMaximumSize(new java.awt.Dimension(71, 23));
        jButtonAdd.setMinimumSize(new java.awt.Dimension(71, 23));
        jButtonAdd.setPreferredSize(new java.awt.Dimension(71, 23));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        add(jButtonAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 500, 80, 30));

        jButtonDelete.setText("Smazat");
        jButtonDelete.setMaximumSize(new java.awt.Dimension(71, 23));
        jButtonDelete.setMinimumSize(new java.awt.Dimension(71, 23));
        jButtonDelete.setPreferredSize(new java.awt.Dimension(71, 23));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        add(jButtonDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 500, 80, 30));
        add(jTextFieldFirma, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 170, 30));

        jLabelTel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelTel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTel.setText("Telefon");
        add(jLabelTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 80, 30));

        jLabelEmail.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelEmail.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelEmail.setText("Email");
        add(jLabelEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 80, 30));
        add(jTextFieldEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 170, 30));

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

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 560, 90));

        jButtonUpdate.setText("Upravit");
        jButtonUpdate.setMaximumSize(new java.awt.Dimension(71, 23));
        jButtonUpdate.setMinimumSize(new java.awt.Dimension(71, 23));
        jButtonUpdate.setPreferredSize(new java.awt.Dimension(71, 23));
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });
        add(jButtonUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 500, 80, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFindActionPerformed
        refresh();
    }//GEN-LAST:event_jButtonFindActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        modify(Work.insert);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        modify(Work.delete);
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        modify(Work.update);
    }//GEN-LAST:event_jButtonUpdateActionPerformed

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
        insert, update, delete
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonFind;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelFirma;
    private javax.swing.JLabel jLabelIDKontakt;
    private javax.swing.JLabel jLabelJmeno;
    private javax.swing.JLabel jLabelMesto;
    private javax.swing.JLabel jLabelMobil;
    private javax.swing.JLabel jLabelPSC;
    private javax.swing.JLabel jLabelPopisne;
    private javax.swing.JLabel jLabelPrijmeni;
    private javax.swing.JLabel jLabelTel;
    private javax.swing.JLabel jLabelUlice;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldFind;
    private javax.swing.JTextField jTextFieldFirma;
    private javax.swing.JTextField jTextFieldIDZakaznik;
    private javax.swing.JTextField jTextFieldJmeno;
    private javax.swing.JTextField jTextFieldMesto;
    private javax.swing.JTextField jTextFieldMobil;
    private javax.swing.JTextField jTextFieldPSC;
    private javax.swing.JTextField jTextFieldPopisne;
    private javax.swing.JTextField jTextFieldPrijmeni;
    private javax.swing.JTextField jTextFieldTel;
    private javax.swing.JTextField jTextFieldUlice;
    // End of variables declaration//GEN-END:variables
}
