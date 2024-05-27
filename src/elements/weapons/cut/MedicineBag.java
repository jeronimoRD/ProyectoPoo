/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.weapons.cut;

import elements.collectibles.weapons.MedicineBagC;
import elements.player.Player;
import elements.weapons.Weapon;
import interfaces.Collectible;
import java.awt.Color;
import java.awt.Graphics;
import threads.CooldownThread;

public class MedicineBag extends Weapon {
    //CHARACTERISTICS
    public static final int WIDTH = 10;
    public static final int HEIGHT = 60;
    
    //ATTACK
    public static final int DAMAGE = 80;
    public static final int SCOPE = 60;
    public static final int TIME_STUNNED = 3000;
    public static final int COOLDOWN_ATTACK= 1500;
    
    private HitboxThread hitboxThread;

    public MedicineBag(Player player) {
        super(0, 0, WIDTH, HEIGHT, new Color(206, 173, 173), player, COOLDOWN_ATTACK, TIME_STUNNED);
        
        this.damage = DAMAGE;
        
        cooldownThread = new CooldownThread(COOLDOWN_ATTACK);
        cooldownThread.start();
        
        hitboxThread = new HitboxThread(player, this, SCOPE);
        hitboxThread.start();
    }

    @Override
    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, width, height);
        
        if(hitboxThread.getHitbox() != null){
           hitboxThread.getHitbox().draw(g); 
        }
    }

    @Override
    public void attack(Player player) {
        if(!cooldownThread.isRecover()){
            if(hitboxThread.getPlayer() == null){
                hitboxThread.setPlayer(player);
            }
            player.getActualWeapon().setAttacking(true);
            cooldownThread.startCoolDown();
        }
    }

    public static int getSCOPE() {
        return SCOPE;
    }

    @Override
    public Collectible drop() {
        return new MedicineBagC(player.getX() + 40, player.getY());
    }
}
