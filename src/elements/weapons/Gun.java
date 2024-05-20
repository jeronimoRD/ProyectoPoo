/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.weapons;

import elements.bullets.Bullet;
import elements.player.Player;
import interfaces.Collidable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import threads.CooldownThread;

public class Gun extends Weapon{
    
    //CHARACTERISTICS
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    
    private static final int DAMAGE = 100;
    private static final int SPEED = 10;
    public static final int COOLDOWN_ATTACK= 500;
    
    private ArrayList<Bullet> bullets;
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
        if(!cooldownThread.isRecover()){
            if(player.getDirection() == Collidable.UP){
                Bullet bullet = new Bullet(player.getX(), player.getY(), player.UP, SPEED);
                bullet.setCollidables(player.getBoundables(), null);
                addBullet(bullet);
                
            }
            if(player.getDirection() == Collidable.DOWN){
                Bullet bullet = new Bullet(player.getX(), player.getY(), player.DOWN, SPEED);
                bullet.setCollidables(player.getBoundables(), null);
                addBullet(bullet);
                
            }
            if(player.getDirection() == Collidable.RIGHT){
                Bullet bullet = new Bullet(player.getX(), player.getY(), player.RIGHT, SPEED);
                bullet.setCollidables(player.getBoundables(), null);
                addBullet(bullet);
                
            }
            if(player.getDirection() == Collidable.LEFT){
                Bullet bullet = new Bullet(player.getX(), player.getY(), player.LEFT, SPEED);
                bullet.setCollidables(player.getBoundables(), null);
                addBullet(bullet);
                
            }
            cooldownThread.startCoolDown();
        }
    }
}
