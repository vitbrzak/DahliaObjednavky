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
public class JPanelZakazniciEshop extends javax.swing.JPanel {

    String OXID;

    public JPanelZakazniciEshop(String OXID) throws SQLException {
        this.OXID = OXID;
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

        try {
            Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");
            String selectSQL = "SELECT OXBILLFNAME, OXBILLLNAME, OXBILLSTREET, OXBILLSTREETNR, OXBILLCITY, OXBILLZIP,"
                    + " OXBILLCOMPANY, OXBILLFON, OXBILLEMAIL FROM OXORDER WHERE OXID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
            preparedStatement.setString(1, OXID);
            ResultSet rset = preparedStatement.executeQuery();

            while (rset.next()) {
                String jmeno = rset.getString("OXBILLFNAME");
                String prijmeni = rset.getString("OXBILLLNAME");
                String ulice = rset.getString("OXBILLSTREET");
                String popisne = rset.getString("OXBILLSTREETNR");
                String mesto = rset.getString("OXBILLCITY");
                String psc = Integer.toString(rset.getInt("OXBILLZIP"));
                String firma = rset.getString("OXBILLCOMPANY");
                String mobil = rset.getString("OXBILLFON");
                String email = rset.getString("OXBILLEMAIL");

                jTextFieldJmeno.setText(jmeno);
                jTextFieldPrijmeni.setText(prijmeni);
                jTextFieldUlice.setText(ulice);
                jTextFieldPopisne.setText(popisne);
                jTextFieldMesto.setText(mesto);
                jTextFieldPSC.setText(psc);
                jTextFieldFirma.setText(firma);
                jTextFieldMobil.setText(mobil);
                jTextFieldEmail.setText(email);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JPanelZakazniciEshop.class.getName()).log(Level.SEVERE, null, ex);
        }

        jTable1.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (jTable1.getSelectedRow() > -1) {
                jTextFieldIDZakaznik.setText(jTable1.getValueAt((int) jTable1.getSelectedRow(), 0).toString());
                jTextFieldJmeno.setText(jTable1.getValueAt((int) jTable1.getSelectedRow(), 1).toString());
                jTextFieldPrijmeni.setText(jTable1.getValueAt((int) jTable1.getSelectedRow(), 2).toString());
                jTextFieldUlice.setText(jTable1.getValueAt((int) jTable1.getSelectedRow(), 3).toString());
                jTextFieldPopisne.setText(jTable1.getValueAt((int) jTable1.getSelectedRow(), 4).toString());
                jTextFieldMesto.setText(jTable1.getValueAt((int) jTable1.getSelectedRow(), 5).toString());
                jTextFieldPSC.setText(jTable1.getValueAt((int) jTable1.getSelectedRow(), 6).toString());
                jTextFieldTel.setText(jTable1.getValueAt((int) jTable1.getSelectedRow(), 8).toString());
                jTextFieldEmail.setText(jTable1.getValueAt((int) jTable1.getSelectedRow(), 9).toString());
            }
        });

        try {
            Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");
            String selectSQL = "SELECT DISTINCT stav.CISLO_OBJ, OXORDERDATE, OXDELTYPE"
                    + " FROM OXORDER JOIN stav ON OXORDER.OXID = OXORDER.OXID"
                    + " WHERE OXORDER.OXID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
            preparedStatement.setString(1, OXID);
            ResultSet rset = preparedStatement.executeQuery();

            while (rset.next()) {
                int cisloObj = rset.getInt(1);
                String datumObj = format.format(rset.getDate("OXORDERDATE"));
                String doprava = "";
                if (rset.getString("OXDELTYPE").equals("9ee963415d05f96506e9b951b4c3a60c")) {
                    doprava = "Poštou";
                } else if (rset.getString("OXDELTYPE").equals("9eed66c9dcab62d43e376f1e2dbd6e89")) {
                    doprava = "Osobně";
                }
                Vector<Object> radek = new Vector<>();

                radek.add(cisloObj);
                radek.add(datumObj);
                radek.add(doprava);
                model.addRow(radek);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JPanelZakazniciEshop.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                            Logger.getLogger(JPanelZakazniciEshop.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Není vybrán zákazník");
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
                        ObjednavkaFrame frame = new ObjednavkaFrame(1,OXID,Integer.parseInt(jTextFieldIDZakaznik.getText()), idObj, cisloObj, datum, doprava);
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
        jButtonSetZakaznik = new javax.swing.JButton();
        jTextFieldFirma = new javax.swing.JTextField();
        jLabelTel = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

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

        jButtonFind.setText("Hledat");
        jButtonFind.setActionCommand("Najít");
        jButtonFind.setMaximumSize(new java.awt.Dimension(71, 23));
        jButtonFind.setMinimumSize(new java.awt.Dimension(71, 23));
        jButtonFind.setPreferredSize(new java.awt.Dimension(71, 23));
        jButtonFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindActionPerformed(evt);
            }
        });
        add(jButtonFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 120, 30));

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

        jButtonSetZakaznik.setText("Potvrdit zákazníka");
        jButtonSetZakaznik.setMaximumSize(new java.awt.Dimension(71, 23));
        jButtonSetZakaznik.setMinimumSize(new java.awt.Dimension(71, 23));
        jButtonSetZakaznik.setPreferredSize(new java.awt.Dimension(71, 23));
        jButtonSetZakaznik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSetZakaznikActionPerformed(evt);
            }
        });
        add(jButtonSetZakaznik, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 500, 120, 30));
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
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFindActionPerformed
        find();
    }//GEN-LAST:event_jButtonFindActionPerformed

    private void jButtonSetZakaznikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSetZakaznikActionPerformed
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
            String email = jTextFieldEmail.getText();

            Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");
            String sql = "";

            if (jTextFieldIDZakaznik.getText().equals("")) {
                sql = "INSERT INTO zakaznici(JMENO, PRIJMENI, ULICE, POPISNE, MESTO, PSC, FIRMA, TEL, MOBIL, EMAIL)"
                        + " VALUES (?,?,?,?,?,?,?,?,?,?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, jmeno);
                    stmt.setString(2, prijmeni);
                    stmt.setString(3, ulice);
                    stmt.setString(4, popisne);
                    stmt.setString(5, mesto);
                    stmt.setInt(6, Integer.parseInt(psc));
                    stmt.setString(7, firma);
                    stmt.setString(8, tel);
                    stmt.setString(9, mobil);
                    stmt.setString(10, email);

                    stmt.executeUpdate();
                    stmt.close();
                    jTextFieldIDZakaznik.setText(Integer.toString(maxID()));
                }
            } else {
                sql = "UPDATE zakaznici SET JMENO = ?, PRIJMENI = ?, ULICE = ?, POPISNE = ?, MESTO = ?, PSC = ?, FIRMA = ?,"
                        + " TEL = ?, MOBIL = ?, EMAIL = ? WHERE ID_ZAKAZNIK = ?";

                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, jmeno);
                    stmt.setString(2, prijmeni);
                    stmt.setString(3, ulice);
                    stmt.setString(4, popisne);
                    stmt.setString(5, mesto);
                    stmt.setInt(6, Integer.parseInt(psc));
                    stmt.setString(7, firma);
                    stmt.setString(8, tel);
                    stmt.setString(9, mobil);
                    stmt.setString(10, email);
                    stmt.setInt(11, Integer.parseInt(idZak));

                    stmt.executeUpdate();
                    stmt.close();
                    //    refresh();
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JFrame(), "Chyba zadání");
        }
        JOptionPane.showMessageDialog(new JFrame(), "Zákazník úspěšně potvrzen");
    }//GEN-LAST:event_jButtonSetZakaznikActionPerformed

    private void find() {

        try {
            Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");
            String selectSQL = "SELECT ID_ZAKAZNIK, JMENO, PRIJMENI, ULICE, POPISNE, MESTO, PSC, FIRMA, TEL, MOBIL, EMAIL"
                    + " FROM zakaznici WHERE LOWER(JMENO) LIKE LOWER(?) AND LOWER(PRIJMENI) LIKE LOWER(?)"
                    + " ORDER BY ID_ZAKAZNIK ASC";
            PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
            preparedStatement.setString(1, "%" + jTextFieldJmeno.getText() + "%");
            preparedStatement.setString(2, "%" + jTextFieldPrijmeni.getText() + "%");
            ResultSet rset = preparedStatement.executeQuery();

            String[] sloupce = {"Kód zákazníka", "Jméno", "Příjmeni", "Ulice", "Popisné", "Město", "PSČ", "Firma", "Telefon", "Mobil", "Email"};
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
                String email = rset.getString("EMAIL");

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
                radek.add(email);
                model.addRow(radek);
            }

            jDialog1.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(JPanelZakazniciEshop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refresh() {

        try {
            int idZak = Integer.parseInt(jTextFieldIDZakaznik.getText());
            Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");
            String selectSQL = "SELECT ID_ZAKAZNIK, JMENO, PRIJMENI, ULICE, POPISNE, MESTO, PSC, FIRMA, TEL, MOBIL, EMAIL"
                    + " FROM zakaznici WHERE ID_ZAKAZNIK = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idZak);
            ResultSet rset = preparedStatement.executeQuery();

            while (rset.next()) {
                String jmeno = rset.getString("JMENO");
                String prijmeni = rset.getString("PRIJMENI");
                String ulice = rset.getString("ULICE");
                String popisne = rset.getString("POPISNE");
                String mesto = rset.getString("MESTO");
                String psc = Integer.toString(rset.getInt("PSC"));
                String firma = rset.getString("FIRMA");
                String tel = rset.getString("TEL");
                String mobil = rset.getString("MOBIL");
                String email = rset.getString("EMAIL");

                jTextFieldJmeno.setText(jmeno);
                jTextFieldPrijmeni.setText(prijmeni);
                jTextFieldUlice.setText(ulice);
                jTextFieldPopisne.setText(popisne);
                jTextFieldMesto.setText(mesto);
                jTextFieldPSC.setText(psc);
                jTextFieldFirma.setText(firma);
                jTextFieldTel.setText(tel);
                jTextFieldMobil.setText(mobil);
                jTextFieldEmail.setText(email);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JPanelZakazniciEshop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int maxID() {
        int maxID = 0;
        try {
            Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");

            PreparedStatement stmtID = conn.prepareStatement("SELECT MAX(ID_ZAKAZNIK) FROM zakaznici");
            ResultSet rs = stmtID.executeQuery();
            if (rs.next()) {
                maxID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JPanelObjednavka.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maxID;
    }

    private void clear() {
        jTextFieldIDZakaznik.setText("");
        jTextFieldJmeno.setText("");
        jTextFieldPrijmeni.setText("");
        jTextFieldUlice.setText("");
        jTextFieldPopisne.setText("");
        jTextFieldMesto.setText("");
        jTextFieldPSC.setText("");
        jTextFieldFirma.setText("");
        jTextFieldMobil.setText("");
        jTextFieldEmail.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFind;
    private javax.swing.JButton jButtonSetZakaznik;
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
