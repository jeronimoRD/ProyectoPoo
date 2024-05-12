/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psychiatric;
import quadrants.Quadrant;
import rooms.Room;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Psychiatric {
    private Level [] levels;
    private Level actualLevel;
    public static final int LEVELS = 3; //Array normal
    
    private Player player;
    
    public Psychiatric() {
        Player player = new Player(Room.QUADRANTS_WIDTH*Quadrant.WIDTH/2, Room.QUADRANTS_HEIGHT*Quadrant.HEIGHT/2);
        levels = new Level[LEVELS];
        
        for(int i = 0; i < LEVELS; i++){
            levels[i] = new Level();
        }
        
        actualLevel = levels[0];
        actualLevel.setPlayer(player);
    }
    
    public int getWidth(){
        return Quadrant.WIDTH * Room.QUADRANTS_WIDTH;
    }
    
    public int getHeight(){
        return Quadrant.HEIGHT * Room.QUADRANTS_HEIGHT;
    }
    
    public void draw(Graphics g) {
        actualLevel.draw(g);
    }
    
    public void keyPressed(int code){
        if(code == KeyEvent.VK_UP | code == KeyEvent.VK_DOWN | code == KeyEvent.VK_RIGHT | code == KeyEvent.VK_LEFT){
            actualLevel.keyPressed(code);
        }
    }
}
