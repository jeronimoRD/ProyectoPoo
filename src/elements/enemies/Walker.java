/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.enemies;

import threads.WalkerThread;
import interfaces.Damageable;
import java.awt.Color;

public class Walker extends Enemy{
    
    public static final int LIFE = 50;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final int STEP = 5;
    
    private WalkerThread walkerThread;
    
    public Walker(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.RED);
        walkerThread = new WalkerThread(this); //¿(this)debería tener una interfaz?
        this.lifeBar = LIFE; //VIDA
    }
    
    @Override
    public void setDamageable(Damageable damageable){
        this.player = damageable;
        
        if(!walkerThread.isRunning()){
            walkerThread.start();
        }
        walkerThread.setRunning(true);
        
    }
}
