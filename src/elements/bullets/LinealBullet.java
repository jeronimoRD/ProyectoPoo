/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.bullets;

import java.awt.Color;
import threads.BulletThread;

public class LinealBullet extends Bullet{
    
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    public static final int STEP = 5;
    
    private BulletThread bulletThread;
    
    public LinealBullet(int x, int y, int direction, int damage, int speed) {
        super(x, y, WIDTH, HEIGHT, Color.ORANGE);
        this.damage = damage;
        
        bulletThread = new BulletThread(this, direction, speed);
        bulletThread.start();
    }
    
    public BulletThread getBulletThread() {
        return bulletThread;
    }
    
}
