/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.bullets;

import java.awt.Color;
import threads.MoveLinealThread;

public class LinealBullet extends Bullet{
    
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    
    public static final int DAMAGE = 20;
    public static final int TIME_STUNNED = 500;
    
    private MoveLinealThread moveLinealThread;
    
    public LinealBullet(int x, int y, int direction, int step, int cooldownMove) {
        super(x, y, WIDTH, HEIGHT, Color.ORANGE, TIME_STUNNED);
        this.damage = DAMAGE;
        this.step = step;
        this.cooldownMove = cooldownMove;
        
        moveLinealThread = new MoveLinealThread(this, direction);
        moveLinealThread.start();
    }

    @Override
    public void explode() {
        explode = true;
        touchCollisionThread.stopRun();
        moveLinealThread.stopRun();
    }
}