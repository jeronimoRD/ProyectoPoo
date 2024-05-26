/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import psychiatric.Psychiatric;

public class KeyHandler implements KeyListener{
    
    private Psychiatric pyschiatric;
    private ArrayList<Integer> keys = new ArrayList<>();
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) { //Presionar
        if(!keys.contains(e.getKeyCode())){
            keys.add(e.getKeyCode()); 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { //Liberar
        keys.remove(Integer.valueOf(e.getKeyCode()));
    }

    public ArrayList<Integer> getKeys() {
        return keys;
    }
    
    public void setPyschiatric(Psychiatric pyschiatric) {
        this.pyschiatric = pyschiatric;
    }
}
