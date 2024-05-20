/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.weapons;

import elements.bullets.Bullet;
import elements.bullets.LinealBullet;
import elements.player.Player;
import interfaces.Collidable;
import interfaces.Damageable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import threads.CooldownThread;

public class Gun extends Weapon{
    
    //CHARACTERISTICS
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    
    private static final int DAMAGE = 1000;
    private static final int SPEED = 10;
    public static final int COOLDOWN_ATTACK= 500;
    
    private CooldownThread cooldownThread;
    
    public Gun(Player player) {
        super(0, 0, WIDTH, HEIGHT, Color.green, player);
        bullets = new ArrayList<>();
        setDamage(DAMAGE);
        
        cooldownThread = new CooldownThread(COOLDOWN_ATTACK);
        cooldownThread.start();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        ArrayList<Bullet> eliminatedBullets =  new ArrayList<>();
        for(Bullet bullet: bullets){
            if(bullet.isExplode()){
                eliminatedBullets.add(bullet);
            }else{
                bullet.draw(g);
            }
        }
        for(int b = 0; b < eliminatedBullets.size(); b++){
           bullets.remove(eliminatedBullets.get(b));
        }
    }

    public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }
    
    @Override
    public void attack(Player player) {
        ArrayList<Damageable> objectives = null;
        if(player.getCreatures() != null){
            objectives = new ArrayList<>();
            for(Damageable creature: player.getCreatures()){
                objectives.add(creature);
            }
        }
        if(!cooldownThread.isRecover()){
            if(player.getDirection() == Collidable.UP){
                LinealBullet bullet = new LinealBullet(player.getX(), player.getY(), player.UP, DAMAGE, SPEED);
                bullet.setCollidables(player.getBoundables(), objectives);
                addBullet(bullet);
            }
            if(player.getDirection() == Collidable.DOWN){
                LinealBullet bullet = new LinealBullet(player.getX(), player.getY(), player.DOWN, DAMAGE, SPEED);
                bullet.setCollidables(player.getBoundables(), objectives);
                addBullet(bullet);
            }
            if(player.getDirection() == Collidable.RIGHT){
                LinealBullet bullet = new LinealBullet(player.getX(), player.getY(), player.RIGHT, DAMAGE, SPEED);
                bullet.setCollidables(player.getBoundables(), objectives);
                addBullet(bullet);
            }
            if(player.getDirection() == Collidable.LEFT){
                LinealBullet bullet = new LinealBullet(player.getX(), player.getY(), player.LEFT, DAMAGE, SPEED);
                bullet.setCollidables(player.getBoundables(), objectives);
                addBullet(bullet);
                
            }
            cooldownThread.startCoolDown();
        }
    }
}
