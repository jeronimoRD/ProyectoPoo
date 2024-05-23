/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import elements.bullets.Bullet;
import interfaces.Movable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChaseThread extends Thread{
    private CooldownThread cooldownThread;
    private Bullet persecutor;
    private Movable prey;
    private boolean running;

    public ChaseThread(Bullet persecutor, Movable prey, int time) {
        cooldownThread = new CooldownThread(time);
        cooldownThread.start();
        cooldownThread.startCoolDown();
        
        this.persecutor = persecutor;
        this.prey = prey;
        running = true;
    }
    
    @Override
    public void run(){
        int px;
        int py;
        int wx;
        int wy;
        while(running){
            System.out.print("");
            
            if(prey != null & cooldownThread.isRecover()){
                px = prey.getX();
                py = prey.getY();

                wx = persecutor.getX();
                wy = persecutor.getY();

                //UP
                if(wy > py){
                    persecutor.setY(wy - (persecutor.getStep()));
                }
                //DOWN
                if(wy < py){
                    persecutor.setY(wy + (persecutor.getStep()));
                }
                //RIGHT
                if(wx < px){
                    persecutor.setX(wx + persecutor.getStep());
                }
                //LEFT
                if(wx > px){
                    persecutor.setX(wx - persecutor.getStep());
                }
                try{
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(WalkerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(!cooldownThread.isRecover()){
                persecutor.setExplode(true);
            }
        }
    }
    
    public void stopRun(){
        this.running = false;
    }
    public void startRun(){
        this.running = true;
    }
}
