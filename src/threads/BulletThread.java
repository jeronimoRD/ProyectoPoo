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
    
    public BulletThread(Bullet bullet, int direction) {
        this.running = true;
        this.bullet = bullet;
        this.direction = direction;
    }
    
    @Override
    public void run(){
        while(running){
            System.out.println("");
            int py = bullet.getY();
            int px = bullet.getX();
            
            //UP
            if(direction == Bullet.UP){
                py -= Bullet.STEP;
                bullet.setY(py);
            //DOWN
            }else if(direction == Bullet.DOWN){
                py += Bullet.STEP;
                bullet.setY(py);
            //RIGHT
            }else if(direction == Bullet.RIGHT){
                px += Bullet.STEP;
                bullet.setX(px);
            //LEFT
            }else if(direction == Bullet.LEFT){
                px -= Bullet.STEP;
                bullet.setX(px);
            }
            
            try {
                Thread.sleep(50); //SPEED OF BULLET
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
