/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import elements.Bullet;
import enemies.Shooter;

public class ShooterThread extends Thread{
    private volatile boolean paused;
    private boolean running;
    
    private Shooter shooter;
    private int direction;
    
    public ShooterThread(Shooter shooter){
        this.shooter = shooter;
        this.running = false;
        this.paused = false;
    }
    
    @Override
    public void run(){
        while(running){
            Bullet bullet = new Bullet(shooter.getX(), shooter.getY());
            bullet.setCollidables(shooter.getCollidables());
            bullet.setPlayer(shooter.getPlayer());
            shooter.addBullet(bullet);
            bullet.move(direction);
            
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                System.out.println("ERROR");
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

    public void setDirection(int direction) {
        this.direction = direction;
    }
    
}
