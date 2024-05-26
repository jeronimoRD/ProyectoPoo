/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.enemies.shooter;

import elements.bullets.Bullet;
import elements.enemies.Enemy;
import interfaces.Damageable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public abstract class Shooter extends Enemy{
    
    //CHARACTERISTICS
    protected ArrayList<Bullet> bullets;
    protected ShooterThread shooterThread;
    
    public Shooter(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        
        bullets = new ArrayList<>();
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
    
    @Override
    public void die() {
        ArrayList<Bullet> explodes = new ArrayList<>();
        for(Bullet bullet: bullets){
            if(!bullet.isExplode()){
                explodes.add(bullet);
            }
        }
        for(int b = 0; b < explodes.size(); b++){
            explodes.get(b).explode();
        }
        shooterThread.stopRun();
    }
    
    @Override
    public void setPlayer(Damageable player){
        this.player = player;
    }
    
    public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }
}

