/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements.player;

import threads.TouchCollisionThread;
import sprites.Sprite;
import elements.enemies.Enemy;
import elements.inventory.Inventory;
import elements.weapons.Weapon;
import elements.weapons.cut.Cutter;
import exceptions.FullInventoryException;
import interfaces.Boundable;
import interfaces.Collectible;
import interfaces.Collidable;
import interfaces.Damageable;
import interfaces.Iteractable;
import interfaces.Movable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import threads.CooldownThread;

public class Player extends Sprite implements Damageable, Movable{
    
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
    public static final int STEP = 5; 
    private int direction; 
    private DirectionThread vision;
    
    //ITERACTABLE
    private Inventory inventory;
    
    //BOUNDABLE
    private ArrayList<Boundable> boundables;
    
    //ENEMIES
    private ArrayList<Enemy> creatures;
    
    //COLLECTIBLES - PILLS
    private ArrayList<Collectible> collectibles;
    private static final int HEARTHPILL = 1;
    
    private int heartPills;
    private CooldownThread pillCooldown;
    public static final int COOLDOWN_PILL = 1000;
    
    //ITERACTABLES
    private ArrayList<Iteractable> iteractables;
    private boolean goingDown;
    
    public Player(int x, int y) {
        super(x, y, WIDTH, HEIGHT, Color.CYAN);
        hearts = new Heart[LIVES];
        heartCooldown = new CooldownThread(COOLDOWN_LIVE);
        
        int px = 30; 
        int py = 820;
        for(int h = 0; h < LIVES; h++){
            hearts[h] = new Heart(px, py);
            hearts[h].setLive(true);
            px += 80;
        }
        heartCooldown.start();
        
        inventory = new Inventory(this);
        //FIRST WEAPON
        inventory.addWeapon(new Cutter(this));
        
        heartPills = 0;
        pillCooldown = new CooldownThread(COOLDOWN_PILL);
        pillCooldown.start();
        
        vision = new DirectionThread(this);
        vision.start();
        
        touchCollisionThread = new TouchCollisionThread(this);
        touchCollisionThread.start();
        
        goingDown = false;
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
    
    @Override
    public void touched(Collidable collidable) {
        //ENEMY
        if(creatures != null){
            for(Enemy creature: creatures){
                if(collidable == creature){
                    takeDamage(0);
                }
            }
        }
        //COLLECTTBLES
        if(collectibles != null){
            for(Collectible collectible: collectibles){
                if(collidable == collectible){
                    if(collectible.getType() == 0){
                        try{
                            inventory.addWeapon(collectible.grabWeapon());
                            collectibles.remove(collectible);
                        }catch(FullInventoryException e){
                            collectible.throwWeapon();
                        }
                    }else if(collectible.getType() == HEARTHPILL){
                        heartPills ++;
                        collectibles.remove(collectible);
                    }
                    
                    setCollidables(boundables, creatures, collectibles, iteractables);
                }
            }
        }
        //ITERACTABLE
        if(iteractables != null){
            for(Iteractable iteractable: iteractables){
                if(iteractable == collidable){
                    goingDown = true;
                    
                }
            }
        }
    }
    
    public void move(ArrayList<Integer> keys){
        if(keys.contains(KeyEvent.VK_W)){
            y -= STEP;
            for(Boundable boundable: boundables){
                if(checkCollision(boundable)){
                    y += STEP;
                    break;
                }
            }   
        }
        if(keys.contains(KeyEvent.VK_S)){
            y += STEP;
            for(Boundable boundable: boundables){
                if(checkCollision(boundable)){
                    y -= STEP;
                    break;
                }
            }   
        }
        if(keys.contains(KeyEvent.VK_D)){
            x += STEP;
            for(Boundable boundable: boundables){
                if(checkCollision(boundable)){
                    x -= STEP;
                    break;
                }
            }   
        }
        if(keys.contains(KeyEvent.VK_A)){
            x -= STEP;
            for(Boundable boundable: boundables){
                if(checkCollision(boundable)){
                    x += STEP;
                    break;
                }
            }   
        }
    }
    
    public void changeWeapon(ArrayList<Integer> keys){
        if(keys.contains(KeyEvent.VK_Q)){
            inventory.changeSelectedWeapon(0);
        }else if(keys.contains(KeyEvent.VK_E)){
            inventory.changeSelectedWeapon(1);
        }
    }
    
    public void takePill(ArrayList<Integer> keys){
        if(!pillCooldown.isRecover()){
            if(keys.contains(KeyEvent.VK_1)){
                if(heartPills > 0){
                    for(Heart heart: hearts){ 
                        if(!heart.isLive()){
                            heart.setLive(true);
                            heartPills --;
                            pillCooldown.startCoolDown();
                            break;
                        }
                    }
                }
            }
            else if(keys.contains(KeyEvent.VK_2)){
            }
            else if(keys.contains(KeyEvent.VK_3)){
            }
        }
    }
    
    public void attack(int x, int y){
        //DIRECTION
        if(vision.getTriangleUp().contains(x, y)){
            direction = UP;
        }
        else if(vision.getTriangleDown().contains(x, y)){
            direction = DOWN;
        }
        else if(vision.getTriangleRight().contains(x, y)){
            direction = RIGHT;
        }
        else if(vision.getTriangleLeft().contains(x, y)){
            direction = LEFT;
        }
        
        if(inventory.getSelectedWeapon() != null){
            inventory.getSelectedWeapon().attack(this);
        }
    }
    
    @Override
    public void takeDamage(int damage) { //private
        if(!heartCooldown.isRecover()){
            for(int h = LIVES - 1; h >= 0; h--){
                if(hearts[h].isLive()){
                    hearts[h].setLive(false);
                    heartCooldown.startCoolDown();
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
    
    //GETTERS AND SETTERS
    public Heart[] getHearts() {
        return hearts;
    }

    public ArrayList<Boundable> getBoundables() {
        return boundables;
    }
    
    public void setCollidables(ArrayList<Boundable> boundables, ArrayList<Enemy> creatures, ArrayList<Collectible> collectibles, ArrayList<Iteractable> iteractables){
        this.boundables = boundables;
        this.creatures = creatures;
        this.collectibles = collectibles;
        this.iteractables = iteractables;
        
        ArrayList<Collidable> collidables = new ArrayList<>();
        for(Boundable boundable: boundables){
            collidables.add(boundable);
        }
        for(Enemy creature: creatures){
            collidables.add(creature);
        }
        for(Collectible collectible: collectibles){
            collidables.add(collectible);
        }
        if(iteractables != null){
            for(Iteractable iteractable: iteractables){
                collidables.add(iteractable);
            }
        }
        
        touchCollisionThread.addCollidable(collidables);
    }
    
    public Inventory getInventory() {
        return inventory;
    }
    
    //PILLS
    public int getHearthPills(){
        return heartPills;
    }
    
    public Weapon getActualWeapon(){
        return inventory.getSelectedWeapon();
    }

    public int getDirection() {
        return direction;
    }

    public ArrayList<Enemy> getCreatures() {
        return creatures;
    }

    @Override
    public int getStep() {
        return STEP;
    }

    @Override
    public int getCoolDownMove() {
        return 0;
    }

    public boolean isGoingDown() {
        return goingDown;
    }

    public void setGoingDown(boolean goingDown) {
        this.goingDown = goingDown;
    }
}
