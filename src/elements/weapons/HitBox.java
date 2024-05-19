/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.weapons;

import elements.Sprite;
import interfaces.Collidable;
import java.awt.Color; // *
import java.awt.Graphics;

public class HitBox extends Sprite implements Collidable{ //EXTEND SPRITE*
    /*
    private int x;
    private int y;
    private int width;
    private int height;
*/

    public HitBox(int x, int y, int width, int height) {
        super(x, y, width, height, Color.CYAN);
        /*
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        */
    }
    
    @Override
    public boolean checkCollision(Collidable collidable) {
        if((collidable.getY() + collidable.getHeight() > y  & y > collidable.getY()) & (collidable.getX() + collidable.getWidth() > x & x > collidable.getX())){
            return true;
        }
        if((collidable.getY() + collidable.getHeight() > y + height & y + height > collidable.getY()) & (collidable.getX() + collidable.getWidth() > x + width & x + width > collidable.getX())){
            return true;
        }
        if((collidable.getY() + collidable.getHeight() > y & y > collidable.getY()) & (collidable.getX() + collidable.getWidth() > x + width & x + width > collidable.getX())){
            return true;
        }
        if((collidable.getY() + collidable.getHeight() > y + height & y + height > collidable.getY()) & (collidable.getX() + collidable.getWidth() > x & x > collidable.getX())){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkCollision(Collidable collidable, int direction) {
        if(direction == UP){
            if(y == collidable.getY() + collidable.getHeight()){
                if(x <= collidable.getX() & collidable.getX() <= x + width){
                    return true;
                }
                else if(x <= collidable.getX() + collidable.getWidth() & collidable.getX() + collidable.getWidth() <= x + width){
                    return true;
                }
            }else{
                return false;
            }
        }
        
        else if(direction == DOWN){
            if(y + height == collidable.getY()){
                if(x <= collidable.getX() & collidable.getX() <= x + width){
                    return true;
                }
                else if(x <= collidable.getX() + collidable.getWidth() & collidable.getX() + collidable.getWidth() <= x + width){
                    return true;
                }
            }else{
                return false;
            }
        }
        
        else if(direction == LEFT){
            if(x == collidable.getX() + collidable.getWidth()){
                if(y <= collidable.getY() & collidable.getY() <= y + height){
                    return true;
                }
                else if(y <= collidable.getY() + collidable.getHeight() & collidable.getY() + collidable.getHeight() <= y + height){
                    return true;
                }
            }else{
                return false;
            }
        }
        
        else if(direction == RIGHT){
            if(x + width == collidable.getX()){
                if(y <= collidable.getY() & collidable.getY() <= y + height){
                    return true;
                }
                else if(y <= collidable.getY() + collidable.getHeight() & collidable.getY() + collidable.getHeight() <= y + height){
                    return true;
                }
            }else{
                return false;
            }
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
    
    //GETTERS AND SETTERS
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
