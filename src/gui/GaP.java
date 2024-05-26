/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import psychiatric.Psychiatric;

/**
 *
 * @author korez
 */
public class GaP extends javax.swing.JPanel implements Runnable {
    
    private Thread hilo;
    private Psychiatric pyschiatric;
    private KeyHandler kh = new KeyHandler();
    private MouseHandler mh = new MouseHandler();
    
    public GaP(){
        initComponents();
        this.setPreferredSize(new Dimension(1000, 900));
        this.setBackground(Color.RED);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.addMouseListener(mh);
        this.setFocusable(true); //?
    }
    
    public void setPyschiatric(Psychiatric pyschiatric) {
        this.pyschiatric = pyschiatric;
        kh.setPyschiatric(pyschiatric);
        mh.setPyschiatric(pyschiatric);
    }
    
    public void empezarHilo() {
        hilo = new Thread(this);
        hilo.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        pyschiatric.draw(g2);
        g2.dispose();
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

    @Override
    public void run() {
        double intervalo = 1000000000/60;
        double delta = 0;
        long lasttime = System.nanoTime();
        long currentime;
        while(hilo!=null){
            currentime = System.nanoTime();
            delta += (currentime-lasttime)/intervalo;
            lasttime = currentime;
            
            if(delta >= 1){
                pyschiatric.keyPressed(kh.getKeys());
                repaint();
                delta--;
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
