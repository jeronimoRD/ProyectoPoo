/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import interfaces.Collidable;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class TouchCollisionThread extends Thread{
    private boolean running;
    private Collidable reference;
    private ArrayList<Collidable> collidables;

    public TouchCollisionThread(Collidable reference) {
        running = true;
        this.reference = reference;
        this.collidables = new ArrayList<>();
    }
    
    @Override
    public void run(){
        while(running){
            try{
                System.out.print("");
                for(Collidable collidable: collidables){
                   if(reference.checkCollision(collidable)){
                       reference.touched(collidable);
                   } 
                }
            }catch(ConcurrentModificationException e){
                try{
                    Thread.sleep(100);
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
    
    public void addCollidable(Collidable collidable){
        if(!collidables.contains(collidable)){
           collidables.add(collidable); 
        }else{
        }
    }
}
