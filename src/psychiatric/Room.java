/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psychiatric;

import elements.Sprite;
import enemies.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import interfaces.Solid;

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
    private ArrayList<Solid> solids;
    
    public Room() {
        super(0, 0, WIDTH, HEIGHT, Color.GRAY);
        
        solids = new ArrayList<>();
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
        g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT);
        
        for(Enemy enemy: enemies){
            enemy.draw(g);
        }
        for(Reward reward: rewards){
            reward.draw(g);
        }
        for(Solid solid: solids){
            solid.draw(g);
        }
        player.draw(g);
    }

    public int checkEntry(){
        if(player.getY() < 0){
            return 0;
        }
        if(player.getY() > Room.HEIGHT){
            return 1;
        }
        if(player.getX() > Room.WIDTH){
            return 2;
        }
        if(player.getX() < 0){
            return 3;
        }
        return -1;
    }
    
    public int keyPressed(int code){
        if(code == KeyEvent.VK_UP | code == KeyEvent.VK_DOWN | code == KeyEvent.VK_RIGHT | code == KeyEvent.VK_LEFT){
            player.move(code);
        }
        return checkEntry();
    }
    
    public void addSolid(Solid solid) {
        solids.add(solid);
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

                for(Solid solid: solids){
                    if(enemy.checkCollisionHitbox(solid)){
                        aggregate = false;
                        break;
                    }
                }
            }while(!aggregate);
            enemies.add(enemy);
            enemy.setSolids(solids);
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

                for(Solid solid: solids){
                    if(reward.checkCollisionHitbox(solid)){
                        aggregate = false;
                        break;
                    }
                }
            }while(!aggregate);
            rewards.add(reward);
        }
    }
    
    //GETTERS AND SETTERS
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        player.setSolids(solids);
        for(Enemy enemy: enemies){
            enemy.setDamageable(player);
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
