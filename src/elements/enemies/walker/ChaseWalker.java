/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.enemies.walker;

import interfaces.Collidable;
import interfaces.Damageable;
import java.awt.Color;
import java.awt.Graphics;

public class ChaseWalker extends Walker{
    //CHARACTERISTICS
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final int LIFE = 50;
    private WalkerThread walkerThread;

    public ChaseWalker(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.RED, LIFE);
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        
        update();
    }
    
    @Override
    public void update(){
        if(stunned.isRecover()){
            walkerThread.setPause(true);
        }else{
            walkerThread.setPause(false);
        }
    }
    
    @Override
    public void touched(Collidable collidable){};
    
    @Override
    public void die(){
        walkerThread.stopRun();
    }
    
    @Override
    public void setPlayer(Damageable player){
        this.player = player;
        
        walkerThread = new WalkerThread(this, player);
        walkerThread.start();
    }
}
