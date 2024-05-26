/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.collectibles.weapons;

import sprites.Sprite;
import elements.weapons.Weapon;
import interfaces.Collectible;
import interfaces.Collidable;
import java.awt.Color;
import java.awt.Graphics;

public abstract class WeaponC extends Sprite implements Collectible{
    
    protected static final int TYPE = 0;
    protected boolean grabed;
    protected Weapon weapon;
    
    public WeaponC(int x, int y, Color color) {
        super(x, y, WIDTH, HEIGHT, color);
        this.grabed = false;
    }

    @Override
    public abstract void draw(Graphics g);
    
    @Override
    public Weapon grabWeapon() {
        grabed = true;
        return weapon;
    }

    @Override
    public void throwWeapon(){
        grabed = false;
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
    
    //GETTERS AND SETTERS
    @Override
    public boolean getGrabed(){
        return grabed;
    }
    
    @Override
    public int getType(){
        return TYPE;
    }
}
