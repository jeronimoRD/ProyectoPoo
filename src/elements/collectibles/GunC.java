/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.collectibles;

import elements.weapons.Gun;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author korez
 */
public class GunC extends WeaponC{
    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;
    
    public GunC(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.GREEN);
        weapon = new Gun(null);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}