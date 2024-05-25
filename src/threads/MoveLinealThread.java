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
public class MoveLinealThread extends Thread{
    private boolean running;
    private Movable movable;
    private int direction;
    
    public MoveLinealThread(Movable movable, int direction){
        this.movable = movable;
        this.direction = direction;
        
        running = true;
    }
    
    @Override
    public void run(){
        while(running){
            System.out.print("");
            if(direction == Collidable.UP){
                movable.setY(movable.getY() - movable.getStep());
            }
            else if(direction == Collidable.DOWN){
                movable.setY(movable.getY() + movable.getStep());
            }
            else if(direction == Collidable.RIGHT){
                movable.setX(movable.getX() + movable.getStep());
            }
            else if(direction == Collidable.LEFT){
                movable.setX(movable.getX() - movable.getStep());
            }
            
            try{
                Thread.sleep(movable.getCoolDownMove());
            } catch (InterruptedException ex) {
                System.out.println("ERROR");
            }
        }
    }
    
    public void stopRun(){
        this.running = false;
    }
    public void startRun(){
        this.running = true;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
