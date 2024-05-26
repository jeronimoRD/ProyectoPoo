/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import interfaces.Collidable;
import interfaces.Movable;

public class ChaseThread extends Thread{
    private boolean running;
    
    //ELEMENTS
    private Movable persecutor;
    private Collidable prey;
    
    //MOVE
    private int px;
    private int py;

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
                px = persecutor.getX();
                py = persecutor.getY(); 
                
                int preyX = prey.getX();
                int preyY = prey.getY();
                
                if(px < preyX){
                    persecutor.setX(px + persecutor.getStep());
                }
                if(px > preyX){
                    persecutor.setX(px - persecutor.getStep());
                }
                if(py < preyY){
                    persecutor.setY(py + persecutor.getStep());
                }
                if(py > preyY){
                    persecutor.setY(py - persecutor.getStep());
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

    public int getPx() {
        return px;
    }

    public int getPy() {
        return py;
    }
}
