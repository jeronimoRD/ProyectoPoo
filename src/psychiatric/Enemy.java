/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psychiatric;

import elements.Sprite;
import interfaces.Collidable;
import interfaces.Damageable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Enemy extends Sprite implements Damageable{ //IS COLLIDABLE TOO
    
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    
    private ArrayList<Collidable> collisions;
    private Damageable player;
    
    public Enemy(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.RED);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
    
    public void move(){
        if(this.checkCollision(player)){ //TEST
            System.out.println("TOMA"); //TEST
        }
    }
    
    public void setDamageable(Damageable damageable){
        this.player = damageable;
    }
    
    public void setCollisions(ArrayList<Collidable> collisions) {
        this.collisions = collisions;
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
