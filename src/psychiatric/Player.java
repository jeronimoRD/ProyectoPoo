/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psychiatric;

import elements.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player extends Sprite{
    
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final int STEP = 10;
    
    public Player(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.CYAN);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
    
    public void move(int code){
        if(code == KeyEvent.VK_UP){
            y -= STEP;
        }
        if(code == KeyEvent.VK_DOWN){
            y += STEP;
        }
        if(code == KeyEvent.VK_RIGHT){
            x += STEP;
        }
        if(code == KeyEvent.VK_LEFT){
            x -= STEP;
        }
    }
}
