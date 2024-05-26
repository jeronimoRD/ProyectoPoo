/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psychiatric;
import elements.player.Player;
import exceptions.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Psychiatric {
    private Level [] levels;
    private Level actualLevel;
    public static final int LEVELS = 3;
    public static final int ROOMS_PER_LEVEL= 8;
    public static final int REWARDS_PER_LEVEL = 2;
    
    public Psychiatric() {
        Player player = new Player(Room.WIDTH/2, Room.HEIGHT/2);
        levels = new Level[LEVELS];
        
        //CREATE LEVELS
        for(int i = 0; i < LEVELS; i++){
            try {
                levels[i] = new Level(ROOMS_PER_LEVEL, REWARDS_PER_LEVEL);
            }catch (IOException e1) { 
                //EXCEPTION FILES
                break;
            }catch (ImpossibleStructureRoomsException e2){ 
                //EXCEPTION NUMBERS OF ROOMS
                break;
            }
        }
        
        //INITIAL LEVEL
        actualLevel = levels[0];
        actualLevel.setPlayer(player);
    }
    
    public void keyPressed(int code){
        if(code == KeyEvent.VK_W | code == KeyEvent.VK_S | code == KeyEvent.VK_D | code == KeyEvent.VK_A | code == KeyEvent.VK_Q | code == KeyEvent.VK_E
          |code == KeyEvent.VK_1 |code == KeyEvent.VK_2 |code == KeyEvent.VK_3){
            actualLevel.keyPressed(code);
        }
    }
    
    public void mousePressed(int code){
        if(code == MouseEvent.BUTTON1){
            actualLevel.mousePressed(code);
        }
    }
    
    //GETTERS AND SETTERS
    public int getScreenWidth(){
        return Room.WIDTH;
    }
    
    public int getScreenHeight(){
        return Room.HEIGHT;
    }
    
    public void draw(Graphics g) {
        actualLevel.draw(g);
    }
}
