package Panels;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vitbr
 */
public class JPanelObjednavkaEshop extends javax.swing.JPanel {

    /**
     * Creates new form JPanelObjednavka
     */
    int idZakaznik;
    int idObj;
    int cisloObj;
    String datum;
    int doprava;
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public JPanelObjednavkaEshop(int idZakaznik, int idObj, int cisloObj, String datum, int doprava) {
        this.idZakaznik = idZakaznik;
        this.idObj = idObj;
        this.cisloObj = cisloObj;
        this.datum = datum;
        this.doprava = doprava;
        initComponents();
        myInit();
    }

    private static final String URL = "jdbc:mysql://localhost/objednavky?useUnicode=true&characterEncoding=UTF-8";

    public final void myInit() {

        String[] sloupce = {"Číslo jiřiny", "Název jiřiny", "Množství"};
        DefaultTableModel model = new DefaultTableModel(sloupce, 0);
        jTable1.setModel(model);

        if (cisloObj != 0) {
            jTextFieldCisloObj.setText("" + cisloObj);
            jFormattedTextFieldDatum.setText(datum);
            jComboBoxDoprava.setSelectedIndex(doprava);

            try {
                Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");
                String selectSQL = "SELECT polozky.ID_JIRINA, jiriny.NAZEV, MNOZSTVI"
                        + " FROM polozky JOIN jiriny ON polozky.ID_JIRINA = jiriny.ID_jirina"
                        + " WHERE polozky.ID_OBJEDNAVKA = ? ORDER BY polozky.ID_JIRINA";
                PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
                preparedStatement.setInt(1, idObj);
                ResultSet rset = preparedStatement.executeQuery();

                while (rset.next()) {

                    int idJirina = rset.getInt("ID_JIRINA");
                    String nazev = rset.getString("NAZEV");
                    int mnozstvi = rset.getInt("MNOZSTVI");
                    Vector<Object> radek = new Vector<>();

                    radek.add(idJirina);
                    radek.add(nazev);
                    radek.add(mnozstvi);
                    model.addRow(radek);
                }
            } catch (SQLException ex) {
                Logger.getLogger(JPanelZakaznici.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        model.addRow(new Vector<>());

        //Vlastní funkce ENTERu
        jTable1.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        jTable1.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (jTable1.isCellSelected(jTable1.getSelectedRow(), 0)) {
                    
                    
                    if (jTable1.getCellEditor() != null) {
                        jTable1.getCellEditor().stopCellEditing();
                    }
                    
                    if (!"".equals(jTable1.getValueAt(jTable1.getSelectedRow(), 0))) {
                        int cislo = Integer.parseInt(jTable1.getValueAt((int) jTable1.getSelectedRow(), 0).toString());
                        String nazev = "";

                        try {
                            Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");

                            PreparedStatement stmtNazev = conn.prepareStatement("SELECT NAZEV FROM jiriny WHERE CISLO = ?");
                            stmtNazev.setInt(1, cislo);
                            ResultSet rs = stmtNazev.executeQuery();
                            if (rs.next()) {
                                nazev = rs.getString(1);
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(JPanelObjednavkaEshop.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        jTable1.setValueAt(nazev, (int) jTable1.getSelectedRow(), 1);
                        jTable1.changeSelection(jTable1.getSelectedRow(), 2, false, false);
                        jTable1.setValueAt("1", (int) jTable1.getSelectedRow(), 2);
                    } else {
                        model.removeRow(jTable1.getSelectedRow());
                        jTable1.changeSelection(jTable1.getRowCount()-1, 0, false, false);
                    }
                    
                } else if (jTable1.isCellSelected(jTable1.getSelectedRow(), 2)) {
                    if (jTable1.getValueAt(jTable1.getRowCount() - 1, 0) != null) {
                        model.addRow(new Vector<>());
                    }
                    if (jTable1.getCellEditor() != null) {
                        jTable1.getCellEditor().stopCellEditing();
                    }
                    jTable1.changeSelection(jTable1.getSelectedRow() + 1, 0, false, false);
                }
            }

        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelDatumObj = new javax.swing.JLabel();
        jLabelCisloObj = new javax.swing.JLabel();
        jTextFieldCisloObj = new javax.swing.JTextField();
        jLabelDatumObj1 = new javax.swing.JLabel();
        jComboBoxDoprava = new javax.swing.JComboBox<>();
        jFormattedTextFieldDatum = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonAdd = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 255, 153));
        setMinimumSize(new java.awt.Dimension(600, 610));
        setPreferredSize(new java.awt.Dimension(600, 610));

        jLabelDatumObj.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelDatumObj.setText("Datum objednávky");

        jLabelCisloObj.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelCisloObj.setText("Číslo objednávky");

        jLabelDatumObj1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelDatumObj1.setText("Způsob odběru");

        jComboBoxDoprava.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Poštou", "Osobně" }));
        jComboBoxDoprava.setMinimumSize(new java.awt.Dimension(170, 30));
        jComboBoxDoprava.setPreferredSize(new java.awt.Dimension(170, 30));

        jFormattedTextFieldDatum.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd.MM.yyyy"))));
        jFormattedTextFieldDatum.setMinimumSize(new java.awt.Dimension(170, 30));
        jFormattedTextFieldDatum.setPreferredSize(new java.awt.Dimension(170, 30));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
        jTable1.setCellSelectionEnabled(true);
        jTable1.setNextFocusableComponent(jButtonAdd);
        jTable1.setRowHeight(30);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable1);

        jButtonAdd.setText("Přidat");
        jButtonAdd.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonAdd.setMinimumSize(new java.awt.Dimension(80, 30));
        jButtonAdd.setPreferredSize(new java.awt.Dimension(80, 30));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonUpdate.setText("Upravit");
        jButtonUpdate.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonUpdate.setMinimumSize(new java.awt.Dimension(80, 30));
        jButtonUpdate.setPreferredSize(new java.awt.Dimension(80, 30));
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Smazat");
        jButtonDelete.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonDelete.setMinimumSize(new java.awt.Dimension(80, 30));
        jButtonDelete.setPreferredSize(new java.awt.Dimension(80, 30));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelCisloObj)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldCisloObj, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelDatumObj)
                                    .addComponent(jLabelDatumObj1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jFormattedTextFieldDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxDoprava, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCisloObj, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCisloObj, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDatumObj, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDatumObj1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDoprava, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        try {
            java.util.Date datumObj = format.parse(jFormattedTextFieldDatum.getText());
            Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");
            String sql = "";
            sql = "INSERT INTO objednavky(CISLO_OBJ, ID_ZAKAZNIK, ID_DOPRAVA, DATUM_OBJ)"
                    + " VALUES (?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(jTextFieldCisloObj.getText()));
            stmt.setInt(2, idZakaznik);
            stmt.setInt(3, jComboBoxDoprava.getSelectedIndex() + 1);
            stmt.setDate(4, new java.sql.Date(datumObj.getTime()));

            stmt.executeUpdate();
            stmt.close();

            int maxIDObj = 0;
            
            PreparedStatement stmtObj = conn.prepareStatement("SELECT MAX(ID_OBJEDNAVKA) FROM objednavky");
            ResultSet rs = stmtObj.executeQuery();
            if (rs.next()) {
                maxIDObj = rs.getInt(1);
            }

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                Object value = jTable1.getValueAt(i, 0);
                if (value != null && !"".equals(value)) {
                    sql = "INSERT INTO polozky(ID_OBJEDNAVKA, ID_JIRINA, MNOZSTVI)"
                            + " VALUES (?,?,?)";
                    PreparedStatement stmt2 = conn.prepareStatement(sql);
                    stmt2.setInt(1, maxIDObj);
                    stmt2.setInt(2, Integer.parseInt(jTable1.getValueAt(i, 0).toString()));
                    stmt2.setInt(3, Integer.parseInt(jTable1.getValueAt(i, 2).toString()));

                    stmt2.executeUpdate();
                    stmt2.close();
                }
            }

            JOptionPane.showMessageDialog(new JFrame(), "Objednávka úspěšně přidána");

            //Zavreni frame
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();

        } catch (SQLException | ParseException ex) {
            Logger.getLogger(JPanelObjednavkaEshop.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        try {
            java.util.Date datumObj = format.parse(jFormattedTextFieldDatum.getText());
            Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");
            String sql = "";
            sql = "UPDATE objednavky SET CISLO_OBJ = ?, ID_DOPRAVA = ?, DATUM_OBJ = ?"
                    + " WHERE ID_OBJEDNAVKA = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(jTextFieldCisloObj.getText()));
            stmt.setInt(2, jComboBoxDoprava.getSelectedIndex() + 1);
            stmt.setDate(3, new java.sql.Date(datumObj.getTime()));
            stmt.setInt(4, idObj);

            stmt.executeUpdate();
            stmt.close();

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                Object value = jTable1.getValueAt(i, 0);
                if (value != null && !"".equals(value)) {
                    sql = "DELETE FROM polozky WHERE ID_OBJEDNAVKA = ?";
                    PreparedStatement stmt2 = conn.prepareStatement(sql);
                    stmt2.setInt(1, idObj);

                    stmt2.executeUpdate();
                    stmt2.close();
                }
            }

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                Object value = jTable1.getValueAt(i, 0);
                if (value != null && !"".equals(value)) {
                    sql = "INSERT INTO polozky(ID_OBJEDNAVKA, ID_JIRINA, MNOZSTVI)"
                            + " VALUES (?,?,?)";
                    PreparedStatement stmt3 = conn.prepareStatement(sql);
                    stmt3.setInt(1, idObj);
                    stmt3.setInt(2, Integer.parseInt(jTable1.getValueAt(i, 0).toString()));
                    stmt3.setInt(3, Integer.parseInt(jTable1.getValueAt(i, 2).toString()));

                    stmt3.executeUpdate();
                    stmt3.close();
                }
            }

            JOptionPane.showMessageDialog(new JFrame(), "Objednávka úspěšně upravena");

            //Zavreni frame
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();

        } catch (SQLException | ParseException ex) {
            Logger.getLogger(JPanelObjednavkaEshop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        try {
            Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");

            for (int i = 0; i < jTable1.getRowCount(); i++) {
                Object value = jTable1.getValueAt(i, 0);
                if (value != null && !"".equals(value)) {
                    String sql = "DELETE FROM polozky WHERE ID_OBJEDNAVKA = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, idObj);

                    stmt.executeUpdate();
                    stmt.close();
                }
            }

            String sql = "DELETE FROM objednavky WHERE ID_OBJEDNAVKA = ?";
            PreparedStatement stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, idObj);

            stmt2.executeUpdate();
            stmt2.close();

            JOptionPane.showMessageDialog(new JFrame(), "Objednávka úspěšně smazána");

            //Zavreni frame
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose();

        } catch (SQLException ex) {
            Logger.getLogger(JPanelObjednavkaEshop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<String> jComboBoxDoprava;
    private javax.swing.JFormattedTextField jFormattedTextFieldDatum;
    private javax.swing.JLabel jLabelCisloObj;
    private javax.swing.JLabel jLabelDatumObj;
    private javax.swing.JLabel jLabelDatumObj1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldCisloObj;
    // End of variables declaration//GEN-END:variables

}
