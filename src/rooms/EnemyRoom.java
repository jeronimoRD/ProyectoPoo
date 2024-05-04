/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rooms;

import elements.Quadrant;
import elements.Room;
import static elements.Room.FLOOR;
import static elements.Room.QUADRANTS_HEIGHT;
import static elements.Room.QUADRANTS_ROW;
import static elements.Room.WALL;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import quadrants.Floor;
import quadrants.Wall;

public class EnemyRoom extends Room{
    
    private int enemies;
    
    public EnemyRoom(File editor) {
        super(editor);
        
        this.enemies = enemies;
        
        //CREATE ROOM
        quadrants = new Quadrant[QUADRANTS_ROW][QUADRANTS_HEIGHT];
        
        try {
            FileReader file = new FileReader(editor);
            int instruction;
            
            int quadrantX = 0;
            int quadrantY = 0;
            int row = 0;
            int column = 0;
            
            while ((instruction = file.read()) != -1) {
                if((char)instruction == WALL){
                    quadrants[row][column] = new Wall(quadrantX, quadrantY);
                    
                    column += 1;
                    quadrantX += Quadrant.WIDTH;
                    
                }else if((char)instruction == FLOOR){
                    quadrants[row][column] = new Floor(quadrantX, quadrantY);
                    
                    column += 1;
                    quadrantX += Quadrant.WIDTH;
                    
                }else{ //2 SPACES FOR LINE
                    if(column == 0){
                        row += 1;
                        quadrantY += Quadrant.HEIGHT;
                    }else{
                        column = 0;
                        quadrantX = 0; 
                    }
                }
            }
            file.close();
        } catch (IOException e) {
            throw new RuntimeException("Error: File no found");
        }
        
        //ADD ENEMIES
    }
    
}
