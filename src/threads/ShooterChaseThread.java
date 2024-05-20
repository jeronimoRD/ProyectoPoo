/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import elements.bullets.Bullet;
import elements.bullets.ChaseBullet;
import elements.enemies.Shooter;
import interfaces.Damageable;
import java.util.ArrayList;
/*
public class ShooterChaseThread extends ShooterThread{
    
    public ShooterChaseThread(Shooter shooter, boolean up, boolean down, boolean right, boolean left, int cooldown) {
        super(shooter, up, down, right, left, cooldown);
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
                ArrayList<Damageable> objective = new ArrayList<>();
                objective.add(shooter.getPlayer());
                
                ChaseBullet bullet = new ChaseBullet(shooter.getX(), shooter.getY(), shooter.UP, 50, shooter.getPlayer());
                bullet.setCollidables(shooter.getBoundables(), objective);;
                shooter.addBullet(bullet);
                
                try {
                    Thread.sleep(cooldown); //SPEED OF SPAWN BULLET
                } catch (InterruptedException ex) {
                    System.out.println("ERROR");
                }
            }
        }
    }

    
}*/
