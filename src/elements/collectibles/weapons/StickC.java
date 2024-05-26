/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.collectibles.weapons;

import elements.weapons.cut.Stick;
import java.awt.Color;
import java.awt.Graphics;

public class StickC extends WeaponC{
    
    public StickC(int x, int y) {
        super(x, y, Color.YELLOW);
        weapon = new Stick(null);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
