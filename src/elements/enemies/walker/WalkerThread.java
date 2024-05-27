/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.enemies.walker;

import interfaces.Collidable;
import threads.ChaseThread;

public class WalkerThread extends ChaseThread{
    private boolean running;
    private boolean pause;
    private Walker walker;
    private Collidable player;
    
    public WalkerThread(Walker walker, Collidable player){
        super(walker, player);
        this.running = true;
        this.pause = false;
        this.walker = walker;
        this.player = player;
    }
    
    @Override
    public void run(){
        int px;
        int py;
        int wx;
        int wy;
        while(running){
            System.out.print("");
            if(!pause){
                px = player.getX();
                py = player.getY();

                wx = walker.getX();
                wy = walker.getY();

                boolean still;

                //WITHOUT COLLISIONS
                if(wx < px){
                    still = false;
                    for(Collidable collision: walker.getBoundables()){
                        if(collision != walker){
                            if(walker.checkCollisionHitbox(collision, walker.RIGHT)){
                                still = true;
                            }
                        }
                    }
                    if(!still){
                        walker.setX(wx + walker.STEP);
                        for(Collidable collision: walker.getBoundables()){
                            if(walker.checkCollision(collision)){
                                if(collision != walker){
                                    walker.setX(collision.getX() - walker.getWidth());
                                    break;
                                }
                            }
                        }
                    }
                }if(wx > px){
                    still = false;
                    for(Collidable collision: walker.getBoundables()){
                        if(collision != walker){
                            if(walker.checkCollisionHitbox(collision, walker.LEFT)){
                                still = true;
                            }
                        }
                    }
                    if(!still){
                        walker.setX(wx - walker.STEP);
                        for(Collidable collision: walker.getBoundables()){
                            if(walker.checkCollision(collision)){
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
                    for(Collidable collision: walker.getBoundables()){
                        if(collision != walker){
                            if(walker.checkCollisionHitbox(collision, walker.DOWN)){
                                still = true;
                            }
                        }
                    }
                    if(!still){
                    walker.setY(wy + walker.STEP);
                        for(Collidable collision: walker.getBoundables()){
                            if(walker.checkCollision(collision)){
                                if(collision != walker){
                                    walker.setY(collision.getY() - walker.getHeight());
                                    break;
                                }
                            }
                        }
                    }
                }if(wy > py){
                    still = false;
                    for(Collidable collision: walker.getBoundables()){
                        if(collision != walker){
                            if(walker.checkCollisionHitbox(collision, walker.UP)){
                                still = true;
                            }
                        }
                    }
                    if(!still){
                    walker.setY(wy - walker.STEP);
                        for(Collidable collision: walker.getBoundables()){
                            if(walker.checkCollision(collision)){
                                if(collision != walker){
                                    walker.setY(collision.getY()+collision.getHeight());
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
            }
        }
    }
    
    public void stopRun(){
        this.running = false;
    }
    public void startRun(){
        this.running = true;
    }
    
    public void setPause(boolean pause){
        this.pause = pause;
    }
}
