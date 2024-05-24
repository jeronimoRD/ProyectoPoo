/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.enemies.shooter;

import java.awt.Color;

public class ShooterAllDirections extends Shooter{
    
    //CHARACTERISTICS
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final int COOLDOWNSHOOT = 2000;
    
    //LIFE
    public static final int LIFE = 100;
    
    //BULLET
    public static final int SPEED = 5;
    private static final int COOLDOWN_MOVE = 100;
    
    public ShooterAllDirections(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.BLUE);
        this.lifeBar = LIFE;
        
        shooterThread = new ShooterThread(this, true, true, true, true, COOLDOWNSHOOT, SPEED, COOLDOWN_MOVE, 1);
        shooterThread.start();
    }
}
