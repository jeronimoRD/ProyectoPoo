/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.weapons;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author korez
 */
public class Stick extends Weapon{

    
    public static final int WIDTH = 5;
    public static final int HEIGHT = 30;
    private static final int DAMAGE = 5;
    
    public Stick() {
        super(0, 0, WIDTH, HEIGHT, Color.orange);
    }

    @Override
    public void drawIcon(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
