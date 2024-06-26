/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.bullets;

import sprites.Sprite;
import interfaces.Boundable;
import interfaces.Collidable;
import interfaces.Damageable;
import interfaces.Movable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import threads.TouchCollisionThread;

public abstract class Bullet extends Sprite implements Collidable, Movable{
    
    //CHARACTERISTICS
    protected int step;
    protected int cooldownMove;
    protected int damage;
    protected int timeStunned;
    protected boolean explode;
    
    //KNOW
    protected ArrayList<Boundable> boundables;
    protected ArrayList<Damageable> objectives;
    
    protected TouchCollisionThread touchCollisionThread;
    
    public Bullet(int x, int y, int width, int height, Color color, int timeStunned) {
        super(x, y, width, height, color);
        
        boundables = new ArrayList<>();
        objectives = new ArrayList<>();
        explode = false;
        
        this.timeStunned = timeStunned;
        
        touchCollisionThread = new TouchCollisionThread(this);
        touchCollisionThread.start();
    }
    
    @Override
    public void touched(Collidable collidable) {
        for(Boundable boundable: boundables){
            if(collidable == boundable){
                explode();
            }
        }
        if(objectives != null){
            for(Damageable damageable: objectives){
                if(collidable == damageable){
                    damageable.takeDamage(damage, timeStunned);
                    explode();
                }
            }
        }
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
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
    
    //GETTERS AND SETTERS
    public void setCollidables(ArrayList<Boundable> boundables, ArrayList<Damageable> objectives) {
        this.boundables = boundables;
        this.objectives = objectives;
        
        ArrayList<Collidable> collidables = new ArrayList<>();
        
        for(Boundable boundable: boundables){
            collidables.add(boundable);
        }
        for(Damageable damageable: objectives){
            collidables.add(damageable);
        }
        touchCollisionThread.addCollidable(collidables);
    }
    
    public abstract void explode();
    
    public boolean isExplode() {
        return explode;
    }
    
    @Override
    public int getStep() {
        return step;
    }
    
    @Override
    public int getCoolDownMove() {
        return cooldownMove;
    }
    
}
