/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psychiatric;

import elements.enemies.Walker;
import elements.enemies.Enemy;
import elements.enemies.Shooter;
import elements.Reward;
import elements.player.Player;
import elements.Sprite;
import elements.player.Heart;
import interfaces.Collidable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Room extends Sprite{

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    
    private Player player;
    
    private Room roomUp;
    private Room roomDown;
    private Room roomRight;
    private Room roomLeft;
    
    private boolean doorUp;
    private boolean doorDown;
    private boolean doorRight;
    private boolean doorLeft;
    
    private ArrayList<Enemy> enemies;
    private ArrayList<Reward> rewards;
    private ArrayList<Collidable> collidables;
    
    public Room() {
        super(0, 0, WIDTH, HEIGHT, Color.GRAY);
        
        collidables = new ArrayList<>();
        rewards = new ArrayList<>();
        enemies = new ArrayList<>();
        
        roomUp = null;
        roomDown = null;
        roomRight = null;
        roomLeft = null;
        
        doorUp = false;
        doorDown = false;
        doorRight = false;
        doorLeft = false;
    }

    @Override
    public void draw(Graphics g) {
        //FLOOR
        g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT);
        
        //COLLIDABLES
        for(Collidable collidable: collidables){
            collidable.draw(g); //!!CUIDADO!!
        }
        //ENEMIES
        for(Enemy enemy: enemies){
            enemy.draw(g);
        }
        //REWARDS
        for(Reward reward: rewards){
            reward.draw(g);
        }
        //HEARTS
        for(Heart heart: player.getHearts()){
            heart.draw(g);
        }
        //INVENTORY
        player.getInventory().draw(g);
        player.draw(g);
    }

    //NEXTROOM
    public int checkEntry(){
        if(player.getY() < 0){
            return Collidable.UP;
        }
        if(player.getY() > Room.HEIGHT){
            return Collidable.DOWN;
        }
        if(player.getX() > Room.WIDTH){
            return Collidable.RIGHT;
        }
        if(player.getX() < 0){
            return Collidable.LEFT;
        }
        return -1; //NOTNEXTROOM
    }
    
    public int keyPressed(int code){
        if(code == KeyEvent.VK_UP | code == KeyEvent.VK_DOWN | code == KeyEvent.VK_RIGHT | code == KeyEvent.VK_LEFT){
            player.move(code);
        }
        return checkEntry();
    }
    
    public void mousePressed(int code){
        if(code == MouseEvent.BUTTON1){
            player.attack();
        }
        for(Enemy enemy: enemies){
            if(enemy.getLifeBar() == 0){
                enemies.remove(enemy);
                break;
            }
        }
    }
    
    public void addCollidable(Collidable collidable) {
        collidables.add(collidable);
    }

    public void addWalker(int numberEnemies){
        for(int i = 0; i < numberEnemies; i++){
            boolean aggregate;
            Enemy enemy = null;
            do{
                int px = (int) (Math.random() * (WIDTH));
                int py = (int) (Math.random() * (HEIGHT));
                enemy = new Walker(px, py); 
                aggregate = true;

                for(Collidable collidable: collidables){
                    if(enemy.checkCollision(collidable)){
                        aggregate = false;
                        break;
                    }
                }
            }while(!aggregate);
            enemies.add(enemy);
            enemy.setCollidables(collidables); //¿Se agrega a collidables?
        }
    }
    
    //---------------------ENEMIES---------------------
    public void addShooter(int numberEnemies){
        for(int i = 0; i < numberEnemies; i++){
            boolean aggregate;
            Enemy enemy = null;
            do{
                int px = (int) (Math.random() * (WIDTH));
                int py = (int) (Math.random() * (HEIGHT));
                enemy = new Shooter(px, py); 
                aggregate = true;

                for(Collidable collidable: collidables){
                    if(enemy.checkCollision(collidable)){
                        aggregate = false;
                        break;
                    }
                }
            }while(!aggregate);
            enemies.add(enemy);
            enemy.setCollidables(collidables); //¿Se agrega a collidables?
        }
    }
    
    public void addReward(int numberRewards){
        for(int i = 0; i < numberRewards; i++){
            boolean aggregate;
            Reward reward = null;
            do{
                int px = (int) (Math.random() * (WIDTH));
                int py = (int) (Math.random() * (HEIGHT));
                reward = new Reward(px, py);
                aggregate = true;

                for(Collidable collidable: collidables){
                    if(reward.checkCollision(collidable)){
                        aggregate = false;
                        break;
                    }
                }
            }while(!aggregate);
            rewards.add(reward); //¿Se agrega a collidables?
        }
    }
    //---------------------------------------------------
    
    //GETTERS AND SETTERS
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        player.setCollidables(collidables);
        for(Enemy enemy: enemies){
            enemy.setPlayer(player);
        }
    }
    
    public boolean isDoorUp() {
        return doorUp;
    }

    public boolean isDoorDown() {
        return doorDown;
    }

    public boolean isDoorRight() {
        return doorRight;
    }

    public boolean isDoorLeft() {
        return doorLeft;
    }

    public void setDoorUp(boolean doorUp) {
        this.doorUp = doorUp;
    }

    public void setDoorDown(boolean doorDown) {
        this.doorDown = doorDown;
    }

    public void setDoorRight(boolean doorRight) {
        this.doorRight = doorRight;
    }

    public void setDoorLeft(boolean doorLeft) {
        this.doorLeft = doorLeft;
    }

    public Room getRoomUp() {
        return roomUp;
    }

    public void setRoomUp(Room roomUp) {
        this.roomUp = roomUp;
    }

    public Room getRoomDown() {
        return roomDown;
    }

    public void setRoomDown(Room roomDown) {
        this.roomDown = roomDown;
    }

    public Room getRoomRight() {
        return roomRight;
    }

    public void setRoomRight(Room roomRight) {
        this.roomRight = roomRight;
    }

    public Room getRoomLeft() {
        return roomLeft;
    }

    public void setRoomLeft(Room roomLeft) {
        this.roomLeft = roomLeft;
    }
}
