/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import elements.player.Player;
import interfaces.Collidable;

/**
 *
 * @author korez
 */
public class HorizontalMoveThread extends Thread{
    private volatile boolean paused;
    private boolean running;
    
    private boolean right;
    private boolean left;
    private Player player;
    
    public HorizontalMoveThread(Player player){ //¿Collidable?
        this.player = player;
        this.running = true;
        this.paused = false;
        this.right = false;
        this.left = false;
    }
    
    @Override
    public void run(){
        while(running){
            System.out.print(""); //¿QUE?
            if(right){
                player.setX(player.getX() + player.STEP);
                for(Collidable collidable: player.getBoundables()){
                    if(player.checkCollision(collidable)){
                        player.setX(collidable.getX()+collidable.getWidth());
                        return;
                    }
                }
                right = false;
            }
            else if(left){
                player.setX(player.getX() - player.STEP);
                for(Collidable collidable: player.getBoundables()){
                    if(player.checkCollision(collidable)){
                        player.setX(collidable.getX()+collidable.getWidth());
                        return;
                    }
                }
                 left = false;
            }
        }
    }
    
    public void pause(){
        this.paused = !this.paused;
    }
    
    public void halt(){ //Stop
        this.running = false;
    }
    
    //GETTERS AND SETTERS
    public boolean isRunning(){
        return running;
    }

    public void setRunning(boolean running){
        this.running = running;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
    
}
