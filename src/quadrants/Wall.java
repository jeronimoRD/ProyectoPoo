/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quadrants;

import interfaces.Collidable;
import java.awt.Color;
import java.awt.Graphics;

public class Wall extends Quadrant implements Collidable{

    public Wall(int x, int y) {
        super(x, y, Color.BLACK);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    @Override
    public boolean checkCollision(Collidable collidable) {
        return true;
    }
}
