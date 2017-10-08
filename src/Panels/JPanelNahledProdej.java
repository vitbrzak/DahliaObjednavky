package Panels;

import Frames.NahledyFrame;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.ParseException;
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
public class JPanelNahledProdej extends javax.swing.JPanel {

    private static final String URL = "jdbc:mysql://localhost/objednavky?useUnicode=true&characterEncoding=UTF-8";
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    static java.util.Date datumOd;
    static java.util.Date datumDo;
    String datumOdNadpis;
    String datumDoNadpis;

    public JPanelNahledProdej(String datumOd, String datumDo) {
        try {
            JPanelNahledProdej.datumOd = format.parse(datumOd);
            JPanelNahledProdej.datumDo = format.parse(datumDo);
            this.datumOdNadpis = datumOd;
            this.datumDoNadpis = datumDo;
        } catch (ParseException ex) {
            Logger.getLogger(JPanelNahledProdej.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        try {
            myInit();
        } catch (SQLException ex) {
            Logger.getLogger(JPanelNahledProdej.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public final void myInit() throws SQLException {

        String[] sloupce = {"Číslo odrůdy", "Název", "Objednané kusy"};
        DefaultTableModel model = new DefaultTableModel(sloupce, 0);
        jTable1.setModel(model);
        Connection conn = (Connection) DriverManager.getConnection(URL, "root", "");

        jLabelDatum.setText(datumOdNadpis + " - " + datumDoNadpis);
        int celkem = 0;

        for (int i = 1; i < 165; i++) {
            try {
                String selectSQL = "SELECT jiriny.CISLO, NAZEV, SUM(polozky.MNOZSTVI)"
                        + " FROM jiriny JOIN polozky ON jiriny.ID_JIRINA = polozky.ID_JIRINA"
                        + " JOIN objednavky ON polozky.ID_OBJEDNAVKA = objednavky.ID_OBJEDNAVKA"
                        + " WHERE polozky.ID_JIRINA = ? AND (objednavky.DATUM_OBJ >= ? AND objednavky.DATUM_OBJ <= ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
                preparedStatement.setInt(1, i);
                preparedStatement.setDate(2, new java.sql.Date(datumOd.getTime()));
                preparedStatement.setDate(3, new java.sql.Date(datumDo.getTime()));
                ResultSet rset = preparedStatement.executeQuery();

                while (rset.next()) {

                    int cislo = rset.getInt("CISLO");
                    String nazev = rset.getString("NAZEV");
                    int mnozstvi = rset.getInt(3);
                    Vector<Object> radek = new Vector<>();

                    radek.add(cislo);
                    radek.add(nazev);
                    radek.add(mnozstvi);
                    celkem = celkem + mnozstvi;
                    model.addRow(radek);

                }
            } catch (SQLException ex) {
                Logger.getLogger(NahledyFrame.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
        model.addRow(new Vector<>());
        
        Vector<Object> radek = new Vector<>();
        radek.add("");
        radek.add("Celkový součet");
        radek.add(celkem);
        model.addRow(radek);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonPrint = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabelNadpis1 = new javax.swing.JLabel();
        jLabelDatum = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 153));
        setMaximumSize(new java.awt.Dimension(1000, 600));
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setPreferredSize(new java.awt.Dimension(1000, 600));

        jButtonPrint.setText("Tisk");
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
        jLabelNadpis1.setText("Prodej podle odrůd");
        jLabelNadpis1.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabelDatum.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelDatum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDatum.setMaximumSize(new java.awt.Dimension(250, 30));
        jLabelDatum.setMinimumSize(new java.awt.Dimension(250, 30));
        jLabelDatum.setPreferredSize(new java.awt.Dimension(250, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(373, 373, 373)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNadpis1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 954, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(447, 447, 447))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabelNadpis1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintActionPerformed

        MessageFormat header = new MessageFormat("Prodej podle odrůd:  " + datumOdNadpis + " - " + datumDoNadpis);
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
    private javax.swing.JLabel jLabelDatum;
    private javax.swing.JLabel jLabelNadpis1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
