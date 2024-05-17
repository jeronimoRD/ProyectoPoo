/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psychiatric;

import elements.Sprite;
import interfaces.Collidable;
import java.awt.Color;
import java.awt.Graphics;

public class Reward extends Sprite implements Collidable{
    
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    public Reward(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.YELLOW);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT);
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
    public boolean checkCollisionHitbox(Collidable collidable, int direction) {
        if(direction == UP){
            if(y == collidable.getY() + collidable.getHeight()){
                return true;
            }else{
                return false;
            }
        }
        
        else if(direction == DOWN){
            if(y + height == collidable.getY()){
                return true;
            }else{
                return false;
            }
        }
        
        else if(direction == LEFT){
            if(x == collidable.getX() + collidable.getWidth()){
                return true;
            }else{
                return false;
            }
        }
        
        else if(direction == RIGHT){
            if(x + width == collidable.getX()){
                return true;
            }else{
                return false;
            }
        }
        
        /*
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
        */
        return false;
    }    
}
