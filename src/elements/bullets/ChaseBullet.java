/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.bullets;

import interfaces.Movable;
import java.awt.Color;
import threads.ChaseThread;

public class ChaseBullet extends Bullet{
    
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    public static final int STEP = 5;
    
    public static final int TIME = 10000; //TIME OF LIVE
    
    private ChaseThread chaseThread;
    private int lastMove;
    
    public ChaseBullet(int x, int y, int speed, int damage, Movable movable) {
        super(x, y, WIDTH, HEIGHT, Color.PINK);
        this.damage = damage;
        
        chaseThread = new ChaseThread(this, movable, TIME);
        chaseThread.start();
    }
    
}
