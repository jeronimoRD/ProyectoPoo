/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import interfaces.Collidable;
import interfaces.Movable;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class TouchCollisionThread extends Thread{
    private boolean running;
    private Movable reference;
    private ArrayList<Collidable> collidables;

    public TouchCollisionThread(Movable reference) {
        running = true;
        this.reference = reference;
        this.collidables = new ArrayList<>();
    }
    
    @Override
    public void run(){
        while(running){
            System.out.print("");
            if(collidables != null){
                try{
                    for(Collidable collidable: collidables){
                        if(collidable != null){
                            if(reference.checkCollision(collidable)){
                                reference.touched(collidable);
                            } 
                        }
                    }
                }catch(ConcurrentModificationException e){
                    try{
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        System.out.println("ERROR");
                    }
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
    
    public void addCollidable(ArrayList<Collidable> collidables){
        this.collidables = collidables; 
    }
}
