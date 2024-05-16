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
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends Sprite implements Damageable{ //IS COLLIDABLE TOO
    
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final int STEP = 10;
    
    private ArrayList<Collidable> collisions;
    
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
        if(code == KeyEvent.VK_UP){
            y -= STEP;
            for(Collidable collision: collisions){
                if(checkCollisionHitbox(collision)){
                    y = collision.getY()+collision.getHeight();
                    return;
                }
            }
        }
        if(code == KeyEvent.VK_DOWN){
            y += STEP;
            for(Collidable collision: collisions){
                if(checkCollisionHitbox(collision)){
                    y = collision.getY() - HEIGHT;
                    return;
                }
            }
        }
        if(code == KeyEvent.VK_RIGHT){
            x += STEP;
            for(Collidable collision: collisions){
                if(checkCollisionHitbox(collision)){
                    x = collision.getX() - WIDTH;
                    return;
                }
            }
        }
        if(code == KeyEvent.VK_LEFT){
            x -= STEP;
            for(Collidable collision: collisions){
                if(checkCollisionHitbox(collision)){
                    x = collision.getX()+collision.getWidth();
                    return;
                }
            }
        }
    }

    @Override
    public boolean checkCollisionHitbox(Collidable collidable) {
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

    @Override
    public boolean checkCollisionTouch(Collidable collidable) {
        if(collidable.getY() == y + height | collidable.getY() + collidable.getHeight() == y){
            if(x <= collidable.getX() & collidable.getX() <= x + width){
                return true;
            }
            else if(x <= collidable.getX() + collidable.getWidth() & collidable.getX() + collidable.getWidth() <= x + width){
                return true;
            }
        }
        if(collidable.getX() == x + width | collidable.getX() + collidable.getWidth() == x){
            if(y <= collidable.getY() & collidable.getY() <= y + height){
                return true;
            }
            else if(y <= collidable.getY() + collidable.getHeight() & collidable.getY() + collidable.getHeight() <= y + height){
                return true;
            }
        }
        return false;
    }
}
