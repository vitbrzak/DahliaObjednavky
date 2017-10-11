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
import javax.swing.JOptionPane;
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

    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    private static final String URL = "jdbc:mysql://localhost/objednavky?useUnicode=true&characterEncoding=UTF-8";

    public final void myInit() {

        jDialog1.setLocationRelativeTo(null);
        
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
            JOptionPane.showMessageDialog(new JFrame(), "Chyba databáze");
        }

        jTable2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    try {
                        int sRow = jTable2.getSelectedRow();
                        String OXID = vOXID.elementAt(sRow).toString();
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
        jButtonRefresh = new javax.swing.JButton();

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

        jButtonRefresh.setText("Obnovit");
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });
        add(jButtonRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 500, 80, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        myInit();
    }//GEN-LAST:event_jButtonRefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
