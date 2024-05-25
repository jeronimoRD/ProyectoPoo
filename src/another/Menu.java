/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package another;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author korez
 */
public class Menu extends Sprite{

    public Menu(int x, int y, int width, int height) {
        super(x, y, width, height, Color.PINK);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
