/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.enemies;

import sprites.Sprite;
import interfaces.Boundable;
import interfaces.Collidable;
import interfaces.Damageable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Enemy extends Sprite implements Damageable{
    
    //BOUNDABLE
    protected ArrayList<Boundable> boundables;
    
    //PLAYER
    protected Damageable player;
    
    //CHARACTERISTICS
    protected int lifeBar;
    
    public Enemy(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
    
    @Override
    public void takeDamage(int damage) {
        int actualLife = lifeBar - damage;
        if(actualLife <= 0){
            die();
            actualLife = 0;
        }
        lifeBar = actualLife;
    }
    
    @Override
    public abstract void die(); //DAMAGEABLE
    
    @Override
    public boolean checkCollision(Collidable collidable) { // =?
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
    public void setBoundables(ArrayList<Boundable> boundables){
        this.boundables = boundables;
    }
    
    public ArrayList<Boundable> getBoundables() {
        return boundables;
    }
    
    public abstract void setPlayer(Damageable player);

    public Damageable getPlayer() {
        return player;
    }
 
    public int getLifeBar() {
        return lifeBar;
    }
}
