/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io;
import elements.furniture.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import psychiatric.Room;
import static psychiatric.Room.HEIGHT;
import static psychiatric.Room.WIDTH;


public class RoomReader {
    private File[] oneDoor;
    private File[] twoDoor;
    private File[] threeDoor;
    
    private static final char DOOR = 'P';
    private static final char WALL = 'X';
    private static final char FLOOR = 'O';
    
    private static final char HORIZONTALGURNEY = 'G';
    private static final char VERTICALGURNEY = 'H';

    public RoomReader(String oneDoor, String twoDoors, String threeDoors){
        File newOneFiles = new File(oneDoor);
        this.oneDoor = newOneFiles.listFiles();
        
        File newTwoFiles = new File(twoDoors);
        this.twoDoor = newTwoFiles.listFiles();
        
        File newThreeFiles = new File(threeDoors);
        this.threeDoor = newThreeFiles.listFiles();
    }
    
    public Room read(int numberDoors, int enviromentLevel) throws IOException{
        Room room = new Room();
        FileReader reader = null;
        if(numberDoors == 1){
            reader = new FileReader(oneDoor[(int)(Math.random()*(oneDoor.length))]);
        }else if(numberDoors == 2){
            reader = new FileReader(twoDoor[(int)(Math.random()*(twoDoor.length))]);
        }else if(numberDoors == 3){
            reader = new FileReader(threeDoor[(int)(Math.random()*(threeDoor.length))]);
        }
        
        int instruction;
            
        int quadrants_width = WIDTH/100;
        int quadrants_height = HEIGHT/100;

        int quadrantX = 0;
        int quadrantY = 0;
        int row = 0;
        int column = 0;

        while ((instruction = reader.read()) != -1) {
            if((char)instruction == WALL){
                room.addBoundable(new Wall(quadrantX, quadrantY));

                column += 1;
                quadrantX += Wall.WIDTH;

            }else if((char)instruction == FLOOR){

                column += 1;
                quadrantX += Wall.WIDTH;

            }else if((char)instruction == DOOR){
                Door door = new Door(quadrantX, quadrantY);
                room.addBoundable(door);
                room.addCollapsible(door);
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

            }else if((char)instruction == HORIZONTALGURNEY){
                room.addBoundable(new HorizontalGurney(quadrantX, quadrantY));
                
                column += 1;
                quadrantX += Wall.WIDTH;
                
            }else if((char)instruction == VERTICALGURNEY){
                room.addBoundable(new VerticalGurney(quadrantX, quadrantY));
                
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
