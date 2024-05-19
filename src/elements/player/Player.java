/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.player;

import threads.TouchCollisionThread;
import elements.Sprite;
import elements.enemies.Creature;
import elements.inventory.Inventory;
import elements.weapons.Weapon;
import interfaces.Boundable;
import interfaces.Collidable;
import interfaces.Damageable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import threads.CooldownThread;

public class Player extends Sprite implements Damageable{
    
    //CHARACTERISTICS
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    
    //COLLIDABLES
    private TouchCollisionThread touchCollisionThread;
    
    //LIVE
    public static final int LIVES = 3;
    public static final int COOLDOWN_LIVE = 2000;
    private CooldownThread heartCooldown;
    private Heart[] hearts;
    
    //MOVE
    public static final int STEP = 10; 
    private int lastMove = -1;
    private int direction; //¿Aburrido?
    
    //ITERACTABLE
    private Inventory inventory;
    
    //BOUNDABLE
    private ArrayList<Boundable> boundables;
    
    //ENEMIES
    private ArrayList<Creature> creatures;
    
    
    public Player(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.CYAN);
        hearts = new Heart[LIVES];
        heartCooldown = new CooldownThread();
        
        int px = 30; //dimensions of hearts
        int py = 40;
        for(int h = 0; h < LIVES; h++){
            hearts[h] = new Heart(px, py);
            hearts[h].setLive(true);
            px += 60;
        }
        heartCooldown.setTime(COOLDOWN_LIVE);
        heartCooldown.start();
        
        inventory = new Inventory(this);
        
        touchCollisionThread = new TouchCollisionThread(this);
        touchCollisionThread.start();
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT);
        
        if(inventory.getSelectedWeapon() != null){ //DRAW OBJETC ON HAND
            inventory.getSelectedWeapon().setX(x + WIDTH + 10);//!!Test!!
            inventory.getSelectedWeapon().setY(y - 10);//!!Test!!
            inventory.getSelectedWeapon().draw(g);
        }
    }
    
    public void move(int code){
        if(code == KeyEvent.VK_UP){
            direction = UP; //¿Aburrido?
            lastMove = UP;
            y -= STEP;
        }
        if(code == KeyEvent.VK_DOWN){
            direction = DOWN; //¿Aburrido?
            lastMove = DOWN;
            y += STEP;
        }
        if(code == KeyEvent.VK_RIGHT){
            direction = RIGHT; //¿Aburrido?
            lastMove = RIGHT;
            x += STEP;
        }
        if(code == KeyEvent.VK_LEFT){
            direction = LEFT; //¿Aburrido?
            lastMove = LEFT;
            x -= STEP;
        }
    }
    
    public void attack(){
        if(inventory.getSelectedWeapon() != null){
            inventory.getSelectedWeapon().attack(this);
            inventory.getSelectedWeapon().getHitbox().setDirection(direction);
        }
    }
    
    @Override
    public void takeDamage(int damage) { //private
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
    public void die() {
        //GAME OVER
    }
    
    @Override
    public void touched(Collidable collidable) {
        //WALL
        for(Boundable boundable: boundables){
            if(collidable == boundable){
                if(lastMove == UP){
                    y = boundable.getY()+boundable.getHeight();
                }
                if(lastMove == DOWN){
                    y = boundable.getY() - HEIGHT;
                }
                if(lastMove == RIGHT){
                    x = boundable.getX() - WIDTH;
                }
                if(lastMove == LEFT){
                    x = boundable.getX()+boundable.getWidth();
                }
            }
        }
        //ENEMY
        if(creatures != null){
            for(Creature creature: creatures){
                if(collidable == creature){
                    takeDamage(0);
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

    public ArrayList<Boundable> getBoundables() {
        return boundables;
    }
    
    public void setBoundables(ArrayList<Boundable> boundables) {
        this.boundables = boundables;
        
        for(Boundable boundable: boundables){
            touchCollisionThread.addCollidable(boundable);
        }
    }
    
    public void setCreatures(ArrayList<Creature> creatures) {
        this.creatures = creatures;
        
        for(Creature creature: creatures){
            touchCollisionThread.addCollidable(creature);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
    
    public Weapon getActualWeapon(){
        return inventory.getSelectedWeapon();
    }

    public int getDirection() {
        return direction;
    }

    
}
