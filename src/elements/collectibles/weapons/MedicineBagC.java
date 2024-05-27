/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.collectibles.weapons;

import elements.weapons.cut.MedicineBag;
import java.awt.Color;
import java.awt.Graphics;

public class MedicineBagC extends WeaponC {
    public MedicineBagC(int x, int y) {
        super(x, y, new Color(206, 173, 173));
        weapon = new MedicineBag(null);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
