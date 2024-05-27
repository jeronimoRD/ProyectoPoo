/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.weapons;

import sprites.Sprite;
import elements.player.Player;
import java.awt.Color;
import java.awt.Graphics;
import threads.CooldownThread;

public abstract class Weapon extends Sprite{

    //OWNER
    protected Player player;
    
    //CHARACTERISTICS
    protected int damage;
    protected int timeStunned;
    protected boolean attacking;
    
    protected CooldownThread cooldownThread;
    
    public Weapon(int x, int y, int width, int height, Color color, Player player, int cooldownAttack, int timeStunned) {
        super(x, y, width, height, color);
        this.player = player;
        this.damage = 0;
        this.timeStunned = timeStunned;
        this.attacking = false;
    }

    @Override
    public abstract void draw(Graphics g);
    
    public abstract void attack(Player player);

    //GETTERS AND SETTERS
    public int getDamage() {
        return damage;
    }

    public int getTimeStunned() {
        return timeStunned;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }
}
