/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

/**
 *
 * @author deivi
 */
public class SelecPersonajes extends javax.swing.JFrame
{

    //private final Animator aniSM
    /**
     * Creates new form SelecPersonajes
     */
    //  Main sm = new Main();
    public SelecPersonajes()
    {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        snowGirl = new javax.swing.JButton();
        cowboy = new javax.swing.JButton();
        snowMan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        gLCanvas1 = new javax.media.opengl.GLCanvas();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Selecciona personaje");
        setBackground(new java.awt.Color(0, 255, 255));
        setResizable(false);

        snowGirl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/snowgirl.png"))); // NOI18N
        snowGirl.setText("jButton1");
        snowGirl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snowGirlActionPerformed(evt);
            }
        });

        cowboy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cowboy.png"))); // NOI18N
        cowboy.setText("jButton1");
        cowboy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cowboyActionPerformed(evt);
            }
        });

        snowMan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/snowman.png"))); // NOI18N
        snowMan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        snowMan.setMaximumSize(new java.awt.Dimension(432, 337));
        snowMan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snowManActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel1.setText("Selecciona personaje");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("SnowGirl");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("CowBoy");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setText("SnowMan");

        gLCanvas1.setBackground(new java.awt.Color(102, 102, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(snowMan, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(snowGirl, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(cowboy, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(layout.createSequentialGroup()
                .addGap(343, 343, 343)
                .addComponent(gLCanvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jLabel4)
                .addGap(206, 206, 206)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 227, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(104, 104, 104))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(212, 212, 212))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(snowGirl, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cowboy, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(snowMan, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(gLCanvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void snowManActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_snowManActionPerformed
    {//GEN-HEADEREND:event_snowManActionPerformed
        // TODO add your handling code here:
        // DibujaSnowMan sm = new DibujaSnowMan();
        Niveles n = new Niveles(1);
        n.setVisible(rootPaneCheckingEnabled);
        this.dispose();
    }//GEN-LAST:event_snowManActionPerformed

    private void snowGirlActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_snowGirlActionPerformed
    {//GEN-HEADEREND:event_snowGirlActionPerformed
        // TODO add your handling code here:
        //   DibujaSnowGirl sg = new DibujaSnowGirl();
        Niveles n = new Niveles(2);
        n.setVisible(rootPaneCheckingEnabled);
        this.dispose();
    }//GEN-LAST:event_snowGirlActionPerformed

    private void cowboyActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cowboyActionPerformed
    {//GEN-HEADEREND:event_cowboyActionPerformed
        // TODO add your handling code here:
        Niveles n = new Niveles(3);
        n.setVisible(rootPaneCheckingEnabled);
        this.dispose();
    }//GEN-LAST:event_cowboyActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(SelecPersonajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(SelecPersonajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(SelecPersonajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(SelecPersonajes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new SelecPersonajes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cowboy;
    private javax.media.opengl.GLCanvas gLCanvas1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton snowGirl;
    private javax.swing.JButton snowMan;
    // End of variables declaration//GEN-END:variables
}
