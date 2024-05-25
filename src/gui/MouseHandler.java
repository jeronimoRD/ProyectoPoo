/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import psychiatric.Psychiatric;

/**
 *
 * @author korez
 */
public class MouseHandler implements MouseListener{
    
    private Psychiatric pyschiatric;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pyschiatric.mousePressed(e.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public void setPyschiatric(Psychiatric pyschiatric) {
        this.pyschiatric = pyschiatric;
    }
}
