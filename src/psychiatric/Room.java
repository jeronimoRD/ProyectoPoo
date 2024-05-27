/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psychiatric;

import elements.collectibles.pills.HeartPill;
import elements.collectibles.pills.Pill;
import elements.collectibles.weapons.GunC;
import elements.collectibles.weapons.WeaponC;
import sprites.Menu;
import elements.enemies.walker.Walker;
import elements.enemies.Enemy;
import elements.player.Player;
import sprites.Sprite;
import elements.enemies.shooter.*;
import elements.furniture.Stairs;
import exceptions.NoWeaponToThrows;
import interfaces.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Room extends Sprite{
    
    private boolean goingDown;
    
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
    private ArrayList<Iteractable> iteractables;
    private ArrayList<Enemy> enemies;
    private ArrayList<Collectible> collectibles;
    private ArrayList<Boundable> boundables;
    private ArrayList<Collapsible> collapsibles;
    
    private Menu menu;
    
    public Room() {
        super(0, 0, WIDTH, HEIGHT, Color.GRAY);
        
        boundables = new ArrayList<>();
        collapsibles = new ArrayList<>();
        collectibles = new ArrayList<>();
        enemies = new ArrayList<>();
        iteractables = new ArrayList<>();
        
        roomUp = null;
        roomDown = null;
        roomRight = null;
        roomLeft = null;
        
        doorUp = false;
        doorDown = false;
        doorRight = false;
        doorLeft = false;
        
        //INVENTORY
        menu = new Menu(0, HEIGHT, WIDTH, 100);
        
        goingDown = false;
    }

    @Override
    public void draw(Graphics g) {
        //FLOOR
        g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT);
        
        //BOUNDABLES
        try{
            for(Boundable boundable: boundables){
                boundable.draw(g);
            }
            //ITERACTABLES
            for(Iteractable iteractable: iteractables){
                iteractable.draw(g);
            }

            //ENEMIES
            for(Enemy enemy: enemies){
                enemy.draw(g); 
            }
            //REWARDS
            for(Collectible collectible: collectibles){
                collectible.draw(g);
            }
        }catch(ConcurrentModificationException e){}
        
        //PLAYER
        if(player != null){
            player.draw(g);
            menu.draw(g);
            player.getInventory().draw(g);
        }    
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
        
        if(enemies.size() <= 0){
            for(int d = 0; d < collapsibles.size(); d++){
                boundables.remove(collapsibles.get(d));
            }
        }
        
        if(player != null){
            if(player.isGoingDown()){
                
                
                goingDown = true;
            }
        }
    }

    //NEXTROOM
    public int checkEntry(){
        if(player != null){
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
        }
        return -1; //NOTNEXTROOM
    }
    
    public int keyPressed(ArrayList<Integer> keys){
        if(player != null){
            if(keys.contains(KeyEvent.VK_W) | keys.contains(KeyEvent.VK_S) | keys.contains(KeyEvent.VK_A) | keys.contains(KeyEvent.VK_D)){
                player.move(keys);
            }
            if(keys.contains(KeyEvent.VK_Q) | keys.contains(KeyEvent.VK_E)){
                player.changeWeapon(keys);
            }

            if(keys.contains(KeyEvent.VK_1) | keys.contains(KeyEvent.VK_2)| keys.contains(KeyEvent.VK_3)){
                player.takePill(keys);
            }
            
            if(keys.contains(KeyEvent.VK_F)){
                try{
                    Collectible c = player.drop();
                    for(Boundable boundable: boundables){
                        if(c.checkCollision(boundable)){
                            player.getInventory().addWeapon(c.grabWeapon());
                            throw new NoWeaponToThrows();
                        }
                    }
                    collectibles.add(c);
                    player.setCollidables(boundables, enemies, collectibles, iteractables);
                }catch(NoWeaponToThrows e){};
            }
        }
        
        return checkEntry();
    }
    
    public void mousePressed(int code, int x, int y){
        if(code == MouseEvent.BUTTON1){
            player.attack(x, y);
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
    public void addIteractable(int numberIteractables) {
        for(int i = 0; i < numberIteractables; i++){
            boolean aggregate;
            Stairs stair = null;
            do{
                int px = (int) (Math.random() * (WIDTH));
                int py = (int) (Math.random() * (HEIGHT));
                stair = new Stairs(px, py); 
                aggregate = true;

                for(Collidable collidable: boundables){
                    if(stair.checkCollision(collidable)){
                        aggregate = false;
                        break;
                    }
                }
            }while(!aggregate);
            iteractables.add(stair);
        }
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
            enemy.setBoundables(boundables);
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
    
    public void addHearthPill(int numberRewards){
        for(int i = 0; i < numberRewards; i++){
            boolean aggregate;
            Pill pill = null;
            do{
                int px = (int) (Math.random() * (WIDTH));
                int py = (int) (Math.random() * (HEIGHT));
                //!TEST!
                pill = new HeartPill(px, py);
                aggregate = true;

                for(Collidable collidable: boundables){
                    if(pill.checkCollision(collidable)){
                        aggregate = false;
                        break;
                    }
                }
            }while(!aggregate);
            collectibles.add(pill);
        }
    }
    
    public void addFurtinure(int numberFurtinure, ArrayList<Boundable> furtinures){
        for(int i = 0; i < numberFurtinure; i++){
            boolean aggregate;
            //RANDOM FURTINURE
            int index = (int) (Math.random() * (furtinures.size()));
            Boundable furtinure = furtinures.get(index);
            
            do{
                int px = (int) (Math.random() * (WIDTH));
                int py = (int) (Math.random() * (HEIGHT));
                
                furtinure.setX(px);
                furtinure.setY(py);
                
                aggregate = true;
                for(Collidable collidable: boundables){
                    if(furtinure.checkCollision(collidable)){
                        aggregate = false;
                        break;
                    }
                }
            }while(!aggregate);
            boundables.add(furtinure);
        }
    }
    //---------------------------------------------------
    
    //GETTERS AND SETTERS
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        player.setCollidables(boundables, enemies, collectibles, iteractables);
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

    public boolean isGoingDown() {
        return goingDown;
    }

    public void setGoingDown(boolean goingDown) {
        this.goingDown = goingDown;
    }
}
