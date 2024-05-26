/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.weapons.cut;

import sprites.Sprite;
import interfaces.Collidable;
import java.awt.Color;
import java.awt.Graphics;

public class Hitbox extends Sprite implements Collidable{ //EXTEND SPRITE*
    
    private Collidable collidable;
    private int direction;
    
    public Hitbox(int x, int y, int width, int height) {
        super(x, y, width, height, Color.CYAN);
    }
    
    @Override
    public boolean checkCollision(Collidable collidable) {
        //V2
        if((collidable.getY() <= y & y <= collidable.getY() + collidable.getHeight()) & (collidable.getX() <= x & x <= collidable.getX() + collidable.getWidth())){
            return true;
        }else if((collidable.getY() <= y + height & y + height <= collidable.getY() + collidable.getHeight()) & (collidable.getX() <= x + width & x + width <= collidable.getX() + collidable.getWidth())){
            return true;
        }else if((collidable.getY() <= y & y <= collidable.getY() + collidable.getHeight()) & (collidable.getX() <= x + width & x + width <= collidable.getX() + collidable.getWidth())){
            return true;   
        }else if((collidable.getY() <= y + height & y + height <= collidable.getY() + collidable.getHeight()) & (collidable.getX() <= x & x <= collidable.getX() + collidable.getWidth())){
            return true;
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

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public Collidable getCollidable() {
        return collidable;
    }

    public void setCollidable(Collidable collidable) {
        this.collidable = collidable;
    }
}
