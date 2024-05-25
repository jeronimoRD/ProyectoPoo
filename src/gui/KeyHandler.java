/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import psychiatric.Psychiatric;

/**
 *
 * @author korez
 */
public class KeyHandler implements KeyListener{
    
    private Psychiatric pyschiatric;
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        pyschiatric.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pyschiatric.keyPressed(e.getKeyCode());
    }

    public void setPyschiatric(Psychiatric pyschiatric) {
        this.pyschiatric = pyschiatric;
    }
}
