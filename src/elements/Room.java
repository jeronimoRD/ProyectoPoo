/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elements;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import psychiatric.Player;

public abstract class Room{

    protected Player player;
    
    protected File editor;
    
    protected boolean doorUp;
    protected boolean doorDown;
    protected boolean doorRight;
    protected boolean doorLeft;
    
    public static final int DOOR = 'P';
    public static final int WALL = 'X';
    public static final int FLOOR = 'O';
    
    protected Quadrant[][] quadrants; //Array normal
    public static final int QUADRANTS_WIDTH = 3;
    public static final int QUADRANTS_HEIGHT = 3;
    
    public Room(File editor) {
        this.editor = editor;
    }

    public void draw(Graphics g) {
        for(Quadrant[] row: quadrants){
            for(Quadrant quadrant: row){
                quadrant.draw(g);
            }
        }
        player.draw(g);
    }

    public int checkEntry(){
        if(player.getY() < 0){
            return 0;
        }
        if(player.getY() > QUADRANTS_HEIGHT*Quadrant.HEIGHT){
            return 1;
        }
        if(player.getX() > QUADRANTS_HEIGHT*Quadrant.HEIGHT){
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
 
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
}
