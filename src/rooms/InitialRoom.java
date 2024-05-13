/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rooms;

import quadrants.Quadrant;
import static rooms.Room.*;
import quadrants.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import interfaces.Collidable;
import java.util.ArrayList;

public class InitialRoom extends Room{
    
    public InitialRoom(File editor) {
        super(editor);
        
        collisions = new ArrayList<>();
        quadrants = new Quadrant[QUADRANTS_WIDTH][QUADRANTS_HEIGHT];
        
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
                    quadrants[row][column] = new Wall(quadrantX, quadrantY);
                    
                    column += 1;
                    quadrantX += Quadrant.WIDTH;
                    
                }else if((char)instruction == FLOOR){
                    quadrants[row][column] = new Floor(quadrantX, quadrantY);
                    
                    column += 1;
                    quadrantX += Quadrant.WIDTH;
                
                }else if((char)instruction == DOOR){
                    
                    if(row == 0){
                        this.doorUp = true;
                    }else if(row + 1 == QUADRANTS_HEIGHT){
                        this.doorDown = true;
                    }else if(column == 0){
                        this.doorLeft = true;
                    }else if(column + 1 == QUADRANTS_WIDTH){
                        this.doorRight = true;
                    }
                    
                    quadrants[row][column] = new Door(quadrantX, quadrantY);
                    
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
        
    }
    
}
