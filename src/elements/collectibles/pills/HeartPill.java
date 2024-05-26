/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.collectibles.pills;

import java.awt.Color;
import java.awt.Graphics;

public class HeartPill extends Pill{

    private static final int TYPE = 1;
    
    public HeartPill(int x, int y) {
        super(x, y, Color.RED);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    @Override
    public int getType() {
        return TYPE;
    }
}
