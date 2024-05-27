/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.weapons.cut;

import elements.collectibles.weapons.CutterC;
import elements.player.Player;
import elements.weapons.Weapon;
import interfaces.Collectible;
import java.awt.Color;
import java.awt.Graphics;
import threads.CooldownThread;

public class Cutter extends Weapon {
    //CHARACTERISTICS
    public static final int WIDTH = 5;
    public static final int HEIGHT = 30;
    
    //ATTACK
    public static final int DAMAGE = 30;
    public static final int SCOPE = 30;
    public static final int TIME_STUNNED = 500;
    public static final int COOLDOWN_ATTACK = 700;
    
    private HitboxThread hitboxThread;

    public Cutter(Player player) {
        super(0, 0, WIDTH, HEIGHT, new Color(71, 62, 62), player, COOLDOWN_ATTACK, TIME_STUNNED);
        
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

    @Override
    public Collectible drop() {
        return new CutterC(player.getX() + 40, player.getY());
    }
}
