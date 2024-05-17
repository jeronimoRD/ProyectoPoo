/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enemies;

import interfaces.Damageable;

public class Walker extends Enemy{
    private WalkerThread walkerThread;
    public static final int STEP = 10;
    
    public Walker(int x, int y) {
        super(x, y);
        walkerThread = new WalkerThread(this); //INTERFACE?
    }
    
    public void move(){
        walkerThread.start(); //NO RUN
    }
    
    @Override
    public void setDamageable(Damageable damageable){
        this.player = damageable;
        move();
    }
}
