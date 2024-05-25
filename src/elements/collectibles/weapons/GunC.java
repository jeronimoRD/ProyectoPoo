/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.collectibles.weapons;

import elements.weapons.large.GunLineal;
import java.awt.Color;
import java.awt.Graphics;

public class GunC extends WeaponC{
    
    public GunC(int x, int y) {
        super(x, y, Color.GREEN);
        weapon = new GunLineal(null);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
