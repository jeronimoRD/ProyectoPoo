/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.enemies;

import elements.bullets.Bullet;
import interfaces.Collidable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import threads.ShooterThread;

public abstract class Shooter extends Creature{
    
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
    
    @Override
    public void die() {
        shooterThread.stopRun();
        shooterThread = null;
    }
    public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
    
    @Override
    public void touched(Collidable collidable) {}
}
