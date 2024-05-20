/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import elements.bullets.Bullet;
import elements.enemies.Shooter;

public class ShooterThread extends Thread{
    private boolean running;
    
    private Shooter shooter;
    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;
    
    private int cooldown;
    
    public ShooterThread(Shooter shooter, boolean up, boolean down, boolean right, boolean left, int cooldown){
        this.shooter = shooter;
        this.running = true;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.cooldown = cooldown;
    }
    
    @Override
    public void run(){
        while(running){
            //HURT ITSELF
            /*
            if(shooter.getPlayer().getActualWeapon().getHitbox() != null){
                if(shooter.getPlayer().getActualWeapon().getHitbox().checkCollision(shooter)){
                    shooter.takeDamage(shooter.getPlayer().getActualWeapon().getDamage());
                }
            }
            */
            if(up){
                Bullet bullet = new Bullet(shooter.getX(), shooter.getY(), shooter.UP);
                bullet.setBoundables(shooter.getBoundables());
                bullet.setPlayer(shooter.getPlayer()); //? MACHETE
                shooter.addBullet(bullet);
            }
            if(down){
                Bullet bullet = new Bullet(shooter.getX(), shooter.getY(), shooter.DOWN);
                bullet.setBoundables(shooter.getBoundables());
                bullet.setPlayer(shooter.getPlayer()); //? MACHETE
                shooter.addBullet(bullet);
            }
            if(right){
                Bullet bullet = new Bullet(shooter.getX(), shooter.getY(), shooter.RIGHT);
                bullet.setBoundables(shooter.getBoundables());
                bullet.setPlayer(shooter.getPlayer()); //? MACHETE
                shooter.addBullet(bullet);
            }
            if(left){
                Bullet bullet = new Bullet(shooter.getX(), shooter.getY(), shooter.LEFT);
                bullet.setBoundables(shooter.getBoundables());
                bullet.setPlayer(shooter.getPlayer()); //? MACHETE
                shooter.addBullet(bullet);
            }
            
            try {
                Thread.sleep(cooldown); //SPEED OF SPAWN BULLET
            } catch (InterruptedException ex) {
                System.out.println("ERROR");
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
