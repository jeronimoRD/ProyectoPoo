/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import elements.player.Player;
import interfaces.Collidable;

public class VerticalMoveThread extends Thread{
    //TEST
    private volatile boolean paused;
    private boolean running;
    
    private boolean up;
    private boolean down;
    private Player player;
    
    public VerticalMoveThread(Player player){ //¿Collidable?
        this.player = player;
        this.running = true;
        this.paused = false;
        this.up = false;
        this.down = false;
    }
    
    @Override
    public void run(){
        while(running){
            System.out.print(up);
            if(up){
                System.out.println("arriba");
                player.setY(player.getY() - player.STEP);
                for(Collidable collidable: player.getBoundables()){
                    if(player.checkCollision(collidable)){
                        System.out.println("si");
                        player.setY(collidable.getY()+collidable.getHeight());
                        return;
                    }
                }
                up = false;
            }
            else if(down){
                System.out.println("abajo");
                player.setY(player.getY() + player.STEP);
                for(Collidable collidable: player.getBoundables()){
                    if(player.checkCollision(collidable)){
                        player.setY(collidable.getY()+player.getHeight());
                        return;
                    }
                }
                down = false;
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

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
