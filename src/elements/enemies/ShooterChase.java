/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.enemies;
import java.awt.Color;
import threads.ShooterThread;

public class ShooterChase extends Shooter{
    
    //CHARACTERISTICS
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    
    //LIFE
    public static final int LIFE = 100;
    
    //SHOT
    public static final int COOLDOWNSHOOT = 10000;
    
    public ShooterChase(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.PINK);
        this.lifeBar = LIFE;
        
        shooterThread = new ShooterThread(this, true, true, true, true, COOLDOWNSHOOT, 2);
        shooterThread.start();
    }
    
}
