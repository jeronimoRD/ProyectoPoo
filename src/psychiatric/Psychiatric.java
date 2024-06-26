/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psychiatric;
import elements.player.Player;
import exceptions.*;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Psychiatric {
    private Level [] levels;
    private Level actualLevel;
    public static final int LEVELS = 3;
    
    //LEVEL 1
    public static final int ROOMS_PER_LEVEL= 8;
    public static final int REWARDS_PER_LEVEL = 2;
    public static final int ENVIROMENT_LEVEL = 1;
    public static final int MAX_ENEMIES = 3;
    
    
    public Psychiatric() {
        Player player = new Player(Room.WIDTH/2, Room.HEIGHT/2);
        levels = new Level[LEVELS];
        
        //CREATE LEVELS
        for(int i = 0; i < LEVELS; i++){
            try {
                levels[i] = new Level(ROOMS_PER_LEVEL, REWARDS_PER_LEVEL, ENVIROMENT_LEVEL, MAX_ENEMIES);
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
    
    public void keyPressed(ArrayList<Integer> keys){
        actualLevel.keyPressed(keys);
    }
    
    public void mousePressed(int code, int x, int y){
        if(code == MouseEvent.BUTTON1){
            actualLevel.mousePressed(code, x, y);
        }
    }
    
    public void draw(Graphics g) {
        actualLevel.draw(g);
        update();
    }
    
    public void update(){
        if(actualLevel.isGoingDown()){
            for(int l = 0; l < LEVELS; l++){
                if(actualLevel == levels[l]){
                    Player player = actualLevel.getPlayer();
                    player.setGoingDown(false);
                    actualLevel = levels[l + 1];
                    actualLevel.setPlayer(player);
                    break;
                }
            }
        }
    }
}
