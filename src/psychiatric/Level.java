/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psychiatric;
import quadrants.Quadrant;
import rooms.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;

public class Level {
    private File[] oneFiles;
    private File[] twoFiles;
    private File[] threeFiles;
    private Room[] rooms;
    private Room actualRoom;
    
    public static final int ROOMS_LEVEL = 4;

    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int RIGHT = 2;
    private static final int LEFT = 3;
    
    public Level() {
        
        //CREATE FILES LEVELS
        File newOneFiles = new File("C:\\Users\\korez\\OneDrive\\Documents\\NetBeansProjects\\pruebasMapa\\src\\filesOneDoor");
        oneFiles = newOneFiles.listFiles();
        
        File newTwoFiles = new File("C:\\Users\\korez\\OneDrive\\Documents\\NetBeansProjects\\pruebasMapa\\src\\filesTwoDoors");
        twoFiles = newTwoFiles.listFiles();
        
        File newTrheeFiles = new File("C:\\Users\\korez\\OneDrive\\Documents\\NetBeansProjects\\pruebasMapa\\src\\filesThreeDoors");
        threeFiles = newTrheeFiles.listFiles();
        
        //CREATE ROOMS
        rooms = new Room[ROOMS_LEVEL];
            //INITIAL ROOM
        InitialRoom initialRoom = new InitialRoom(oneFiles[(int)(Math.random()*(oneFiles.length))]);
        rooms[0] = initialRoom;
        
        //CREATE RANDOM HALLWAY
        for(int r = 0; r < ROOMS_LEVEL; r++){
            
            if(r == ROOMS_LEVEL - 2){
                while(true){
                    FinalRoom finalRoom = new FinalRoom(oneFiles[(int)(Math.random()*(oneFiles.length))]);
                    
                    if(rooms[r].getRoomUp() == null && rooms[r].isDoorUp()){
                        if(finalRoom.isDoorDown()){
                           rooms[r].setRoomUp(finalRoom);
                           finalRoom.setRoomDown(rooms[r]);
                           rooms[r+1] = finalRoom;
                           break;
                        }
                    }else if(rooms[r].getRoomDown() == null && rooms[r].isDoorDown()){
                        if(finalRoom.isDoorUp()){
                            rooms[r].setRoomDown(finalRoom);
                            finalRoom.setRoomUp(rooms[r]);
                            rooms[r+1] = finalRoom;
                            break;
                        }
                    }else if(rooms[r].getRoomRight() == null && rooms[r].isDoorRight()){
                        if(finalRoom.isDoorLeft()){
                            rooms[r].setRoomRight(finalRoom);
                            finalRoom.setRoomLeft(rooms[r]);
                            rooms[r+1] = finalRoom;
                            break;
                        }
                    }else if(rooms[r].getRoomLeft() == null && rooms[r].isDoorLeft()){
                        if(finalRoom.isDoorRight()){
                            rooms[r].setRoomLeft(finalRoom);
                            finalRoom.setRoomRight(rooms[r]);
                            rooms[r+1] = finalRoom;
                            break;
                        }
                    }
                }
                break;
            }
            else{
                while(true){
                    EnemyRoom newRoom = new EnemyRoom(twoFiles[(int)(Math.random()*(twoFiles.length))]);
                    
                    if(rooms[r].isDoorUp() && rooms[r].getRoomUp() == null){
                        if(newRoom.isDoorDown()){
                           rooms[r].setRoomUp(newRoom);
                           newRoom.setRoomDown(rooms[r]);
                           rooms[r+1] = newRoom;
                           break;
                        }
                    }else if(rooms[r].isDoorDown() && rooms[r].getRoomDown() == null){
                        if(newRoom.isDoorUp()){
                            rooms[r].setRoomDown(newRoom);
                            newRoom.setRoomUp(rooms[r]);
                            rooms[r+1] = newRoom;
                            break;
                        }
                    }else if(rooms[r].isDoorRight() && rooms[r].getRoomRight() == null){
                        if(newRoom.isDoorLeft()){
                            rooms[r].setRoomRight(newRoom);
                            newRoom.setRoomLeft(rooms[r]);
                            rooms[r+1] = newRoom;
                            break;
                        }
                    }else if(rooms[r].isDoorLeft() && rooms[r].getRoomLeft() == null){
                        if(newRoom.isDoorRight()){
                            rooms[r].setRoomLeft(newRoom);
                            newRoom.setRoomRight(rooms[r]);
                            rooms[r+1] = newRoom;
                            break;
                        }
                    }
                }
            }
        }
        
        //CREATE PLAYER
        Player player = new Player(Room.QUADRANTS_WIDTH*Quadrant.WIDTH/2, Room.QUADRANTS_HEIGHT*Quadrant.HEIGHT/2); //Lógica de psiquiatrico
        
        actualRoom = rooms[0];
        actualRoom.setPlayer(player);
    }
    
    public void draw(Graphics g) {
        actualRoom.draw(g);
    }
    
    public void keyPressed(int code){
        if(code == KeyEvent.VK_UP | code == KeyEvent.VK_DOWN | code == KeyEvent.VK_RIGHT | code == KeyEvent.VK_LEFT){
            int mov = actualRoom.keyPressed(code);
            Player player = actualRoom.getPlayer();
            
            if(mov == UP & actualRoom.isDoorUp()){
                player.setY(Room.QUADRANTS_HEIGHT*Quadrant.HEIGHT);
                actualRoom = actualRoom.getRoomUp();
                actualRoom.setPlayer(player);
                
            }else if(mov == DOWN & actualRoom.isDoorDown()){
                player.setY(0);
                actualRoom = actualRoom.getRoomDown();
                actualRoom.setPlayer(player);
                
            }else if(mov == RIGHT & actualRoom.isDoorRight()){
                player.setX(0);
                actualRoom = actualRoom.getRoomRight();
                actualRoom.setPlayer(player);
                
            }else if(mov == LEFT & actualRoom.isDoorLeft()){
                player.setX(Room.QUADRANTS_WIDTH*Quadrant.WIDTH);
                actualRoom = actualRoom.getRoomLeft();
                actualRoom.setPlayer(player);
                
            }
        }
    }
}
