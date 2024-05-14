/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rooms;

import elements.Sprite;
import interfaces.Collidable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import psychiatric.Player;

public abstract class Room extends Sprite{

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    
    public static final int DOOR = 'P';
    public static final int WALL = 'X';
    public static final int FLOOR = 'O';
    
    protected Player player;
    
    protected File editor;
    
    protected Room roomUp;
    protected Room roomDown;
    protected Room roomRight;
    protected Room roomLeft;
    
    protected boolean doorUp;
    protected boolean doorDown;
    protected boolean doorRight;
    protected boolean doorLeft;
    
    protected ArrayList<Collidable> collisions;
    
    public Room(File editor) {
        super(0, 0, WIDTH, HEIGHT, Color.GRAY);
        this.editor = editor;
        
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
        
        for(Collidable collision: collisions){
            collision.draw(g);
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
 
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        player.setCollisions(collisions);
    }
    
}
