/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psychiatric;

import elements.Sprite;
import interfaces.Collidable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends Sprite implements Collidable{
    
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final int STEP = 10;
    
    private ArrayList<Collidable> collisions;
    private static final int WALL = 1;
    private static final int DOOR = 2;
    
    
    public Player(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.CYAN);
    }

    public void setCollisions(ArrayList<Collidable> collisions) {
        this.collisions = collisions;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
    
    public void move(int code){
        int px = x;
        int py = y;
        
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
        
        for(Collidable collision: collisions){
            if(checkCollision(collision)){
                x = px;
                y = py;
                return;
            }
        }
    }
    
    @Override
    public boolean checkCollision(Collidable collidable) {
        if((collidable.getY() + collidable.getHeight() > y  & y >= collidable.getY()) & (collidable.getX() + collidable.getWidth() > x & x >= collidable.getX())){
            return true;
        }
        if((collidable.getY() + collidable.getHeight() >= y + height & y + height > collidable.getY()) & (collidable.getX() + collidable.getWidth() >= x + width & x + width > collidable.getX())){
            return true;
        }
        if((collidable.getY() + collidable.getHeight() > y & y > collidable.getY()) & (collidable.getX() + collidable.getWidth() >= x + width & x + width > collidable.getX())){
            return true;
        }
        if((collidable.getY() + collidable.getHeight() >= y + height & y + height > collidable.getY()) & (collidable.getX() + collidable.getWidth() > x & x > collidable.getX())){
            return true;
        }
        return false;
    }
}
