/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.enemies;

import elements.Sprite;
import elements.player.Player;
import threads.TouchCollisionThread;
import interfaces.Boundable;
import interfaces.Collidable;
import interfaces.Damageable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Creature extends Sprite implements Damageable{
    
    //BOUNDABLE
    protected ArrayList<Boundable> boundables;
    
    //PLAYER
    protected Player player;
    
    //CHARACTERISTICS
    protected int lifeBar;
    
    //COLLIDABLES
    private TouchCollisionThread touchCollisionThread;
    
    public Creature(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        touchCollisionThread = new TouchCollisionThread(this);
        touchCollisionThread.start();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
    
    @Override
    public void touched(Collidable collidable) {
        
    }
    
    @Override
    public void takeDamage(int damage) {
        int actualLife = lifeBar - damage;
        if(actualLife < 0){
            actualLife = 0;
        }
        lifeBar = actualLife;
    }
    
    @Override
    public abstract void die(); //DAMAGEABLE
    
    @Override
    public boolean checkCollision(Collidable collidable) { // =?
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
    
    //GETTERS AND SETTERS
    public void setPlayer(Player player){
        this.player = player;
        
        if(player.getActualWeapon().getHitbox() != null){
            touchCollisionThread.addCollidable(player.getActualWeapon().getHitbox());
        }
    }

    public Player getPlayer() {
        return player;
    }
    
    public ArrayList<Boundable> getBoundables() {
        return boundables;
    }
    
    public void setBoundables(ArrayList<Boundable> boundables) {
        this.boundables = boundables;
    }

    public int getLifeBar() {
        return lifeBar;
    }
    
}
