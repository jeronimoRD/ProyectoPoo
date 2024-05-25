/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psychiatric;

import elements.enemies.walker.Walker;
import elements.enemies.Enemy;
import elements.player.Player;
import another.Sprite;
import elements.collectibles.*;
import elements.enemies.shooter.*;
import elements.player.Heart;
import interfaces.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Room extends Sprite{

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    
    private Room roomUp;
    private Room roomDown;
    private Room roomRight;
    private Room roomLeft;
    
    private boolean doorUp;
    private boolean doorDown;
    private boolean doorRight;
    private boolean doorLeft;
    
    private Player player;
    private ArrayList<Enemy> enemies;
    private ArrayList<Collectible> collectibles;
    private ArrayList<Boundable> boundables;
    private ArrayList<Collapsible> collapsibles;
    
    public Room() {
        super(0, 0, WIDTH, HEIGHT, Color.GRAY);
        
        boundables = new ArrayList<>();
        collapsibles = new ArrayList<>();
        collectibles = new ArrayList<>();
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
        
        //BOUNDABLES
        for(Boundable boundable: boundables){
            boundable.draw(g);
        }
        //ENEMIES
        for(Enemy enemy: enemies){
            enemy.draw(g); 
        }
        //REWARDS
        for(Collectible collectible: collectibles){
            collectible.draw(g);
        }
        //HEARTS
        for(Heart heart: player.getHearts()){
            heart.draw(g);
        }
        //INVENTORY
        player.getInventory().draw(g);
        player.draw(g);
        
        update();
    }
    
    public void update(){
        ArrayList<Enemy> eliminatedCreatures = new ArrayList<>();
        for(Enemy enemy: enemies){
            if(enemy.getLifeBar() == 0){
                eliminatedCreatures.add(enemy);
            }
        }for(int e = 0; e < eliminatedCreatures.size(); e++){
            enemies.remove(eliminatedCreatures.get(e));
        }
        
        ArrayList<Collectible> elimatedCollectible = new ArrayList<>();
        for(Collectible collectible: collectibles){
            if(collectible.getGrabed() == true){
                elimatedCollectible.add(collectible);
            }
        }for(int c = 0; c < elimatedCollectible.size(); c++){
            collectibles.remove(elimatedCollectible.get(c));
        }
        
        if(enemies.size() <= 0){
            for(int d = 0; d < collapsibles.size(); d++){
                boundables.remove(collapsibles.get(d));
            }
        }
    }

    //NEXTROOM
    public int checkEntry(){
        if(player.getY() < 0){
            for(Enemy enemy: enemies){
                enemy.setPlayer(null);
            }
            return Boundable.UP;
        }
        if(player.getY() > Room.HEIGHT){
            for(Enemy enemy: enemies){
                enemy.setPlayer(null);
            }
            return Boundable.DOWN;
        }
        if(player.getX() > Room.WIDTH){
            for(Enemy enemy: enemies){
                enemy.setPlayer(null);
            }
            return Boundable.RIGHT;
        }
        if(player.getX() < 0){
            for(Enemy enemy: enemies){
                enemy.setPlayer(null);
            }
            return Boundable.LEFT;
        }
        return -1; //NOTNEXTROOM
    }
    
    public int keyPressed(int code){
        if(code == KeyEvent.VK_W | code == KeyEvent.VK_S | code == KeyEvent.VK_D | code == KeyEvent.VK_A){
            player.move(code);
        }
        if(code == KeyEvent.VK_1 | code == KeyEvent.VK_2){
            player.changeWeapon(code);
        }
        return checkEntry();
    }
    
    public void mousePressed(int code){
        if(code == MouseEvent.BUTTON1){
            player.attack();
        }
    }
    
    public void addBoundable(Boundable boundable) {
        boundables.add(boundable);
    }
    
    public void addCollapsible(Collapsible collapsible){
        collapsibles.add(collapsible);
    }
    
    //TEST -> RANDOM
    //---------------------ADD---------------------
    public void addWalker(int numberEnemies){
        for(int i = 0; i < numberEnemies; i++){
            boolean aggregate;
            Enemy enemy = null;
            do{
                int px = (int) (Math.random() * (WIDTH));
                int py = (int) (Math.random() * (HEIGHT));
                enemy = new Walker(px, py); 
                aggregate = true;

                for(Collidable collidable: boundables){
                    if(enemy.checkCollision(collidable)){
                        aggregate = false;
                        break;
                    }
                }
            }while(!aggregate);
            enemies.add(enemy);
            enemy.setBoundables(boundables);
        }
    }
    
    public void addShooter(int numberEnemies){
        for(int i = 0; i < numberEnemies; i++){
            boolean aggregate;
            Enemy enemy = null;
            do{
                int px = (int) (Math.random() * (WIDTH));
                int py = (int) (Math.random() * (HEIGHT));
                enemy = new ShooterAllDirections(px, py); 
                aggregate = true;

                for(Collidable collidable: boundables){
                    if(enemy.checkCollision(collidable)){
                        aggregate = false;
                        break;
                    }
                }
            }while(!aggregate);
            enemies.add(enemy);
            enemy.setBoundables(boundables); //¿Se agrega a collidables?
        }
    }
    
    public void addShooterChase(int numberEnemies){
        for(int i = 0; i < numberEnemies; i++){
            boolean aggregate;
            Enemy enemy = null;
            do{
                int px = (int) (Math.random() * (WIDTH));
                int py = (int) (Math.random() * (HEIGHT));
                enemy = new ShooterChase(px, py); 
                aggregate = true;

                for(Collidable collidable: boundables){
                    if(enemy.checkCollision(collidable)){
                        aggregate = false;
                        break;
                    }
                }
            }while(!aggregate);
            enemies.add(enemy);
            enemy.setBoundables(boundables); //¿Se agrega a collidables?
        }
    }
    
    public void addStick(int numberRewards){
        for(int i = 0; i < numberRewards; i++){
            boolean aggregate;
            WeaponC weapon = null;
            do{
                int px = (int) (Math.random() * (WIDTH));
                int py = (int) (Math.random() * (HEIGHT));
                //!TEST!
                weapon = new StickC(px, py);
                aggregate = true;

                for(Collidable collidable: boundables){
                    if(weapon.checkCollision(collidable)){
                        aggregate = false;
                        break;
                    }
                }
            }while(!aggregate);
            collectibles.add(weapon);
        }
    }
    
    public void addGun(int numberRewards){
        for(int i = 0; i < numberRewards; i++){
            boolean aggregate;
            WeaponC weapon = null;
            do{
                int px = (int) (Math.random() * (WIDTH));
                int py = (int) (Math.random() * (HEIGHT));
                //!TEST!
                weapon = new GunC(px, py);
                aggregate = true;

                for(Collidable collidable: boundables){
                    if(weapon.checkCollision(collidable)){
                        aggregate = false;
                        break;
                    }
                }
            }while(!aggregate);
            collectibles.add(weapon);
        }
    }
    //---------------------------------------------------
    
    //GETTERS AND SETTERS
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        player.setCollidables(boundables, enemies, collectibles);
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
