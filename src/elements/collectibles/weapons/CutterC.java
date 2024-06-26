/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.collectibles.weapons;

import elements.weapons.cut.Cutter;
import java.awt.Color;
import java.awt.Graphics;

public class CutterC extends WeaponC{
    
    public CutterC(int x, int y){
        super(x, y, new Color(71, 62, 62));
        weapon = new Cutter(null);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
