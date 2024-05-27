/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.enemies.shooter;

import elements.bullets.ChaseBullet;
import elements.bullets.LinealBullet;
import interfaces.Damageable;
import java.util.ArrayList;

public class ShooterThread extends Thread{
    protected boolean running;
    private boolean pause;
    
    protected Shooter shooter;
    
    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    
    protected int cooldown;
    private int step;
    private int cooldownBullet;
    protected int typeBullet;
    
    public ShooterThread(Shooter shooter, boolean up, boolean down, boolean right, boolean left, int cooldown, int step, int cooldownBullet, int typeBullet){
        this.shooter = shooter;
        this.running = true;
        pause = false;
        
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        
        this.cooldown = cooldown;
        this.step = step;
        this.cooldownBullet = cooldownBullet;
        this.typeBullet = typeBullet;
    }
    
    @Override
    public void run(){
        while(running){
            System.out.print("");
            if(!pause){
                if(shooter.getPlayer() != null){
                    ArrayList<Damageable> objective = new ArrayList<>();
                    objective.add(shooter.getPlayer());
                    if(typeBullet == 1){
                        if(up){
                            LinealBullet bullet = new LinealBullet(shooter.getX(), shooter.getY(), shooter.UP, step, cooldownBullet);
                            bullet.setCollidables(shooter.getBoundables(), objective);;
                            shooter.addBullet(bullet);
                        }
                        if(down){
                            LinealBullet bullet = new LinealBullet(shooter.getX(), shooter.getY(), shooter.DOWN, step, cooldownBullet);
                            bullet.setCollidables(shooter.getBoundables(), objective);
                            shooter.addBullet(bullet);
                        }
                        if(right){
                            LinealBullet bullet = new LinealBullet(shooter.getX(), shooter.getY(), shooter.RIGHT, step, cooldownBullet);
                            bullet.setCollidables(shooter.getBoundables(), objective);
                            shooter.addBullet(bullet);
                        }
                        if(left){
                            LinealBullet bullet = new LinealBullet(shooter.getX(), shooter.getY(), shooter.LEFT, step, cooldownBullet);
                            bullet.setCollidables(shooter.getBoundables(), objective);
                            shooter.addBullet(bullet);
                        }
                        try {
                            Thread.sleep(cooldown);
                        } catch (InterruptedException ex) {
                            System.out.println("ERROR");
                        }
                    }else if(typeBullet == 2){
                        ChaseBullet bullet = new ChaseBullet(shooter.getX(), shooter.getY(), step, cooldownBullet, shooter.getPlayer());
                        bullet.setCollidables(shooter.getBoundables(), objective);
                        shooter.addBullet(bullet);
                        try {
                            Thread.sleep(cooldown); 
                        } catch (InterruptedException ex) {
                            System.out.println("ERROR");
                        }
                        if(shooter.bullets != null & shooter.getLifeBar() > 0){
                            try{
                                if(shooter.bullets.get(0) != null){
                                    shooter.bullets.get(0).explode(); 
                                }
                            }catch(IndexOutOfBoundsException e){}
                        }
                    }
                }
            }
        }
    }
    
    public void stopRun(){
        this.running = false;
    }
    public void startRun(){
        this.running = true;
    }
    
    public void setPause(boolean pause){
        this.pause = pause;
    }
}
