/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psychiatric;
import quadrants.Quadrant;
import rooms.Room;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Psychiatric {
    private ArrayList<Level> levels;
    private Level actualLevel;
    
    public static final int LEVELS = 3; //Array normal

    public Psychiatric() {
        levels = new ArrayList<>();
        
        // TEST
        levels.add(new Level());
        /*
        for(int i = 0; i < LEVELS; i++){
            levels.add(new Level());
        }*/
        
        actualLevel = levels.get(0);
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
