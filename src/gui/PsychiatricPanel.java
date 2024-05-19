/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import psychiatric.Psychiatric;

/**
 *
 * @author isabela
 */
public class PsychiatricPanel extends JPanel{
    private Psychiatric psychiatric;
    private BufferedImage bufferedImage;

    public void setPsychiatric(Psychiatric psychiatric){
        this.psychiatric = psychiatric;
        // Inicializar el bufferedImage cuando se establece la instancia de Psychiatric
        bufferedImage = new BufferedImage(psychiatric.getScreenWidth(), psychiatric.getScreenHeight(), BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (bufferedImage != null) {
            Graphics2D g2d = bufferedImage.createGraphics();
            psychiatric.draw(g2d);
            g2d.dispose();

            g.drawImage(bufferedImage, 0, 0, this);
        }
    }
}
