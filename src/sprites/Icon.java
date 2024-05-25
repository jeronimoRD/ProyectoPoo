/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sprites;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Icon extends Sprite{

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    
    public Icon(int x, int y, Color color) {
        super(x, y, WIDTH, HEIGHT, color);
    }

    @Override
    public abstract void draw(Graphics g);
}
