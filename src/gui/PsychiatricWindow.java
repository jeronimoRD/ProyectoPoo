/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import psychiatric.Psychiatric;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import psychiatric.Player;
import psychiatric.Room;

public class PsychiatricWindow extends javax.swing.JFrame {

    private Psychiatric psychiatric;
    private Player player;
    public static final int MAX_OBJECTS = 10;

    public void setPsychiatric(Psychiatric psychiatric) {
        this.psychiatric = psychiatric;
        
    }

    public void setPlayer(Player player){
        this.player = player; 
   }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Image image = createImage(getWidth(), getHeight()); // Crea una imagen temporal del tamaño de la ventana
        Graphics imageGraphics = image.getGraphics(); // Obtiene el contexto gráfico de la imagen

        psychiatric.draw(imageGraphics); // Dibuja el contenido de psychiatric en la imagen
        drawInventory(imageGraphics); // Dibuja el inventario en la imagen

        g.drawImage(image, 0, 0, this); // Dibuja la imagen en el contexto gráfico de la ventana
    }

    private void drawInventory(Graphics g) {

        int slotWidth = 50;
        int slotSpacing = 10;
        int numSlots = 4;
        int initialX = 20;
        int initialY = 47;
        int verticalSpacing = 60;

        final int slotWidthAndSpacing = slotWidth + slotSpacing;

        g.setColor(Color.LIGHT_GRAY);

        for (int i = 0; i < numSlots; i++) {
            int currentY = initialY + verticalSpacing * i;

            Rectangle slot = new Rectangle(initialX, currentY, slotWidth, slotWidth);
            g.fillRect(slot.x, slot.y, slot.width, slot.height);
            g.drawString("" + i, initialX + 2, currentY + 10);
        }

    }

    public PsychiatricWindow() {
        initComponents();
        psychiatric = new Psychiatric();
         Player player = new Player(Room.WIDTH / 2, Room.HEIGHT / 2); // Inicializa el objeto player
    psychiatric.setPlayer(player); // Asigna el jugador al objeto psychiatric
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
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
        if (evt.getKeyChar() == 'q') {
            System.exit(0);
        }

        if (evt.getKeyCode() == KeyEvent.VK_UP | evt.getKeyCode() == KeyEvent.VK_DOWN | evt.getKeyCode() == KeyEvent.VK_RIGHT | evt.getKeyCode() == KeyEvent.VK_LEFT) {
            psychiatric.keyPressed(evt.getKeyCode());
            repaint();
        }
    }//GEN-LAST:event_formKeyPressed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        System.out.println("Mouse clicked");
    if (evt.getButton() == MouseEvent.BUTTON3) {
        player.moveMouse(evt.getButton());
    }
    }//GEN-LAST:event_formMouseClicked

    public static void main(String args[]) {
        PsychiatricWindow psychiatricW = new PsychiatricWindow();
        Psychiatric psychiatric = new Psychiatric();

        // Valores a obtener con psiquiatrico
        psychiatricW.setSize(psychiatric.getWidth(), psychiatric.getHeight());
        psychiatricW.setPsychiatric(psychiatric);
        psychiatricW.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
