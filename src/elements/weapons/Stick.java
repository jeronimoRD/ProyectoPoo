/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.weapons;

import elements.player.Player;
import interfaces.Collidable;
import java.awt.Color;
import java.awt.Graphics;
import threads.AttackCooldownThread;

/**
 *
 * @author korez
 */
public class Stick extends Weapon{

    //CHARACTERISTICS
    public static final int WIDTH = 5;
    public static final int HEIGHT = 30;
    
    //CHARACTERISTICS WEAPON
    private static final int DAMAGE = 100;
    private static final int SCOPE = 50;
    public static final int COOLDOWN_ATTACK= 500;
    
    private AttackCooldownThread attackCooldownThread;
    
    public Stick(Player player) {
        super(0, 0, WIDTH, HEIGHT, Color.orange, player);
        setDamage(DAMAGE);
        attackCooldownThread = new AttackCooldownThread(this); //INTERFAZ
        attackCooldownThread.setTime(COOLDOWN_ATTACK);
        attackCooldownThread.start();
    }
    
    @Override
    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, width, height);
        if(hitbox != null){
            int px = 0;
            int py = 0;
            int pwidth = 0;
            int pheight = 0;
            if(hitbox.getDirection() == player.UP){
                px = hitbox.getCollidable().getX();
                py = hitbox.getCollidable().getY() - SCOPE;
                pwidth = hitbox.getCollidable().getWidth();
                pheight = SCOPE;
            }else if(hitbox.getDirection() == player.DOWN){
                px = hitbox.getCollidable().getX() ;
                py = hitbox.getCollidable().getY() + hitbox.getCollidable().getHeight();
                pwidth = hitbox.getCollidable().getWidth();
                pheight = SCOPE;
            }else if(hitbox.getDirection() == player.RIGHT){
                px = hitbox.getCollidable().getX() + hitbox.getCollidable().getWidth();
                py = hitbox.getCollidable().getY();
                pwidth = SCOPE;
                pheight = hitbox.getCollidable().getHeight();
            }else if(hitbox.getDirection() == player.LEFT){
                px = hitbox.getCollidable().getX() - SCOPE;
                py = hitbox.getCollidable().getY();
                pwidth = SCOPE;
                pheight = hitbox.getCollidable().getHeight();
            }
            hitbox.setX(px);
            hitbox.setY(py);
            hitbox.setWidth(pwidth);
            hitbox.setHeight(pheight);
            hitbox.draw(g);
        }
    }

    @Override
    public void attack(Collidable player) {
        setHitbox(new HitBox(0, 0, 0, 0));
        hitbox.setCollidable(player);
        attackCooldownThread.setRecover(true);
    }
}
