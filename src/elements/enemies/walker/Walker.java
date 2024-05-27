/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.enemies.walker;

import elements.enemies.Enemy;
import interfaces.Boundable;
import interfaces.Collidable;
import interfaces.Damageable;
import interfaces.Movable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import threads.TouchCollisionThread;

public abstract class Walker extends Enemy implements Movable{
    //MOVE
    public static final int STEP = 1;
    public static final int COOLDOWNMOVE = 10;
    
    //COLLIDABLES
    private TouchCollisionThread touchCollisionThread;
    
    public Walker(int x, int y, int width, int height, Color color, int lifeBar) {
        super(x, y, width, height, color);
        this.lifeBar = lifeBar; 
        touchCollisionThread = new TouchCollisionThread(this);
        touchCollisionThread.start();
    }
    
    public boolean checkCollisionHitbox(Collidable collidable, int direction) {
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
    public abstract void draw(Graphics g);
    
    public abstract void update();
    
    @Override
    public abstract void touched(Collidable collidable); 
    
    @Override
    public abstract void die();
    
    @Override
    public abstract void setPlayer(Damageable player);
    
    @Override
    public int getStep() {
        return STEP;
    }

    @Override
    public int getCoolDownMove() {
        return COOLDOWNMOVE;
    }
    
    @Override
    public void setBoundables(ArrayList<Boundable> boundables){
        this.boundables = boundables;
        
        ArrayList<Collidable> collidables = new ArrayList<>();
        for(Boundable boundable: boundables){
            collidables.add(boundable);
        }
        
        touchCollisionThread.addCollidable(collidables);
    }
}
