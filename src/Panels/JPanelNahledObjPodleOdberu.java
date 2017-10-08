
package Panels;

import Frames.NahledyFrame;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
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
public class JPanelNahledObjPodleOdberu extends javax.swing.JPanel {

    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    private static final String URL = "jdbc:mysql://localhost/objednavky?useUnicode=true&characterEncoding=UTF-8";

    public JPanelNahledObjPodleOdberu() {
        initComponents();
        myInit();
        jComboBoxDoprava.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                myInit();
            }
        });
    }

    public final void myInit() {

        String[] sloupce = {"Příjmeni", "Jméno", "Číslo objednávky", "Město", "Datum objednání"};
        DefaultTableModel model = new DefaultTableModel(sloupce, 0);
        jTable1.setModel(model);

        if (jComboBoxDoprava.getSelectedIndex() == 0) {
            try {
                Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");
                String selectSQL = "SELECT DISTINCT PRIJMENI, JMENO, objednavky.CISLO_OBJ,"
                        + " MESTO, objednavky.DATUM_OBJ FROM zakaznici JOIN objednavky"
                        + " ON zakaznici.ID_ZAKAZNIK = objednavky.ID_ZAKAZNIK"
                        + " WHERE objednavky.ID_DOPRAVA = 1 ORDER BY PRIJMENI";
                PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
                ResultSet rset = preparedStatement.executeQuery();

                while (rset.next()) {

                    String prijmeni = rset.getString("PRIJMENI");
                    String jmeno = rset.getString("JMENO");
                    int cisloObj = rset.getInt("CISLO_OBJ");
                    String mesto = rset.getString("MESTO");
                    String datumObj = format.format(rset.getDate("DATUM_OBJ"));
                    //  String email = "";
                    Vector<Object> radek = new Vector<>();

                    radek.add(prijmeni);
                    radek.add(jmeno);
                    radek.add(cisloObj);
                    radek.add(mesto);
                    radek.add(datumObj);
                    //  radek.add(email);
                    model.addRow(radek);

                }
            } catch (SQLException ex) {
                Logger.getLogger(NahledyFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");
                String selectSQL = "SELECT DISTINCT PRIJMENI, JMENO, objednavky.CISLO_OBJ,"
                        + " MESTO, objednavky.DATUM_OBJ FROM zakaznici JOIN objednavky"
                        + " ON zakaznici.ID_ZAKAZNIK = objednavky.ID_ZAKAZNIK"
                        + " WHERE objednavky.ID_DOPRAVA = 2 ORDER BY PRIJMENI";
                PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
                ResultSet rset = preparedStatement.executeQuery();

                while (rset.next()) {

                    String prijmeni = rset.getString("PRIJMENI");
                    String jmeno = rset.getString("JMENO");
                    int cisloObj = rset.getInt("CISLO_OBJ");
                    String mesto = rset.getString("MESTO");
                    String datumObj = format.format(rset.getDate("DATUM_OBJ"));
                    //  String email = "";
                    Vector<Object> radek = new Vector<>();

                    radek.add(prijmeni);
                    radek.add(jmeno);
                    radek.add(cisloObj);
                    radek.add(mesto);
                    radek.add(datumObj);
                    //  radek.add(email);
                    model.addRow(radek);

                }
            } catch (SQLException ex) {
                Logger.getLogger(NahledyFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonPrint = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabelNadpis1 = new javax.swing.JLabel();
        jComboBoxDoprava = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(204, 255, 153));
        setMaximumSize(new java.awt.Dimension(1000, 600));
        setMinimumSize(new java.awt.Dimension(1000, 600));

        jButtonPrint.setText("Tisk");
        jButtonPrint.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintActionPerformed(evt);
            }
        });

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

        jLabelNadpis1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelNadpis1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNadpis1.setText("Objednávky podle odběru");
        jLabelNadpis1.setPreferredSize(new java.awt.Dimension(100, 30));

        jComboBoxDoprava.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Poštou", "Osobně" }));
        jComboBoxDoprava.setMinimumSize(new java.awt.Dimension(100, 30));
        jComboBoxDoprava.setPreferredSize(new java.awt.Dimension(100, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(373, 373, 373)
                .addComponent(jLabelNadpis1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(377, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBoxDoprava, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(391, 391, 391))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(23, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 954, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(23, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabelNadpis1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 464, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxDoprava, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(86, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(87, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintActionPerformed

        MessageFormat header = new MessageFormat("Objednávky podle odběru - poštou");
        if (jComboBoxDoprava.getSelectedIndex() == 1) {
            header = new MessageFormat("Objednávky podle odběru - osobně");
        }

        MessageFormat footer = new MessageFormat("Strana {0}");

        try {
            boolean complete = jTable1.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            if (complete) {
                JOptionPane.showMessageDialog(new JFrame(), "Tisk dokončen");
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Tisk zrušen");
            }
        } catch (PrinterException pe) {
            JOptionPane.showMessageDialog(new JFrame(), "Nastala chyba při tisku");
        }
    }//GEN-LAST:event_jButtonPrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JComboBox<String> jComboBoxDoprava;
    private javax.swing.JLabel jLabelNadpis1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
