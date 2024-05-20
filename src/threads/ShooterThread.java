/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import elements.bullets.Bullet;
import elements.bullets.ChaseBullet;
import elements.bullets.LinealBullet;
import elements.enemies.Shooter;
import interfaces.Damageable;
import java.util.ArrayList;

public class ShooterThread extends Thread{
    protected boolean running;
    
    protected Shooter shooter;
    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    
    protected int cooldown;
    protected int typeBullet;
    
    public ShooterThread(Shooter shooter, boolean up, boolean down, boolean right, boolean left, int cooldown, int typeBullet){
        this.shooter = shooter;
        this.running = true;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.cooldown = cooldown;
        this.typeBullet = typeBullet;
    }
    
    @Override
    public void run(){
        while(running){
            System.out.print("");
            
            if(shooter.getPlayer() != null){
                //HURT
                if(shooter.getPlayer().getActualWeapon() != null){
                    if(shooter.getPlayer().getActualWeapon().isAttacking()){
                        if(shooter.checkCollision(shooter.getPlayer().getActualWeapon().getHitbox())){
                            shooter.takeDamage(shooter.getPlayer().getActualWeapon().getDamage());
                        }
                    }
                    else if(shooter.getPlayer().getInventory().getSelectedWeapon().getBullets() != null){
                        for(Bullet bullet: shooter.getPlayer().getInventory().getSelectedWeapon().getBullets()){
                            if(shooter.checkCollision(bullet)){
                                shooter.takeDamage(shooter.getPlayer().getActualWeapon().getDamage());
                            }
                        }
                    }
                }
                if(typeBullet == 1){
                    ArrayList<Damageable> objective = new ArrayList<>();
                    objective.add(shooter.getPlayer());
                    if(up){
                        LinealBullet bullet = new LinealBullet(shooter.getX(), shooter.getY(), shooter.UP, 0, 0);
                        bullet.setCollidables(shooter.getBoundables(), objective);;
                        shooter.addBullet(bullet);
                    }
                    if(down){
                        LinealBullet bullet = new LinealBullet(shooter.getX(), shooter.getY(), shooter.DOWN, 0, 0);
                        bullet.setCollidables(shooter.getBoundables(), objective);
                        shooter.addBullet(bullet);
                    }
                    if(right){
                        LinealBullet bullet = new LinealBullet(shooter.getX(), shooter.getY(), shooter.RIGHT, 0, 0);
                        bullet.setCollidables(shooter.getBoundables(), objective);
                        shooter.addBullet(bullet);
                    }
                    if(left){
                        LinealBullet bullet = new LinealBullet(shooter.getX(), shooter.getY(), shooter.LEFT, 0, 0);
                        bullet.setCollidables(shooter.getBoundables(), objective);
                        shooter.addBullet(bullet);
                    }

                    try {
                        Thread.sleep(cooldown); //SPEED OF SPAWN BULLET
                    } catch (InterruptedException ex) {
                        System.out.println("ERROR");
                    }
                }else if(typeBullet == 2){
                    ArrayList<Damageable> objective = new ArrayList<>();
                    objective.add(shooter.getPlayer());
                    ChaseBullet bullet = new ChaseBullet(shooter.getX(), shooter.getY(), 0, 0, shooter.getPlayer());
                    bullet.setCollidables(shooter.getBoundables(), objective);
                    shooter.addBullet(bullet);
                    
                    try {
                        Thread.sleep(ChaseBullet.TIME);
                    }catch (InterruptedException ex) {
                        System.out.println("ERROR");
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
    
    //GETTERS AND SETTERS
}
