/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quadrants;

import java.awt.Color;
import java.awt.Graphics;

public class Door extends Quadrant{
    
    public Door(int x, int y) {
        super(x, y, Color.ORANGE);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
