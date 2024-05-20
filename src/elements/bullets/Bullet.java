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
    
    private BulletThread bulletThread;
    private TouchCollisionThread touchCollisionThread;
    private ArrayList<Boundable> boundables;
    private Damageable player;
    
    private boolean explode;
    
    public Bullet(int x, int y, int direction, int speed) {
        super(x, y, WIDTH, HEIGHT, Color.ORANGE);
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
        if(collidable == player){
            player.takeDamage(0);
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
    
    public void setCollidables(ArrayList<Boundable> boundables, Damageable player) {
        this.boundables = boundables;
        this.player = player;
        
        ArrayList<Collidable> collidables = new ArrayList<>();
        collidables.add(player);
        if(boundables != null){
            for(Boundable boundable: boundables){
                collidables.add(boundable);
            }
            touchCollisionThread.addCollidable(collidables);
        }
    }

    public void setPlayer(Damageable player) {
        this.player = player;
    }

    public Damageable getPlayer() {
        return player;
    }
    
    public BulletThread getBulletThread() {
        return bulletThread;
    }

    public boolean isExplode() {
        return explode;
    }
}
