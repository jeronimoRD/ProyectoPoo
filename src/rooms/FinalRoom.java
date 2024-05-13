/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rooms;

import static rooms.Room.DOOR;
import static rooms.Room.FLOOR;
import static rooms.Room.QUADRANTS_HEIGHT;
import static rooms.Room.QUADRANTS_WIDTH;
import static rooms.Room.WALL;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FinalRoom extends Room {
    public FinalRoom(File editor) {
        super(editor);
        
        collisions = new ArrayList<>();
        
        
        try {
            FileReader file = new FileReader(editor);
            int instruction;
            
            int quadrantX = 0;
            int quadrantY = 0;
            int row = 0;
            int column = 0;
            
            while ((instruction = file.read()) != -1) {
                if((char)instruction == WALL){
                    collisions.add(new rWall(quadrantX, quadrantY));
                    
                    column += 1;
                    quadrantX += rWall.WIDTH;
                    
                }else if((char)instruction == FLOOR){
                    
                    column += 1;
                    quadrantX += rWall.WIDTH;
                    
                }else if((char)instruction == DOOR){
                    //collisions.add(new rDoor(quadrantX, quadrantY));
                    if(row == 0){
                        this.doorUp = true;
                    }else if(row + 1 == QUADRANTS_HEIGHT){
                        this.doorDown = true;
                    }else if(column == 0){
                        this.doorLeft = true;
                    }else if(column + 1 == QUADRANTS_WIDTH){
                        this.doorRight = true;
                    }
                    
                    column += 1;
                    quadrantX += rWall.WIDTH;
                    
                }else{ //2 SPACES FOR LINE
                    if(column == 0){
                        row += 1;
                        quadrantY += rWall.HEIGHT;
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
    }
}
