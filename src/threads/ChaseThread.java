/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import interfaces.Collidable;
import interfaces.Movable;

/**
 *
 * @author korez
 */
public class ChaseThread extends Thread{
    private boolean running;
    
    //ELEMENTS
    private Movable persecutor;
    private Collidable prey;

    public ChaseThread(Movable persecutor, Collidable prey) {
        this.persecutor = persecutor;
        this.prey = prey;
        running = true;
    }
    
    @Override
    public void run(){
        while(running){
            System.out.print("");
            if(prey != null){
                int pX = persecutor.getX();
                int pY = persecutor.getY();        
                int preyX = prey.getX();
                int preyY = prey.getY();

                if(pX < preyX){
                    persecutor.setX(pX + persecutor.getStep());
                }
                if(pX > preyX){
                    persecutor.setX(pX - persecutor.getStep());
                }
                if(pY < preyY){
                    persecutor.setY(pY + persecutor.getStep());
                }
                if(pY > preyY){
                    persecutor.setY(pY - persecutor.getStep());
                }
                try{
                    Thread.sleep(persecutor.getCoolDownMove());
                } catch (InterruptedException ex) {
                    System.out.println("ERROR");
                }
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
