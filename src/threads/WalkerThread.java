/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import elements.enemies.Walker;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WalkerThread extends Thread{
    private boolean running;
    private Walker walker;
    
    public WalkerThread(Walker walker) {
        this.running = true;
        this.walker = walker;
    }
    
    @Override
    public void run(){
        int px;
        int py;
        int wx;
        int wy;
        while(running){
            System.out.print("");
            if(walker.getPlayer() != null){
                if(walker.getPlayer().getActualWeapon() != null){
                    if(walker.getPlayer().getActualWeapon().isAttacking()){
                        if(walker.checkCollision(walker.getPlayer().getActualWeapon().getHitbox())){
                            walker.takeDamage(walker.getPlayer().getActualWeapon().getDamage());
                        }
                    }
                }
                px = walker.getPlayer().getX();
                py = walker.getPlayer().getY();

                wx = walker.getX();
                wy = walker.getY();

                //HURT ITSELF
                /*
                if(walker.getPlayer().getActualWeapon().getHitbox() != null){
                    if(walker.getPlayer().getActualWeapon().getHitbox().checkCollision(walker)){
                        walker.takeDamage(walker.getPlayer().getActualWeapon().getDamage());
                    }
                }
                */
                //UP
                if(wy > py){
                    walker.setY(wy - walker.STEP);
                    walker.setLastMove(walker.UP);
                    /*
                    still = false;
                    for(Collidable collision: walker.getBoundables()){
                        if(collision != walker & collision != walker.getPlayer()){ //FUTURE HURT
                            if(walker.checkCollision(collision, walker.UP)){
                                still = true;
                            }
                        }
                    }
                    if(!still){
                    walker.setY(wy - walker.STEP);
                        for(Collidable collision: walker.getBoundables()){
                            if(walker.checkCollision(collision)){
                                if(collision != walker & collision != walker.getPlayer()){ //FUTURE HURT
                                    walker.setY(collision.getY()+collision.getHeight());
                                    break;
                                }
                            }
                        }
                    }
                    */
                }
                //DOWN
                if(wy < py){
                    walker.setY(wy + walker.STEP);
                    walker.setLastMove(walker.DOWN);
                    /*
                    still = false;
                    for(Collidable collision: walker.getBoundables()){
                        if(collision != walker & collision != walker.getPlayer()){ //FUTURE HURT
                            if(walker.checkCollision(collision, walker.UP)){
                                still = true;
                            }
                        }
                    }
                    if(!still){
                    walker.setY(wy - walker.STEP);
                        for(Collidable collision: walker.getBoundables()){
                            if(walker.checkCollision(collision)){
                                if(collision != walker & collision != walker.getPlayer()){ //FUTURE HURT
                                    walker.setY(collision.getY()+collision.getHeight());
                                    break;
                                }
                            }
                        }
                    }
                    */
                }
                
                //RIGHT
                if(wx < px){
                    walker.setX(wx + walker.STEP);
                    walker.setLastMove(walker.RIGHT);
                    /*
                    still = false;
                    for(Collidable collision: walker.getBoundables()){
                        if(collision != walker & collision != walker.getPlayer()){
                            if(walker.checkCollision(collision, walker.RIGHT)){
                                still = true;
                            }
                        }
                    }
                    if(!still){
                        walker.setX(wx + walker.STEP);
                        for(Collidable collision: walker.getBoundables()){
                            if(walker.checkCollision(collision)){
                                if(collision != walker & collision != walker.getPlayer()){ //FUTURE HURT
                                    walker.setX(collision.getX() - walker.getWidth());
                                    break;
                                }
                            }
                        }
                    }
                    */  
                }
                //LEFT
                if(wx > px){
                    walker.setX(wx - walker.STEP);
                    walker.setLastMove(walker.LEFT);
                    /*
                    still = false;
                    for(Collidable collision: walker.getBoundables()){
                        if(collision != walker & collision != walker.getPlayer()){ //FUTURE HURT
                            if(walker.checkCollision(collision, walker.LEFT)){
                                still = true;
                            }
                        }
                    }
                    if(!still){
                        walker.setX(wx - walker.STEP);
                        for(Collidable collision: walker.getBoundables()){
                            if(walker.checkCollision(collision)){
                                if(collision != walker & collision != walker.getPlayer()){ //FUTURE HURT
                                    walker.setX(collision.getX()+collision.getWidth());
                                    break;
                                }
                            }
                        }
                    }
                    */
                }
                try{
                    Thread.sleep(60);
                } catch (InterruptedException ex) {
                    Logger.getLogger(WalkerThread.class.getName()).log(Level.SEVERE, null, ex);
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
