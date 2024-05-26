/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.weapons.large;

import elements.bullets.Bullet;
import elements.bullets.LinealBullet;
import elements.player.Player;
import elements.weapons.Weapon;
import interfaces.Collidable;
import interfaces.Damageable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import threads.CooldownThread;

public class GunLineal extends Weapon{
    
    //CHARACTERISTICS
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    
    //BULLETS
    private static final int SPEED = 1;
    private static final int COOLDOWN_MOVE = 1;
    private ArrayList<LinealBullet> bullets;
    
    public static final int COOLDOWN_ATTACK= 500;
    
    private CooldownThread cooldownThread;
    
    public GunLineal(Player player){ //INTERFACE?
        super(0, 0, WIDTH, HEIGHT, Color.green, player);
        bullets = new ArrayList<>();
        
        cooldownThread = new CooldownThread(COOLDOWN_ATTACK);
        cooldownThread.start();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        
        for(Bullet bullet: bullets){
            bullet.draw(g);
        }
        
        update();
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
                LinealBullet bullet = new LinealBullet(player.getX(), player.getY(), player.UP, SPEED, COOLDOWN_MOVE);
                bullet.setCollidables(player.getBoundables(), objectives);
                bullets.add(bullet);
            }
            if(player.getDirection() == Collidable.DOWN){
                LinealBullet bullet = new LinealBullet(player.getX(), player.getY(), player.DOWN, SPEED, COOLDOWN_MOVE);
                bullet.setCollidables(player.getBoundables(), objectives);
                bullets.add(bullet);
            }
            if(player.getDirection() == Collidable.RIGHT){
                LinealBullet bullet = new LinealBullet(player.getX(), player.getY(), player.RIGHT, SPEED, COOLDOWN_MOVE);
                bullet.setCollidables(player.getBoundables(), objectives);
                bullets.add(bullet);
            }
            if(player.getDirection() == Collidable.LEFT){
                LinealBullet bullet = new LinealBullet(player.getX(), player.getY(), player.LEFT, SPEED, COOLDOWN_MOVE);
                bullet.setCollidables(player.getBoundables(), objectives);
                bullets.add(bullet);
            }
            cooldownThread.startCoolDown();
        }
    }
    
    public void update(){
        ArrayList<Bullet> eliminatedBullets =  new ArrayList<>();
        for(Bullet bullet: bullets){
            if(bullet.isExplode()){
                eliminatedBullets.add(bullet);
            }
        }
        for(int b = 0; b < eliminatedBullets.size(); b++){
           bullets.remove(eliminatedBullets.get(b));
        }
    }
}
