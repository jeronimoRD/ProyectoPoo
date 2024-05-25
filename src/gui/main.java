/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.JFrame;
import psychiatric.Psychiatric;

/**
 *
 * @author korez
 */
public class main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        GaP panel = new GaP();
        ventana.add(panel);
        ventana.pack();
        Psychiatric pyschiatric = new Psychiatric();
        panel.setPyschiatric(pyschiatric);
        
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        panel.empezarHilo();
    }
    
}
