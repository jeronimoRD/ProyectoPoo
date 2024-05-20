/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.bullets;

import elements.Sprite;
import interfaces.Boundable;
import interfaces.Collidable;
import interfaces.Damageable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import threads.BulletThread;
import threads.TouchCollisionThread;

public class Bullet extends Sprite implements Collidable{

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    public static final int STEP = 5;
    private int damage;
    
    private BulletThread bulletThread;
    private TouchCollisionThread touchCollisionThread;
    private ArrayList<Boundable> boundables;
    private ArrayList<Damageable> objectives;
    
    private boolean explode;
    
    public Bullet(int x, int y, int direction, int speed, int damage) {
        super(x, y, WIDTH, HEIGHT, Color.ORANGE);
        this.damage = damage;
        boundables = new ArrayList<>();
        
        
        explode = false;
        
        bulletThread = new BulletThread(this, direction, speed);
        bulletThread.start();
        
        touchCollisionThread = new TouchCollisionThread(this);
        touchCollisionThread.start();
    }
    
    @Override
    public void touched(Collidable collidable) {
        for(Boundable boundable: boundables){
            if(collidable == boundable){
                explode = true;
            }
        }
        if(objectives != null){
            for(Damageable damageable: objectives){
                if(collidable == damageable){
                    damageable.takeDamage(damage);
                    explode = true;
                }
            }
        }
    }
    
    @Override
    public void draw(Graphics g) {
        if(!explode){
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
    }

    @Override
    public boolean checkCollision(Collidable collidable) {
        //PUNTO ARRIBA-IZQUIERDO
        if((collidable.getY() <= y & y <= collidable.getY() + collidable.getHeight()) & (collidable.getX() <= x & x <= collidable.getX() + collidable.getWidth())){
            return true;
        //PUNTO ABAJO-DERECHA
        }else if((collidable.getY() <= y + height & y + height <= collidable.getY() + collidable.getHeight()) & (collidable.getX() <= x + width & x + width <= collidable.getX() + collidable.getWidth())){
            return true;
         //PUNTO ARRIBA-DERECHA
        }else if((collidable.getY() <= y & y <= collidable.getY() + collidable.getHeight()) & (collidable.getX() <= x + width & x + width <= collidable.getX() + collidable.getWidth())){
            return true;
        //PUNTO ABAJO-IZQUIERDA    
        }else if((collidable.getY() <= y + height & y + height <= collidable.getY() + collidable.getHeight()) & (collidable.getX() <= x & x <= collidable.getX() + collidable.getWidth())){
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

    public ArrayList<Boundable> getBoundables() {
        return boundables;
    }
    
    public void setCollidables(ArrayList<Boundable> boundables, ArrayList<Damageable> objectives) {
        this.boundables = boundables;
        this.objectives = objectives;
        
        ArrayList<Collidable> collidables = new ArrayList<>();
        if(boundables != null){
            for(Boundable boundable: boundables){
                collidables.add(boundable);
            }
        }
        if(objectives != null){
            for(Damageable damageable: objectives){
                collidables.add(damageable);
            }
        }
        touchCollisionThread.addCollidable(collidables);
    }

    public void setObjectives(ArrayList<Damageable> objectives) {
        this.objectives = objectives;
    }
    
    public BulletThread getBulletThread() {
        return bulletThread;
    }

    public boolean isExplode() {
        return explode;
    }
    
    
}
