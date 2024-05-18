/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.enemies;

import elements.Bullet;
import interfaces.Damageable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import threads.ShooterThread;

public class Shooter extends Enemy{
    
    public static final int LIFE = 100;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    
    private ArrayList<Bullet> bullets;
    private ShooterThread shooterThread;
    
    public Shooter(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.BLUE);
        this.lifeBar = LIFE; //ADD LIFE
        bullets = new ArrayList<>();
        shooterThread = new ShooterThread(this); //¿4 hilos para 4 dirreciones?
    }
    
    private void shot(int direction){
        if(!shooterThread.isRunning()){
            shooterThread.setDirection(direction);
            shooterThread.start();
        }
        shooterThread.setRunning(true);
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        for(Bullet bullet: bullets){
            bullet.draw(g);
        }
    }
    
    public void explode(Bullet bullet){
        bullets.remove(bullet);
    }
    
    //GETTERS AND SETTERS
    @Override
    public void setDamageable(Damageable damageable){
        this.player = damageable;
        shot(DOWN); //!!Test!! ¿Por qué solo permite uno?
    }
    
    public void addBullet(Bullet bullet){
        bullets.add(bullet);
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}
