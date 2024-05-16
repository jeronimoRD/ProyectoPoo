/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enemies;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author korez
 */
public class WalkerThread extends Thread{
    private volatile boolean paused;
    private boolean running;
    private Walker walker;
    
    public WalkerThread(Walker walker) {
        this.running = true;
        this.paused = false;
        this.walker = walker;
    }
    
    @Override
    public void run(){
        int px;
        int py;
        int wx;
        int wy;
        while(running){
            px = walker.player.getX();
            py = walker.player.getY();
            
            wx = walker.getX();
            wy = walker.getY();
            
            //WITHOUT COLLISIONS
            
            if(wx < px){
                walker.setX(wx + walker.STEP);
            }if(wx > px){
                walker.setX(wx - walker.STEP);
            }
            if(wy < py){
                walker.setY(wy + walker.STEP);
            }if(wy > py){
                walker.setY(wy - walker.STEP);
            }
            
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(WalkerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void pause(){
        this.paused = !this.paused;
    }
    
    public void halt(){ //Stop
        this.running = false;
    }
}
