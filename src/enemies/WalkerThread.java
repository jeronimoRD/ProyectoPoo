/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enemies;

import interfaces.Collidable;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            
            boolean still;
            
            //WITHOUT COLLISIONS
            if(wx < px){
                still = false;
                for(Collidable collision: walker.collidables){
                    if(collision != walker){
                        if(walker.checkCollisionHitbox(collision, walker.RIGHT)){
                            System.out.println("co");
                            still = true;
                        }
                    }
                }
                if(!still){
                    walker.setX(wx + walker.STEP);
                    for(Collidable collision: walker.collidables){
                        if(walker.checkCollisionHitbox(collision)){
                            if(collision != walker){
                                walker.setX(collision.getX() - walker.getWidth());
                                break;
                            }
                        }
                    }
                }
            }if(wx > px){
                still = false;
                for(Collidable collision: walker.collidables){
                    if(collision != walker){
                        if(walker.checkCollisionHitbox(collision, walker.LEFT)){
                            System.out.println("co");
                            still = true;
                        }
                    }
                }
                if(!still){
                    walker.setX(wx - walker.STEP);
                    for(Collidable collision: walker.collidables){
                        if(walker.checkCollisionHitbox(collision)){
                            if(collision != walker){
                                walker.setX(collision.getX()+collision.getWidth());
                                break;
                            }
                        }
                    }
                }
            }
            if(wy < py){
                still = false;
                for(Collidable collision: walker.collidables){
                    if(collision != walker){
                        if(walker.checkCollisionHitbox(collision, walker.DOWN)){
                            System.out.println("co");
                            still = true;
                        }
                    }
                }
                if(!still){
                walker.setY(wy + walker.STEP);
                    for(Collidable collision: walker.collidables){
                        if(walker.checkCollisionHitbox(collision)){
                            if(collision != walker){
                                System.out.println("abajo");
                                walker.setY(collision.getY() - walker.getHeight());
                                break;
                            }
                        }
                    }
                }
            }if(wy > py){
                still = false;
                for(Collidable collision: walker.collidables){
                    if(collision != walker){
                        if(walker.checkCollisionHitbox(collision, walker.UP)){
                            System.out.println("co");
                            still = true;
                        }
                    }
                }
                if(!still){
                walker.setY(wy - walker.STEP);
                    for(Collidable collision: walker.collidables){
                        if(walker.checkCollisionHitbox(collision)){
                            if(collision != walker){
                                System.out.println("arr");
                                walker.setY(collision.getY()+collision.getHeight());
                                break;
                            }
                        }
                    }
                }
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
