/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import elements.bullets.Bullet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author korez
 */
public class BulletThread extends Thread{
    private boolean running;
    private Bullet bullet;
    private int direction;
    private int speed;
    
    public BulletThread(Bullet bullet, int direction, int speed) {
        this.running = true;
        this.bullet = bullet;
        this.direction = direction;
        this.speed = speed;
    }
    
    @Override
    public void run(){
        while(running){
            int py = bullet.getY();
            int px = bullet.getX();
            
            //UP
            if(direction == Bullet.UP){
                py -= bullet.getStep();
                bullet.setY(py);
            //DOWN
            }else if(direction == Bullet.DOWN){
                py += bullet.getStep();
                bullet.setY(py);
            //RIGHT
            }else if(direction == Bullet.RIGHT){
                px += bullet.getStep();
                bullet.setX(px);
            //LEFT
            }else if(direction == Bullet.LEFT){
                px -= bullet.getStep();
                bullet.setX(px);
            }
            
            try {
                Thread.sleep(speed); //SPEED OF BULLET
            } catch (InterruptedException ex) {
                Logger.getLogger(WalkerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //GETTERS AND SETTERS
    public void stopRun(){
        this.running = false;
    }
    public void startRun(){
        this.running = true;
    }

    public int getDirection(){
        return direction;
    }
}
