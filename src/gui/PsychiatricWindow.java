/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;
import interfaces.Drawable;
import java.awt.Graphics;
import java.awt.Image;
import psychiatric.Psychiatric;
import java.awt.event.KeyEvent;

public class PsychiatricWindow extends javax.swing.JFrame implements Drawable{

    private Psychiatric psychiatric;
    
    public void setPsychiatric(Psychiatric psychiatric){
        this.psychiatric = psychiatric;
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Image image = createImage(this.getWidth(), getHeight());
        psychiatric.draw(image.getGraphics());
        
        g.drawImage(image, 0, 0, null);
    }
    
    public PsychiatricWindow() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if(evt.getKeyChar() == 'q'){
            System.exit(0);
        }
        
        if(evt.getKeyCode() == KeyEvent.VK_UP | evt.getKeyCode() == KeyEvent.VK_DOWN | evt.getKeyCode() == KeyEvent.VK_RIGHT | evt.getKeyCode() == KeyEvent.VK_LEFT){
            psychiatric.keyPressed(evt.getKeyCode());
            repaint();
        }
    }//GEN-LAST:event_formKeyPressed

    public static void main(String args[]) {
        PsychiatricWindow psychiatricW = new PsychiatricWindow();
        Psychiatric psychiatric = new Psychiatric();
        // Valores a obtener con psiquiatrico
        
        // se debe implementar luego de tener el resto de clases con drawable
        psychiatric.setDrawable(psychiatricW);
        
        psychiatricW.setSize(psychiatric.getWidth(), psychiatric.getHeight());
        psychiatricW.setPsychiatric(psychiatric);
        psychiatricW.setVisible(true);
    }

    @Override
    public void redraw() {
        repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
