/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.player;

import elements.Sprite;
import elements.inventory.Inventory;
import elements.weapons.Weapon;
import interfaces.Collidable;
import interfaces.Damageable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import threads.CooldownThread;

public class Player extends Sprite implements Damageable{ //IS COLLIDABLE TOO
    
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    
    public static final int LIVES = 3;
    public static final int STEP = 10;
    public static final int COOLDOWN_LIVE = 2000;
    
    private ArrayList<Collidable> collidables;
    private CooldownThread heartCooldown;
    private Heart[] hearts;
    
    private Inventory inventory;
    
    public Player(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.CYAN);
        hearts = new Heart[LIVES];
        heartCooldown = new CooldownThread();
        
        int px = 30;
        int py = 40;
        for(int h = 0; h < LIVES; h++){
            hearts[h] = new Heart(px, py);
            hearts[h].setLive(true);
            px += 60;
        }
        heartCooldown.setTime(COOLDOWN_LIVE);
        heartCooldown.start();
        
        inventory = new Inventory();
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT);
        
        if(inventory.getSelectedWeapon() != null){
            inventory.getSelectedWeapon().setX(x + WIDTH + 10);//!!Test!!
            inventory.getSelectedWeapon().setY(y - 10);//!!Test!!
            inventory.getSelectedWeapon().draw(g);
        }
    }
    
    public void move(int code){ // REDUNDANT?
        if(code == KeyEvent.VK_UP){
            y -= STEP;
            for(Collidable collidable: collidables){
                if(checkCollision(collidable)){
                    y = collidable.getY()+collidable.getHeight();
                    return;
                }
            }
        }
        if(code == KeyEvent.VK_DOWN){
            y += STEP;
            for(Collidable collidable: collidables){
                if(checkCollision(collidable)){
                    y = collidable.getY() - HEIGHT;
                    return;
                }
            }
        }
        if(code == KeyEvent.VK_RIGHT){
            x += STEP;
            for(Collidable collidable: collidables){
                if(checkCollision(collidable)){
                    x = collidable.getX() - WIDTH;
                    return;
                }
            }
        }
        if(code == KeyEvent.VK_LEFT){
            x -= STEP;
            for(Collidable collidable: collidables){
                if(checkCollision(collidable)){
                    x = collidable.getX()+collidable.getWidth();
                    return;
                }
            }
        }
    }
    
    public void attack(){
        if(inventory.getSelectedWeapon() != null){
            inventory.getSelectedWeapon().attack();
        }
    }
    
    @Override
    public void takeDamage(int damage) {
        if(!heartCooldown.isRecover()){
            for(int h = LIVES - 1; h >= 0; h--){
                if(hearts[h].isLive()){
                    hearts[h].setLive(false);
                    heartCooldown.setRecover(true);
                    break;
                }
            }
        }
    }

    @Override
    public boolean checkCollision(Collidable collidable) {
        if((collidable.getY() + collidable.getHeight() > y  & y >= collidable.getY()) & (collidable.getX() + collidable.getWidth() > x & x >= collidable.getX())){
            return true;
        }
        if((collidable.getY() + collidable.getHeight() >= y + height & y + height > collidable.getY()) & (collidable.getX() + collidable.getWidth() >= x + width & x + width > collidable.getX())){
            return true;
        }
        if((collidable.getY() + collidable.getHeight() > y & y > collidable.getY()) & (collidable.getX() + collidable.getWidth() >= x + width & x + width > collidable.getX())){
            return true;
        }
        if((collidable.getY() + collidable.getHeight() >= y + height & y + height > collidable.getY()) & (collidable.getX() + collidable.getWidth() > x & x > collidable.getX())){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkCollision(Collidable collidable, int direction) {
        if(direction == UP){
            if(y == collidable.getY() + collidable.getHeight()){
                if(x <= collidable.getX() & collidable.getX() <= x + width){
                    return true;
                }
                else if(x <= collidable.getX() + collidable.getWidth() & collidable.getX() + collidable.getWidth() <= x + width){
                    return true;
                }
            }else{
                return false;
            }
        }
        
        else if(direction == DOWN){
            if(y + height == collidable.getY()){
                if(x <= collidable.getX() & collidable.getX() <= x + width){
                    return true;
                }
                else if(x <= collidable.getX() + collidable.getWidth() & collidable.getX() + collidable.getWidth() <= x + width){
                    return true;
                }
            }else{
                return false;
            }
        }
        
        else if(direction == LEFT){
            if(x == collidable.getX() + collidable.getWidth()){
                if(y <= collidable.getY() & collidable.getY() <= y + height){
                    return true;
                }
                else if(y <= collidable.getY() + collidable.getHeight() & collidable.getY() + collidable.getHeight() <= y + height){
                    return true;
                }
            }else{
                return false;
            }
        }
        
        else if(direction == RIGHT){
            if(x + width == collidable.getX()){
                if(y <= collidable.getY() & collidable.getY() <= y + height){
                    return true;
                }
                else if(y <= collidable.getY() + collidable.getHeight() & collidable.getY() + collidable.getHeight() <= y + height){
                    return true;
                }
            }else{
                return false;
            }
        }
        return false;
    }
    
    //GETTERS AND SETTERS
    public Heart[] getHearts() {
        return hearts;
    }

    public ArrayList<Collidable> getCollidables() {
        return collidables;
    }
    
    public void setCollidables(ArrayList<Collidable> collidable) {
        this.collidables = collidable;
    }

    public Inventory getInventory() {
        return inventory;
    }
    
    public Weapon getActualWeapon(){
        return inventory.getSelectedWeapon();
    }
    
    
}
