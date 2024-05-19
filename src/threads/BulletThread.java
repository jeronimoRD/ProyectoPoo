/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import elements.bullets.Bullet;
import interfaces.Collidable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author korez
 */
public class BulletThread extends Thread{
    private volatile boolean paused;
    private boolean running;
    private Bullet bullet;
    private int direction;
    
    public BulletThread(Bullet bullet) {
        this.running = false;
        this.paused = false;
        this.bullet = bullet;
    }
    
    @Override
    public void run(){
        while(running){
            int py = bullet.getY();
            int px = bullet.getX();
            
            //UP
            if(direction == Bullet.UP){
                py -= Bullet.STEP;
                bullet.setY(py);
                for(Collidable collision: bullet.getBoundables()){
                    if(bullet.checkCollision(collision)){
                        if(collision != bullet & collision != bullet.getPlayer()){
                            running = false;
                            bullet.setExplode(true);
                        }
                    }
                }
            //DOWN
            }else if(direction == Bullet.DOWN){
                py += Bullet.STEP;
                bullet.setY(py);
                for(Collidable collision: bullet.getBoundables()){
                    if(bullet.checkCollision(collision)){
                        if(collision != bullet & collision != bullet.getPlayer()){
                            running = false;
                            bullet.setExplode(true);
                        }
                    }
                }
            //RIGHT
            }else if(direction == Bullet.RIGHT){
                px += Bullet.STEP;
                bullet.setX(px);
                for(Collidable collision: bullet.getBoundables()){
                    if(bullet.checkCollision(collision)){
                        if(collision != bullet & collision != bullet.getPlayer()){
                            running = false;
                            bullet.setExplode(true);
                        }
                    }
                }
            //LEFT
            }else if(direction == Bullet.LEFT){
                px -= Bullet.STEP;
                bullet.setX(px);
                for(Collidable collision: bullet.getBoundables()){
                    if(bullet.checkCollision(collision)){
                        if(collision != bullet & collision != bullet.getPlayer()){
                            running = false;
                            bullet.setExplode(true);
                        }
                    }
                }
            }
            
            try {
                Thread.sleep(50); //SPEED OF BULLET
            } catch (InterruptedException ex) {
                Logger.getLogger(WalkerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void pause(){
        this.paused = !this.paused;
    }
    
    public void halt(){ //Stop
        this.running = false;
    }
    
    //GETTERS AND SETTERS
    public boolean isRunning(){
        return running;
    }

    public void setRunning(boolean running){
        this.running = running;
    }

    public int getDirection(){
        return direction;
    }

    public void setDirection(int direction){
        this.direction = direction;
    }
}
