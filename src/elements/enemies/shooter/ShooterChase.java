/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.enemies.shooter;
import java.awt.Color;

public class ShooterChase extends Shooter{
    
    //CHARACTERISTICS
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final int COOLDOWNSHOOT = 10000;
    
    //LIFE
    public static final int LIFE = 200;
    
    //BULLET
    public static final int SPEED = 1;
    private static final int COOLDOWN_MOVE = 10;
    
    public ShooterChase(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.PINK);
        this.lifeBar = LIFE;
        
        shooterThread = new ShooterThread(this, false, false, true, false, COOLDOWNSHOOT, SPEED, COOLDOWN_MOVE, 2);
        shooterThread.start();
    }
}
