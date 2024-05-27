/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.weapons.cut;

import elements.player.Player;
import elements.weapons.Weapon;
import java.awt.Color;
import java.awt.Graphics;

public class Cutter extends Weapon {
    //CHARACTERISTICS
    public static final int WIDTH = 5;
    public static final int HEIGHT = 30;
    
    //ATTACK
    public static final int DAMAGE = 30;
    public static final int SCOPE = 30;
    public static final int COOLDOWN_ATTACK= 200;
    
    private HitboxThread hitboxThread;

    public Cutter(Player player) {
        super(0, 0, WIDTH, HEIGHT, new Color(71, 62, 62), player);
        
        this.damage = DAMAGE;
        
        hitboxThread = new HitboxThread(player, SCOPE, COOLDOWN_ATTACK);
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
        if(hitboxThread.getPlayer() == null){
            hitboxThread.setPlayer(player);
        }
        player.getActualWeapon().setAttacking(true);
    }
}
