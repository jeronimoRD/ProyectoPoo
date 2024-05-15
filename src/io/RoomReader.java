/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io;
import collidables.Wall;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import psychiatric.Room;
import static psychiatric.Room.HEIGHT;
import static psychiatric.Room.WIDTH;

/**
 *
 * @author korez
 */
public class RoomReader {
    private File[] oneDoor;
    private File[] twoDoor;
    private File[] threeDoor;
    
    private static final int DOOR = 'P';
    private static final int WALL = 'X';
    private static final int FLOOR = 'O';

    public RoomReader(String oneDoor, String twoDoors, String threeDoors) throws FileNotFoundException {
        File newOneFiles = new File(oneDoor);
        this.oneDoor = newOneFiles.listFiles();
        
        File newTwoFiles = new File(twoDoors);
        this.twoDoor = newTwoFiles.listFiles();
        
        File newThreeFiles = new File(threeDoors);
        this.threeDoor = newThreeFiles.listFiles();
    }
    
    public Room read1Door() throws IOException{
        Room room = new Room();
        
        File reader = oneDoor[(int)(Math.random()*(oneDoor.length))];
        int instruction;
            
        int quadrants_width = WIDTH/100;
        int quadrants_height = HEIGHT/100;

        int quadrantX = 0;
        int quadrantY = 0;
        int row = 0;
        int column = 0;

        while ((instruction = reader.read()) != -1) {
            if((char)instruction == WALL){
                room.addCollision(new Wall(quadrantX, quadrantY));

                column += 1;
                quadrantX += Wall.WIDTH;

            }else if((char)instruction == FLOOR){

                column += 1;
                quadrantX += Wall.WIDTH;

            }else if((char)instruction == DOOR){
                //collisions.add(new rDoor(quadrantX, quadrantY));
                if(row == 0){
                    room.setDoorUp(true);
                }else if(row + 1 == quadrants_height){
                    room.setDoorDown(true);
                }else if(column == 0){
                    room.setDoorLeft(true);
                }else if(column + 1 == quadrants_width){
                    room.setDoorRight(true);
                }

                column += 1;
                quadrantX += Wall.WIDTH;

            }else{ //2 SPACES FOR LINE
                if(column == 0){
                    row += 1;
                    quadrantY += Wall.HEIGHT;
                }else{
                    column = 0;
                    quadrantX = 0; 
                }
            }
        }
        
        return room;
    }
    
    public Room read2Door() throws IOException{
        Room room = new Room();
        
        int instruction;
            
        int quadrants_width = WIDTH/100;
        int quadrants_height = HEIGHT/100;

        int quadrantX = 0;
        int quadrantY = 0;
        int row = 0;
        int column = 0;

        while ((instruction = reader.read()) != -1) {
            if((char)instruction == WALL){
                room.addCollision(new Wall(quadrantX, quadrantY));

                column += 1;
                quadrantX += Wall.WIDTH;

            }else if((char)instruction == FLOOR){

                column += 1;
                quadrantX += Wall.WIDTH;

            }else if((char)instruction == DOOR){
                //collisions.add(new rDoor(quadrantX, quadrantY));
                if(row == 0){
                    room.setDoorUp(true);
                }else if(row + 1 == quadrants_height){
                    room.setDoorDown(true);
                }else if(column == 0){
                    room.setDoorLeft(true);
                }else if(column + 1 == quadrants_width){
                    room.setDoorRight(true);
                }

                column += 1;
                quadrantX += Wall.WIDTH;

            }else{ //2 SPACES FOR LINE
                if(column == 0){
                    row += 1;
                    quadrantY += Wall.HEIGHT;
                }else{
                    column = 0;
                    quadrantX = 0; 
                }
            }
        }
        
        return room;
    }
    
    public Room read3Door() throws IOException{
        Room room = new Room();
        
        int instruction;
            
        int quadrants_width = WIDTH/100;
        int quadrants_height = HEIGHT/100;

        int quadrantX = 0;
        int quadrantY = 0;
        int row = 0;
        int column = 0;

        while ((instruction = reader.read()) != -1) {
            if((char)instruction == WALL){
                room.addCollision(new Wall(quadrantX, quadrantY));

                column += 1;
                quadrantX += Wall.WIDTH;

            }else if((char)instruction == FLOOR){

                column += 1;
                quadrantX += Wall.WIDTH;

            }else if((char)instruction == DOOR){
                //collisions.add(new rDoor(quadrantX, quadrantY));
                if(row == 0){
                    room.setDoorUp(true);
                }else if(row + 1 == quadrants_height){
                    room.setDoorDown(true);
                }else if(column == 0){
                    room.setDoorLeft(true);
                }else if(column + 1 == quadrants_width){
                    room.setDoorRight(true);
                }

                column += 1;
                quadrantX += Wall.WIDTH;

            }else{ //2 SPACES FOR LINE
                if(column == 0){
                    row += 1;
                    quadrantY += Wall.HEIGHT;
                }else{
                    column = 0;
                    quadrantX = 0; 
                }
            }
        }
        
        return room;
    }
}
