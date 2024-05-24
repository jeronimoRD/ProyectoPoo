/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.bullets;

import interfaces.Collidable;
import java.awt.Color;
import threads.ChaseThread;

/**
 *
 * @author korez
 */
public class ChaseBullet extends Bullet{
    
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    
    public static final int DAMAGE = 50;
    
    private ChaseThread chaseThread;

    public ChaseBullet(int x, int y, int step, int cooldownMove, Collidable prey) {
        super(x, y, WIDTH, HEIGHT, Color.PINK);
        this.damage = DAMAGE;
        this.step = step;
        this.cooldownMove = cooldownMove;
        
        chaseThread = new ChaseThread(this, prey);
        chaseThread.start();
    }

    @Override
    public void explode() {
        explode = true;
        touchCollisionThread.stopRun();
        chaseThread.stopRun();
    }
}
