/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import elements.enemies.Walker;
import interfaces.Collidable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WalkerThread extends Thread{
    private volatile boolean paused;
    private boolean running;
    private Walker walker;
    
    public WalkerThread(Walker walker) {
        this.running = false;
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
            px = walker.getPlayer().getX();
            py = walker.getPlayer().getY();
            
            wx = walker.getX();
            wy = walker.getY();
            
            boolean still;
            
            //RIGHT
            if(wx < px){
                still = false;
                for(Collidable collision: walker.getCollidables()){
                    if(collision != walker & collision != walker.getPlayer()){
                        if(walker.checkCollision(collision, walker.RIGHT)){
                            still = true;
                        }
                    }
                }
                if(!still){
                    walker.setX(wx + walker.STEP);
                    for(Collidable collision: walker.getCollidables()){
                        if(walker.checkCollision(collision)){
                            if(collision != walker & collision != walker.getPlayer()){ //FUTURE HURT
                                walker.setX(collision.getX() - walker.getWidth());
                                break;
                            }
                        }
                    }
                }
            //LEFT
            }if(wx > px){
                still = false;
                for(Collidable collision: walker.getCollidables()){
                    if(collision != walker & collision != walker.getPlayer()){ //FUTURE HURT
                        if(walker.checkCollision(collision, walker.LEFT)){
                            still = true;
                        }
                    }
                }
                if(!still){
                    walker.setX(wx - walker.STEP);
                    for(Collidable collision: walker.getCollidables()){
                        if(walker.checkCollision(collision)){
                            if(collision != walker & collision != walker.getPlayer()){ //FUTURE HURT
                                walker.setX(collision.getX()+collision.getWidth());
                                break;
                            }
                        }
                    }
                }
            }
            //DOWN
            if(wy < py){
                still = false;
                for(Collidable collision: walker.getCollidables()){
                    if(collision != walker & collision != walker.getPlayer()){ //FUTURE HURT
                        if(walker.checkCollision(collision, walker.DOWN)){
                            still = true;
                        }
                    }
                }
                if(!still){
                walker.setY(wy + walker.STEP);
                    for(Collidable collision: walker.getCollidables()){
                        if(walker.checkCollision(collision)){
                            if(collision != walker & collision != walker.getPlayer()){ //FUTURE HURT
                                walker.setY(collision.getY() - walker.getHeight());
                                break;
                            }
                        }
                    }
                }
             //UP
            }if(wy > py){
                still = false;
                for(Collidable collision: walker.getCollidables()){
                    if(collision != walker & collision != walker.getPlayer()){ //FUTURE HURT
                        if(walker.checkCollision(collision, walker.UP)){
                            still = true;
                        }
                    }
                }
                if(!still){
                walker.setY(wy - walker.STEP);
                    for(Collidable collision: walker.getCollidables()){
                        if(walker.checkCollision(collision)){
                            if(collision != walker & collision != walker.getPlayer()){ //FUTURE HURT
                                walker.setY(collision.getY()+collision.getHeight());
                                break;
                            }
                        }
                    }
                }
            }
            
            //HURT PLAYER
            if(walker.checkCollision(walker.getPlayer())){
                walker.getPlayer().takeDamage(0);
            }
            
            try{
                Thread.sleep(60);
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
    
    //GETTERS AND SETTERS
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
