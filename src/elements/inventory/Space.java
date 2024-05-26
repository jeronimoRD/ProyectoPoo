/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.inventory;

import elements.weapons.Weapon;
import sprites.Sprite;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author korez
 */
public class Space extends Sprite{
    public static final int WIDTH = 60;
    public static final int HEIGHT = 60;
    
    private Weapon weapon;
    
    public Space(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.DARK_GRAY);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT);
        if(weapon != null){
            g.setColor(Color.GREEN);
            g.fillRect(x, y, WIDTH, HEIGHT);
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
