/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.weapons.cut;

import elements.player.Player;
import elements.weapons.Weapon;
import interfaces.Damageable;
import java.util.ConcurrentModificationException;
import threads.CooldownThread;

public class HitboxThread extends Thread{
    private boolean running;
    
    //OWNER
    private Player player;
    private Weapon weapon;
    
    //CHARACTERISTICS
    private int scope;
    
    //HITBOX
    private Hitbox hitbox;
    private CooldownThread cooldownThread;

    public HitboxThread(Player player, Weapon weapon, int scope) {
        running = true;
        this.scope = scope;
        this.player = player;
        this.weapon = weapon;
        
        cooldownThread = new CooldownThread(200);
        cooldownThread.start();
    }

    @Override
    public void run() {
        while(running){
            System.out.print("");
            if(player != null){
                if(player.getActualWeapon() != null & player.getActualWeapon() == weapon){
                    if(player.getActualWeapon().isAttacking() & hitbox == null){
                        hitbox = new Hitbox(player.getX(), player.getY(), scope, scope);
                        cooldownThread.startCoolDown();
                    }
                    if(player.getActualWeapon().isAttacking() & hitbox != null){

                        int px = 0;
                        int py = 0;

                        if(player.getDirection() == player.UP){
                            px = (player.getX() + (player.getWidth()/2)) - (hitbox.getWidth()/2);
                            py = player.getY() - hitbox.getHeight() - 10;

                        }else if(player.getDirection() == player.DOWN){
                            px = (player.getX() + (player.getWidth()/2)) - (hitbox.getWidth()/2);
                            py = player.getY() + player.getHeight() + 10;

                        }else if(player.getDirection() == player.RIGHT){
                            px = player.getX() + player.getWidth() + 10;
                            py = (player.getY() + (player.getHeight()/2)) - (hitbox.getHeight()/2);

                        }else if(player.getDirection() == player.LEFT){
                            px = player.getX() - hitbox.getWidth() - 10;
                            py = (player.getY() + (player.getHeight()/2)) - (hitbox.getHeight()/2);
                        }

                        hitbox.setX(px);
                        hitbox.setY(py);

                        //KILL ENEMY
                        Damageable eliminated = null;
                        try{
                            for(Damageable creature: player.getCreatures()){ //WAIT
                                if(creature.checkCollision(hitbox)){
                                    eliminated = creature;
                                    break;
                                }
                            }
                            if(eliminated != null){
                                eliminated.takeDamage(player.getActualWeapon().getDamage(), player.getActualWeapon().getTimeStunned());
                            }
                        }catch(ConcurrentModificationException e){
                            System.out.println("ostion");
                            try{
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                System.out.println("ERROR");
                            }
                        }

                        if(!cooldownThread.isRecover()){
                            player.getActualWeapon().setAttacking(false);
                        }
                    }
                    if(!player.getActualWeapon().isAttacking() & hitbox != null){
                        hitbox = null;
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }
}
