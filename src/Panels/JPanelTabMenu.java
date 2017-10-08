/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Panels;

import javax.swing.JTabbedPane;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public final class JPanelTabMenu extends javax.swing.JPanel {
    private final Border bGreyLine;
    
    public JPanelTabMenu() throws SQLException {
        super(new GridLayout(1, 1));
        
        JTabbedPane tabbedPane = new JTabbedPane();
        bGreyLine = BorderFactory.createLineBorder(Color.GRAY, 1, true);
        
        JComponent panel1 = new JPanelZakaznici();
        panel1.setBorder(bGreyLine);
        tabbedPane.addTab("Objednávky podle zákazníka", panel1);
        
        JComponent panel2 = new JPanelNahledy();
        panel2.setBorder(bGreyLine);
        tabbedPane.addTab("Náhledy", panel2);
        
        JComponent panel3 = new JPanelEshop();
        panel3.setBorder(bGreyLine);
        tabbedPane.addTab("Nahrát z Eshopu", panel3);

        
        add(tabbedPane);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    
    private static void createAndShowGUI() throws SQLException {
        JFrame frame = new JFrame("ribbonMenu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new JPanelTabMenu(), BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UIManager.put("swing.boldmetal", Boolean.FALSE);
                try {
                    createAndShowGUI();
                } catch (SQLException ex) {
                    Logger.getLogger(JPanelTabMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
