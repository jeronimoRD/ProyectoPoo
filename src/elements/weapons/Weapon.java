/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.weapons;

import elements.Sprite;
import elements.player.Player;
import interfaces.Collidable;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Weapon extends Sprite{

    //OWNER
    protected Player player;
    
    //CHARACTERISTICS
    protected int damage;
    protected HitBox hitbox;
    protected boolean attacking;
    
    public Weapon(int x, int y, int width, int height, Color color, Player player) {
        super(x, y, width, height, color);
        this.player = player;
        this.damage = 0;
        this.hitbox = null;
        this.attacking = false;
    }

    @Override
    public abstract void draw(Graphics g);
    
    public abstract void attack(Player player);

    //GETTERS AND SETTERS
    public HitBox getHitbox() {
        return hitbox;
    }
    
    public void setHitbox(HitBox hitbox) {
        this.hitbox = hitbox;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }
}
