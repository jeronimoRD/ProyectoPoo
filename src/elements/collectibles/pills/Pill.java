/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.collectibles.pills;

import sprites.Sprite;
import elements.weapons.Weapon;
import interfaces.Collectible;
import interfaces.Collidable;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Pill extends Sprite implements Collectible{

    protected boolean grabed;
    
    public Pill(int x, int y, Color color) {
        super(x, y, WIDTH, HEIGHT, color);
    }

    @Override
    public abstract void draw(Graphics g);

    @Override
    public boolean getGrabed() {
        return grabed;
    }
    
    @Override
    public abstract int getType();

    @Override
    public Weapon grabWeapon() {
        return null;
    }

    @Override
    public void throwWeapon(){};
    
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
